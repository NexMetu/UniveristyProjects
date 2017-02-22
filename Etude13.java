/*
  Matthew Lang 2018313
  Dylan Pringle 1897321
  
  got the idea for pascalls trinagle form the town halls meetings and
  http://mathworld.wolfram.com/BinomialCoefficient.html
  http://l3d.cs.colorado.edu/~ctg/classes/struct12/lecslides/struct12lect14.pdf
  used to help work out how to code pascalls trinagle
 */



public class Etude13{
     
    public static void main(String [] args){
        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        long[][] result = new long[n+1][];
        for (int x=0; x<result.length; x++){
            result[x] = new long[x+1];
            result[x][0] = 1;
            result[x][x] = 1;
            for (int y=1; y<x; y++){
                result[x][y] = result[x-1][y]+result[x-1][y-1];
            }
        }
        System.out.println(result[n][k]);
    }

}
