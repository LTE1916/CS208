import java.util.Scanner;

public class string_permutations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("input String");
        String a=scanner.next();
        String[]strings=a.split("");
        
    }
    static String recursion(int n,String[] strings){
        if(n==1){
            return strings[n-1];
        }
        return recursion(n-1,strings);
    }
}
