/*Etude 11
  Matthew Lang 2018313
  Dylan Pringle 1897321


  Also worked with Mikihaila Howse building the sortting of the anagrams
  http://stackoverflow.com/questions/5690351/
  java-stringlist-toarray-gives-classcastexception
 */


import java.util.*;


public class Etude11 {

    static ArrayList<String> foundAna = new ArrayList<String> ();
    static Hashtable<Character, ArrayList<String>> dict
        = new Hashtable<Character, ArrayList<String>>();
    static int maxWords;

    static void sortOutput(ArrayList<String> in, int m ){
        ArrayList<String> res = new ArrayList<String>();
        for(int word =1;word<=m;word++){
            ArrayList<String> wordlist = new ArrayList<String>();
            for(int string=0;string<in.size();string++){
                String [] s = in.get(string).split(" ");
                if(s.length == word){
                    wordlist.add(in.get(string));
                }
            }
            Collections.sort(wordlist, new comp());
            res.addAll(wordlist);
            wordlist.clear();
        }
         
    
        for(String r: res){
            System.out.println(r);
        }
    }
    
     

    static char[] processString(String givenString){
        char[] givenStringArray = null;
        int givenStringRealLength = 0;
        givenString = givenString.toLowerCase();
        for(int x = 0; x < givenString.length(); x++){
            if(Character.isAlphabetic(givenString.charAt(x))){
                givenStringRealLength++;
            }
        }
        givenStringArray = new char[givenStringRealLength];
        int y = 0;
        for(int x = 0; x < givenString.length(); x++){
            if(Character.isAlphabetic(givenString.charAt(x))){
                givenStringArray[y++] = givenString.charAt(x);
            }
        }
        Arrays.sort(givenStringArray);
        return givenStringArray;
    }

    static void processDict(Scanner scan){
        ArrayList<String> current;
        while(scan.hasNextLine()){
            String x = scan.nextLine();
             if(!x.isEmpty()){
                 x = x.trim();
                if(dict.containsKey(x.charAt(0))){
                    current = dict.get(x.charAt(0));
                    x = x.trim();current.add(x);
                    dict.put(x.charAt(0), current);
                }else{
                    current = new ArrayList<String>();
                    current.add(x);
                    dict.put(x.charAt(0), current);                        
                }
            }
        }
             
        return;
    }
        

    static ArrayList<String> findAnagrams(char[] startString,
                                          Hashtable<Character,
                                          ArrayList<String>> dict1){
        char[] givenArray = startString;
        ArrayList<String> dictentry;
        ArrayList<String> anagramWords = new ArrayList<String>();
        for(int i=0;i<givenArray.length;i++){
            dictentry = dict1.get(givenArray[i]);
            for(String x: dictentry){
                char[] sortedArray = processString(x);
                boolean go = matchCharArray(givenArray, sortedArray);
                if(go){
                    if(!anagramWords.contains(x)) anagramWords.add(x);
                }
            }
        }
        System.out.println(Arrays.toString(anagramWords.toArray()));
        return anagramWords;
    }

    static ArrayList<String> findAnagrams(char[] startString){
        return findAnagrams(startString, dict);
    }
    
    static char[] removeLettersArray(char[] arrayToRemove, char[] lettersToRemove){
        char[] temp = arrayToRemove.clone();
        for(int x=0;x<lettersToRemove.length;x++){
            found:
            for(int y=0;y<temp.length;y++){
                if(temp[y] == lettersToRemove[x]){
                    temp[y] = '0';
                    break found;
                }
            }
        }
        if(!((temp.length - lettersToRemove.length) < 0)){
            char[] returnarray = new char[temp.length - lettersToRemove.length];
            int y = 0;
            for(int x=0;x<temp.length;x++){
                if(temp[x] != '0') {
                    returnarray[y++] = temp[x];
                }
            }
            return returnarray;
        }else{
            return null;
        }
    }
                           

