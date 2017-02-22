/* Matthew lang and Dylan Pringle
 * Etude1
 * Java 8 Update 71
 */

import java.util.*;
import java.awt.*;

public class Etude1 {

    private static boolean isNumberLine(String s) {
        final int n =s.length();
        if(n == 0)return false;
        for(int i = 0;i < n;i++){
            if(!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }

    private static void runSim(char bstate,Hashtable<Character,String> dna, int
                          runs){
        Hashtable<String, Character> map = new Hashtable<String, Character>();
        Point cP = new Point();
        String cdna;
        char state = ' ';
        char cdirection = 'N';
        //runs the scenario
        for(int x = 0; x < runs;x++){
            if(map.get(cP.toString()) == null){
                map.put(cP.toString(), bstate);
                cdna = dna.get(bstate);
                //System.out.println("No points in the map");
                //System.out.println(cdna);
                //System.out.println(bstate);
            }else{
                //System.out.println("Found a state");
                state = map.get(cP.toString());
                cdna = dna.get(state);
                //System.out.println(state);
                //System.out.println(cdna);
                                  
            }
            //System.out.println(cdirection);
            switch(cdirection) {
                case 'N':
                    cdirection = cdna.charAt(2);
                    state = cdna.charAt(7);
                    break;
                case 'S':
                    cdirection = cdna.charAt(4);
                    state = cdna.charAt(9);
                    break;
                case 'E':
                    cdirection = cdna.charAt(3);
                    state = cdna.charAt(8);
                    break;
                case 'W':
                    cdirection = cdna.charAt(5);
                    state = cdna.charAt(10);
                    break;
            }
            //System.out.println("Placing :"+cP+" with state : "+state);
            map.put(cP.toString(), state);
            cP = newPoint(cP,cdirection);
            //System.out.println(cP);

        }
        //output to system.out
        System.out.println("# " + cP.x + " " + cP.y);
            
    }


    
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        Hashtable<Character, String> dna = new Hashtable<Character,String>();
        char bstate;
        boolean firstRule= true;
        int runs;
        bstate = ' ';
        String line;
        while(sc.hasNext()){
            line = sc.nextLine();
            if(line.isEmpty() || line.charAt(0) =='#') System.out.println();
            else if(!(line.charAt(0)=='#')){
                System.out.println(line);
                if(isNumberLine(line)){
                    runs = Integer.parseInt(line);
                    runSim(bstate,dna,runs);
                    dna.clear();
                    runs = 0;
                    firstRule = true;
                }
                else{
                    if(line.length() == 11){ //if its a rule line
                        if(firstRule){
                            bstate = line.charAt(0);
                            firstRule = false;
                        }
                        dna.put(line.charAt(0), line);
                    }
                }
            }
        }
    }


    public static Point newPoint(Point x, char y){
        if(y == 'N'){
            x.y++;
        }else if(y == 'S'){
            x.y--;
        }else if(y == 'E'){
            x.x++;
        }else if(y == 'W'){
            x.x--;
        }else{
            System.out.println("unknown direction");
        }
        return x;
    }
}
