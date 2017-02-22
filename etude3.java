/* Matthew Lang 2018313
   etude 3
   java 1.8.0_71

   Used Resources:
   code from a town hall metting and Dylan exlpaining hard how it
   works with his code, found it hard to understand how x>>y & 1
   works but i think i have figured it out, found it easy to left to right
   but harder on using BEDMAS code wise anyway

*/


import java.util.*;

public class etude3 {

    static void output(char[] x, int[] y, char z){
        String ansString = z +" "+y[0];
        if(y.length == 1){
            System.out.println(ansString);
            return;
        }
        for(int x1 = 0; x1 < x.length; x1++){
            ansString +=(" "+x[x1]+" ");
            ansString += y[x1+1];
        }
        System.out.println(ansString);
    }
        
    static void evl(int[] input,int aim,char r){
        char[] operands = new char[input.length-1];
        int ans;
        if(r == 'L'){
            for(int x = 0; x <(1<<(operands.length));x++){
                ans = input[0];
                for(int y = 0; y <operands.length; y++){
                    if(((x>>y)&1)==0){
                        ans += input[y+1];
                        operands[y] = '+';
                    } else{
                        ans *= input[y+1];
                        operands[y]= '*';
                    }
                }
                if(input.length == 1 && input[0] == aim) {
                    output(operands,input,r);
                    return;
                }
                else if(ans == aim){
                        output(operands,input,r);
                        return;
                }
            }
            System.out.println(r + " impossiable");
        }
        else if(r == 'N'){
            for(int x = 0; x <(1<<(operands.length));x++) {
                ans = 0;
                int multix = 0;
                int addx = 0;
                int[] addingNumbers = new int[input.length];
                int[] multiplyingNumbers = new int[input.length];
                boolean firstM = true;
                boolean b = false;
                
                for(int y = 0; y<operands.length;y++){
                    if(((x>>y)&1)==1){
                        operands[y] ='+';
                        addingNumbers[y] = input[y];
                        if(y == (operands.length-1)){
                            addingNumbers[y+1] = input[y+1];
                        }
                        if(multix > 1) {
                            multiplyingNumbers[y] = multix;
                            multix = 1;
                            firstM = true;
                        }
                        if(b){
                            addingNumbers[y] = 0;
                        }
                        b = false;
                    }else{
                        operands[y] = '*';
                        if(firstM) {
                            multix = input[y];
                            firstM = !firstM;
                        }
                        multix *= input[y+1];
                        if(y == (operands.length-1)){
                            multiplyingNumbers[y+1] = multix;
                        }
                        b = true;
                    }
                }
                multix = 0;
                addx = 0;
                for (int z = 0; z < addingNumbers.length; z++) {
                    addx += addingNumbers[z];
                }
                for (int z = 0; z < multiplyingNumbers.length; z++) {
                    multix += multiplyingNumbers[z];
                }
                ans = addx+multix;
                if(input.length == 1 && input[0] == aim) {
                    output(operands,input,r);
                    return;
                }
                else if(ans == aim) {
                    output(operands,input,r);
                    return;
                }
            }
            System.out.println(r + " impossiable");
        }
        return;
    }

    public static void main(String[]args){
        Scanner scan = new Scanner(System.in);
        int[] input;
        boolean ans = false;
        char rule;
        int aim;
        while(scan.hasNext()){
            String str = scan.nextLine();
            if(!str.isEmpty()){
                input = Arrays.stream(str.split(" ")).map(String::trim)        
                    .mapToInt(Integer::parseInt).toArray();
                aim = scan.nextInt();
                rule = scan.next().charAt(0);
                evl(input,aim,rule);
            }
        }

    }

}

    
