import java.util.Scanner;

public class tile_rectangle {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("input n:");
        int n = scanner.nextInt();
        System.out.println();
        int []memory=new int[n+1];
        System.out.println(recursion(n,memory));
    }
    public static int recursion(int n,int[]memory){
        if(n==1){return 1;}
        if(n==2){return 2;}
        else if(memory[n]>0){
            return memory[n];
        }else memory[n]=recursion(n-1,memory)+recursion(n-2,memory);
            return memory[n];
    }

}
