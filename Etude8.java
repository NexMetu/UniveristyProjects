/*
  Matthew Lang
  2018313
  Etude 8
 */


import java.util.*;

public class Etude8 {

    private static final int max = 2000000;
    private static Hashtable <Integer, Integer> DivSum =
        new Hashtable<Integer, Integer> ();
    private static Hashtable <Integer, Boolean> Shown =
        new Hashtable<Integer, Boolean> ();
      
    
    public static void main(String args[]) {
        int y,ssum,ssum2;
        for(int x = 2; x < max; x++){
            if(Shown.get(x) == null){
                if(DivSum.get(x) == null) {
                    ssum = findDivisorSum(x);
                    DivSum.put(x, ssum);
                }else{
                    ssum= DivSum.get(x);
                }
                if(DivSum.get(ssum) == null){
                    ssum2 = findDivisorSum(ssum);
                    DivSum.put(ssum, ssum2);
                }else{
                    ssum2 = DivSum.get(ssum);
                }
                if(x == ssum2){
                    System.out.println(Math.min(x,ssum) + " "
                                       + Math.max(x,ssum));
                Shown.put(x, true);
                Shown.put(ssum, true);
                }          
            }
        }
    }

    private static int findDivisorSum(int x){
        int sRofX = (int)Math.sqrt(x);
        int sum=0;
        for(int i = 2; i <= sRofX; i++) {
            if(x % i == 0)  {
                sum += i;
                int y = x/i;
                if(y!=i){
                    sum+=y;
                }
            }
        }
        return sum;
    }
}
