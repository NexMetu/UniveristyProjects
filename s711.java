/*Matthew Lang
  java 8
  found this resource that explain how to work the math behind the problem, used
   this to code in java, there are java solutions in the forum however its
   brute force and after explaining how you can use factors to make a more sound
   pratice i tried my hand at it, not sure if this is "cheating" however if it
   is just let me know and ill try to make sure i dont do it again when trying
   to solve and understand the problems
   https://programmingpraxis.com/2009/11/27/7-11/
 */

import java.text.*;

public class s711 {

    final static int answer = 711000000;
    public static void main(String[]args){
        int[] factors = new int[100];
        int a,b,c,d,numfactors = 0;
        DecimalFormat Form = new DecimalFormat("$0.00");
        for(int x = 1; x <= 711; x++){
            if(answer % x == 0){
                factors[numfactors] = x;
                numfactors++;
            }
        }
        for(int a1 = 0; a1 < numfactors; a1++){
            a = factors[a1];
            for(int b1= a1; b1<numfactors; b1++){
                b = factors[b1];
                    for(int c1= b1; c1 <numfactors; c1++){
                        c = factors[c1];
                        d = 711 - c - b -a;
                        if(d < c){
                            break;
                        }    
                        if(a*b*c*d == answer){
                            String output = Form.format((double)a/100);
                            output = output +" "+ Form.format((double)b/100);
                            output = output +" "+ Form.format((double)c/100);
                            output = output +" "+ Form.format((double)d/100);
                            System.out.println(output);
                            return; 
                        }
                    }
                }
            }
            
        }


}
