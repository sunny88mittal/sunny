package SRM533;

public class PikachuEasy{
   public String check(String word){
       word  = word.replace("pi", "");
       word  = word.replace("ka", "");
       word  = word.replace("chu", "");
       if(word.length() > 0){
          return "NO";
       }
       return "YES";
   }
}