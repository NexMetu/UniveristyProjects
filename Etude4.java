/*Etude 4 Carpets
  Matthew Lang 2018313
  Dylan Pringle 1897321
  Mikhaila Howse 6845972

  this program assumes that all input carpets will be the same length
*/


import java.util.*;
import java.lang.*;

public class Etude4 {

    private static int matchvalue = 0;
    private static int bal = 10;
    private static ArrayList<char[]> BestCarpet;
    private static Random rand = new Random();

    static boolean checkNotMatch(char[] x, char[]y){
        assert x != null : "passed null pointer for first array";
        assert y != null : "passed null pointer for second array";
        assert x.length == y.length :
        "checkformatch needs arrays to be the same size";
        //checking arrays for matchs ------ need this to do the check
        for(int i =0; i < x.length; i++){
            if(x[i] == y[i]) return false;
        }
        return true;
    }

    static int checkMatchValue(char[] x, char[] y){
        assert x != null : "passed null pointer for first array";
        assert y != null : "passed null pointer for second array";
        assert x.length == y.length :
        "checkformatch needs arrays to be the same size";
        int matchvalue = 0;
        //checking arrays for matchs and returns a value that
        //is the number of matchs
        for(int i=0;i<x.length;i++){
            if(x[i] == y[i]) matchvalue++;
           
        }
        return matchvalue;
    }

    static int checkBalanceValue(char[] x, char[] y){
        assert x != null : "passed null pointer for first array";
        assert y != null : "passed null pointer for second array";
        assert x.length == y.length :
        "checkformatch needs arrays to be the same size";
        int matchvalue = 0;
        int nonmatch = 0;
        //checking arrays for matchs and returns a value that
        //is the number of matchs
        for(int i=0;i<x.length;i++){
            if(x[i] == y[i]) matchvalue++;
            else nonmatch++;
           
        }
        return Math.abs(matchvalue - nonmatch);
    }

    static char[] invertingStrip(char[] strip){
        char[] reversed = new StringBuilder(new String(strip))
            .reverse().toString().toCharArray();
        return reversed;
    }

    static void randgencarpet(Hashtable<Integer, char[]> cap, int carpetsize,
                              char r){
        ArrayList<char[]> carpettry = new ArrayList<char[]> ();
        for(int x = 0; x < carpetsize; x++){
            int y = rand.nextInt(cap.size());
            if(!carpettry.contains(cap.get(y)))
                carpettry.add(x, cap.get(y));
            else x--;
        }
        if(r == 'm'){
            int value = 0;
            for(int x = 1; x < carpetsize; x++){
                value += checkMatchValue(carpettry.get(x-1),
                                         carpettry.get(x));
            }
            if(value > matchvalue){
                BestCarpet = carpettry;
                matchvalue = value;
            }
        }else if(r == 'b'){
            int value = 0;
            for(int x =1; x <carpetsize; x++){
                value += checkBalanceValue(carpettry.get(x-1),
                                           carpettry.get(x));
            }
            if(value < bal || value == 0){
                bal = value;
                BestCarpet = carpettry;
            }
        }
    }

