import sun.misc.Queue;

import java.util.Scanner;
class Point {
    int x;
    int y;
    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }
}

public class knight {
    public static void main(String[]args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int x1= in.nextInt()+n/2;
        int y1= in.nextInt()+n/2;
        int x2= in.nextInt()+n/2;
        int y2= in.nextInt()+n/2;
        int[][]arr=new int[n+1][n+1];
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < n+1; j++) {
                arr[i][j]=-1;
            }
        }
        arr[x1][y1]=0;
        int xDistance=Math.abs(n-x1);
        int yDistance=Math.abs(n-y1);
        Queue<Point>queue = new Queue<>();
        Point p=new Point(x1,y1);
        queue.enqueue(p);
        while (!(p.x==x2&&p.y==y2)){
            p= queue.dequeue();
           Point point1=new Point(p.x-2,p.y+1 );
           Point point2=new Point(p.x-2,p.y-1);
            Point point3=new Point(p.x+2,p.y+1);
            Point point4=new Point(p.x+2,p.y-1);
            Point point5=new Point(p.x-1,p.y+2);
            Point point6=new Point(p.x-1,p.y-2);
            Point point7=new Point(p.x+1,p.y+2);
            Point point8=new Point(p.x+1,p.y-2);
           if(check(arr,point1.x,point1.y,p.x,p.y,n))queue.enqueue(point1);
            if(check(arr,point2.x,point2.y,p.x,p.y,n))queue.enqueue(point2);
            if(check(arr,point3.x,point3.y,p.x,p.y,n))queue.enqueue(point3);
            if(check(arr,point4.x,point4.y,p.x,p.y,n))queue.enqueue(point4);
            if(check(arr,point5.x,point5.y,p.x,p.y,n))queue.enqueue(point5);
            if(check(arr,point6.x,point6.y,p.x,p.y,n))queue.enqueue(point6);
            if(check(arr,point7.x,point7.y,p.x,p.y,n))queue.enqueue(point7);
            if(check(arr,point8.x,point8.y,p.x,p.y,n))queue.enqueue(point8);
        }
        System.out.println(arr[p.x][p.y]);
    }
    public static boolean check(int[][]arr,int x,int y,int xOri,int yOri,int n){
        if(x>=0&&y>=0&&Math.abs(x)<=n&&Math.abs(y)<=n&&arr[x][y]==-1) {
            arr[x][y]=arr[xOri][yOri]+1;
            return true;
        }else return false;
    }
}