    static void buildOutput(char[] startString){
        ArrayList<String> anagramWords = findAnagrams(startString);
        for(int i=0; i < anagramWords.size(); i++){
            String x = anagramWords.get(i);
            if(x.length() == startString.length){
                foundAna.add(x);
            }
            char[] temp = removeLettersArray(startString, x.toCharArray());
            if(temp != null){
                buildPhase(x, temp, anagramWords, 1);
            }
            
        }
        System.out.println(Arrays.toString(foundAna.toArray()));
        //foundAna = Sort(foundAna,maxWords);
                
    }

     static ArrayList<String> Sort(ArrayList<String> input, int maxwords){
        ArrayList<String> res = new ArrayList<String>();
        Hashtable<Integer, ArrayList<String>> sort = new Hashtable<Integer, ArrayList<String>>();
        for(String s: input){
            String [] x = s.split(" ");
            int l = x.length;
            if(!sort.containsKey(l)){
                ArrayList<String> nep = new ArrayList<String>();
                nep.add(s);
                sort.put(l, nep);
            }else{
                ArrayList<String> nep = sort.get(l);
                nep.add(s);
                sort.put(l,nep);
            }
        }
        for(int i= 1; i<= maxwords; i++){
            if(sort.containsKey(i)){
                // System.out.println(i);
                res.addAll(sort.get(i));
            }
        }
        return res;
        
        
     }

    static void buildPhase(String word, char[] temp,
                           ArrayList<String> list, int words){
        if(words == maxWords) return;
        for(int i = 0; i<list.size(); i++){
            if(matchCharArray(temp, list.get(i).toCharArray())){
                String tempString = word+" "+list.get(i);
                char[] temp2 = removeLettersArray(temp,list.get(i).toCharArray());                if(temp2 != null){
                    if(temp2.length == 0) {
                        //processtheword and compare
                        tempString = processAnagram(tempString);
                        if(!foundAna.contains(tempString)){
                            foundAna.add(tempString);
                        }
                    }
                    //if more letters to use and n
                    else {
                        buildPhase(tempString, temp2, list, words+1); 
                    }
                }else{
                    break;
                }
            }
        }
    }

    static String  processAnagram(String x){
        String[] stringarray = x.split(" ");
        Arrays.sort(stringarray);
        Arrays.sort(stringarray, new comp());
        String r= "";
        for(String a: stringarray){
            r+= a+" ";
        }
        return r.trim();


    }
        
    static boolean matchCharArray(char[] a, char[] b){
        boolean[] checkarray = new boolean[a.length];
        boolean found = false;
        for(int x = 0;x<b.length;x++){
            found = false;
            look:
            for(int y=0;y<a.length;y++){
                if(!checkarray[y] && b[x] == a[y]){
                    checkarray[y] = true;
                    found = true;
                    break look;
                }
              
            }
            if(!found){
                return found;
            }
        }
        return found;

    }

    
    
    public static void main(String[] args){
        String givenString = null;
        char[] givenStringArray = null;
        Scanner scan = new Scanner(System.in);
        if(args.length > 1){
            givenString = args[0];
            maxWords = Integer.parseInt(args[1]);
        }
        processDict(scan);
        givenStringArray = processString(givenString);
        buildOutput(givenStringArray);
        System.out.println(Arrays.toString(foundAna.toArray()));
        //System.out.println(dict.toString());
        sortOutput(foundAna,maxWords);
    }



}

class comp implements Comparator<String> {
    public int compare(String first,String second)
        {
            String[] fWords = first.split(" ");
            String[] sWords = second.split(" ");
            for(int f = 0; f < fWords.length; f++){ 
                if (fWords[f].length() !=
                    sWords[f].length()){
                    int x = fWords[f].length();
                    int y = sWords[f].length();
                    return Integer.compare(y,x);
                }
            }
            char c1,c2;
 
            for(int g =0; g < fWords.length; g++){
                for(int h = 0; h<fWords[g].length() ; h ++){
                    c1  = fWords[g].charAt(h);
                    c2 = sWords[g].charAt(h);
                    if(c1 != c2){
                        return Character.compare(c1,c2);
                    }
                }
            }
            return 1;
        }
}
    