    static ArrayList<char[]> findCarpet(Hashtable<Integer, char[]> stripStock,
                                        int carpetsize, char rule){
        assert stripStock != null : "passed not hashtable";
        assert carpetsize > 0  :
        "passing 0>x for a needed positive number for carpet size";
        assert carpetsize <= stripStock.size() :
        "need more strips to make carpet";
        ArrayList<char[]> carpet = new ArrayList<char[]> (carpetsize);
        int n = stripStock.size();
        boolean[] stripused = new boolean[n];
        int striplength = stripStock.get(0).length;
        int maxValue = striplength * (carpetsize-1);
        if(rule == 'n'){
            //for each of the strips pick one and try build from it
            for(int i = 0; i <n; i++){
                carpet.add(stripStock.get(i));
                stripused[i] = true;                        
                //for the other strips in the stirp stock
                for(int r = 0; r < n; r++){
                    if(!stripused[r]){
                        char [] checkStrip = stripStock.get(r);
                        char [] invertStrip = invertingStrip(stripStock.get(r));
                        match:
                        for(int x = carpet.size(); x >= 0; x--){
                            if(x == carpet.size()){
                                if(checkNotMatch(carpet.get(x-1),
                                                 checkStrip)) {
                                    carpet.add(x, checkStrip);
                                    stripused[r] = true;
                                    break match;
                                }
                                else if(checkNotMatch(carpet.get(x-1),
                                                      invertStrip)){
                                    carpet.add(x, invertStrip);
                                    stripused[r] = true;
                                    break match;
                                }
                            }else if(x == 0){
                                if(checkNotMatch(carpet.get(0),
                                                 checkStrip)){
                                    carpet.add(0,checkStrip);
                                    stripused[r] = true;
                                    break match;
                                }else if(checkNotMatch(carpet.get(0),
                                                       invertStrip)){
                                    carpet.add(0, invertStrip);
                                    stripused[r] = true;
                                    break match;
                                }
                            }else{
                                if(checkNotMatch(carpet.get(x-1), checkStrip)
                                   && checkNotMatch(carpet.get(x), checkStrip)){
                                    carpet.add(x, checkStrip);
                                    stripused[r] = true;
                                    break match;
                                }else if(checkNotMatch(carpet.get(x-1),
                                                       invertStrip)
                                         && checkNotMatch(carpet.get(x),
                                                          invertStrip)){
                                    carpet.add(x, invertStrip);
                                    stripused[r] = true;
                                    break match;
                                }
                            }
                        }
                    }
                    if(carpet.size() == carpetsize) return carpet;
                }
                carpet.clear();
                stripused = new boolean[n];
                
            }            
        }else if(rule == 'm'){
            //create a few generations of the carpet;
            for(int i = 0; i <100000;i++){
                randgencarpet(stripStock, carpetsize, 'm');
                if(matchvalue == maxValue) return BestCarpet;
            }
            return BestCarpet;
        }else if(rule == 'b'){
            for(int i = 0;i < 100000;i++){
                randgencarpet(stripStock, carpetsize, 'b');
                if(bal == 0 || (striplength%2!=0 && bal == 1))
                    return BestCarpet;
            }
            return BestCarpet;
        }
        return null;
    }
                                            
    static void print(ArrayList<char[]>carpet, char rule){
        for(char[] x: carpet){
            for(int y=0;y<x.length;y++){
                System.out.print(x[y]);
            }
            System.out.println();
        }
        if(rule == 'm') System.out.println(matchvalue);
        else if(rule == 'b') System.out.println(bal);
    }     

    public static void main(String[]args){
        Hashtable<Integer, char[]>stripStock =
            new Hashtable<Integer, char[]> ();
        ArrayList<char[]> carpet;
        int carpetsize =0,stocksize=0,stripLength=0;
        char rule =' ';
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            String x = scan.nextLine();
            if(stripLength == 0) stripLength = x.length();
            char[] y = x.toCharArray();
            stripStock.put(stocksize++,y);
        }
        System.out.println();
        // for(int i=1;i<=stocksize;i++){
        //     System.out.println(Arrays.toString(stripStock.get(i)));
        //     System.out.println(Arrays.toString(stripStock.get(-i)));         
        // }
        if(args.length < 2){
            System.out.println("Please use some options");
            return;
        }else{
            rule = args[0].charAt(1);
            carpetsize = Integer.parseInt(args[1]);
        }
        if(carpetsize > stocksize){
            System.out.println("Not possiable with limited amount of strips");
            return;
        }
        carpet = findCarpet(stripStock,carpetsize,rule);
        if(carpet != null){
            print(carpet, rule);
        }else{
            System.out.println("Not possiable");
        }
        return;
        
    }

}
