import java.io.*;
import java.util.*;

class Node{
    int id;
    int cnt;
    int depth;
    boolean isVisited;
    ArrayList<Node> connectList =new ArrayList<>();
    
    public  Node(int id){
        this.id=id;
    }
}

public class new_year_call {
    public static void main(String[]args){
        QReader input=new QReader();
        QWriter out = new QWriter();
      //  Scanner input=new Scanner(System.in);
        int n=input.nextInt();//n nodes
        int m= input.nextInt();//m edges
        int p= input.nextInt();//origin
        int q= input.nextInt();//cases
    
        int[]ans=new int[36000];
        ans[0]=1;

        Node[]nodes=new Node[n+1];
        nodes[0]=null;
        for (int i = 1; i < n+1; i++) {
            nodes[i]=new Node(i);
        }

        for (int i = 0; i < m; i++) {
            int a= input.nextInt();
            int b= input.nextInt();
            nodes[a].connectList.add(nodes[b]);
            nodes[b].connectList.add(nodes[a]);

        }
        Node root=nodes[p];
        Queue<Node> queue=new LinkedList<>();
        queue.offer(root);
        root.isVisited=true;
        int maxDepth=0;
        while(!queue.isEmpty()){
           Node cur= queue.poll();
           int cnt=0;
            for (int i = 0; i <cur.connectList.size(); i++) {
                if (!cur.connectList.get(i).isVisited) {
                    cur.connectList.get(i).isVisited = true;
                    cur.connectList.get(i).depth = cur.depth + 1;
                    queue.offer(cur.connectList.get(i));
                    cnt++;
                }
            }
                cur.cnt=cnt;
                ans[cur.depth+1]+=cnt;
            
            if(cur.depth>maxDepth){
                maxDepth=cur.depth;
            }

        }
        int i=0;
        while (i<maxDepth){
            ans[i+1]+=ans[i];
            i++;
        }
    
        for (int j = 0; j < q; j++) {
            int x= input.nextInt();
            if(x>maxDepth){
                out.print(ans[maxDepth]+" ");
            }else out.print(ans[x]+" ");
        }
        out.close();
    }
}
