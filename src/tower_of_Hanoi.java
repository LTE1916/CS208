import java.util.Scanner;

public class tower_of_Hanoi {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("input n:");
        int n = scanner.nextInt();
        
    }
    static void recursion (int n,String from,String target,String free){
        if(n==1){
            System.out.println("move disk1 from "+from+" to "+target );
        }
        recursion(n-1,from,free,target);
        System.out.println("move disk1 "+n+" from " +from+" to "+free);
    }
}
