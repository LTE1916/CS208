import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class lhyyy{
  static class Node{
        int id;
        int depth;
        int father;
        boolean isVisited;
        ArrayList<Node> connectList =new ArrayList<>();

        public  Node(int id){
            this.id=id;
        }
    }
    public static void main(String[]args){
        QReader in=new QReader();
        QWriter out = new QWriter();
        int T= in.nextInt();//case

        for (int i = 0; i < T; i++) {
            int ans=-1;
            int n= in.nextInt();//nodes
            int m= in.nextInt();//edges
            Node[]nodes = new Node[2*n];
            for (int j = 0; j < n; j++) {
                nodes[2*j]=new Node(j+1); //even index: positive,out
                nodes[2*j+1]=new Node(-(j+1)); //odd index:negative, in
            }
            for (int j = 0; j < m; j++) {
                int x=in.nextInt();//from x to y
                int y= in.nextInt();
                // x-out ---- y-in
                nodes[2*(x-1)].connectList.add(nodes[2*(y-1)+1]);
                nodes[2*(y-1)+1].connectList.add(nodes[2*(x-1)]);
            }
            //initial
//            for (int j = 0; j < 2*n; j++) {
//                nodes[j].isVisited=false;
//
//                nodes[j].depth=0;
//                nodes[j].father=0;
//            }

            //BFS
            for (int j = 0; j < 2*n; j+=2) {

                for (int k = 0; k < 2*n; k++) { //initial
                    nodes[k].isVisited=false;
                    nodes[k].depth=0;
                    nodes[k].father=0;
                }
                Node root = nodes[j];
                Queue<Node>queue = new ArrayDeque<>();
                //ArrayQueue<Node> queue = new ArrayQueue<>(2*n);
                queue.offer(root);
                root.isVisited=true;
                while (!queue.isEmpty()){
                    Node cur = queue.poll();
                    for (int k = 0; k <cur.connectList.size(); k++) {
                        if (!cur.connectList.get(k).isVisited) {
                            cur.connectList.get(k).isVisited = true;
                            cur.connectList.get(k).father= cur.id;
                            cur.connectList.get(k).depth = cur.depth + 1;
                            queue.offer(cur.connectList.get(k));
                        }else {
                          if(!(cur.connectList.get(k).id ==cur.father)) {
                              int res = (cur.depth+1)+(cur.connectList.get(k).depth);
                              if(ans==-1){ans=res;}else
                              if(res<ans){ans=res;}
                          }
                        }
                    }
                }
            }
            out.println(ans);
        }
        out.close();
    }
}
