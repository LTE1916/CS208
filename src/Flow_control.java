import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Flow_control {
  public static class Node {
    int inDegree;
    int depth;
    Node ance;
    ArrayList<Node> son = new ArrayList<>();
   // ArrayList<Node> sonMerged = new ArrayList<>();
    ArrayList<Node> father = new ArrayList<>();

    // ArrayList<Node>fatherMerged = new ArrayList<>();//not necessary!!!
    public Node() {

    }
  }

  public static void main(String[] args) {
    QReader in = new QReader();
    QWriter out = new QWriter();
    int T = in.nextInt(); // test case
    for (int i = 0; i < T; i++) {
      int n = in.nextInt();
      int validCnt=n;
      Node[] nodes = new Node[n + 1];

      nodes[0] = new Node();
      for (int j = 1; j < n + 1; j++) {
        Node node = new Node();
        node.ance = node;
        nodes[j] = node;
        //    merged[j] = j;
      }//O(N)
      int m = in.nextInt(); // edges
      for (int j = 1; j < m + 1; j++) {
        int father = in.nextInt();
        int son = in.nextInt();
        nodes[father].son.add(nodes[son]);
        nodes[son].father.add(nodes[father]);
        nodes[son].inDegree++;
        //merge,O(M)
      }
      for (int k = 1; k < n + 1; k++) {
        if (nodes[k].father.size() > 1) {
          int length = nodes[k].father.size();

          Node father = findAncestry(nodes[k].father.get(0));//index to represent the union set
          for (int j = 1; j < length; j++) {
            Node tmpFather = findAncestry(nodes[k].father.get(j));
            if (!father.equals(tmpFather)) {
             if(tmpFather.equals(nodes[k].father.get(j))) {
               tmpFather.ance = father;
               father.father.addAll(tmpFather.father);
               father.son.addAll(tmpFather.son);
               father.inDegree += tmpFather.inDegree;
               validCnt--;
             }
            }
          }

        }//O(N*K)
      }

      Queue<Node> queue = new ArrayDeque<>();
    ArrayList<Node> topoOrder = new ArrayList<>();
    Node root = nodes[1].ance;
    topoOrder.add(root);
    queue.offer(root);
    if (root.inDegree == 0) {// root enqueue
      while (!queue.isEmpty()) {
        Node cur = findAncestry(queue.poll());
        for (int j = 0; j < cur.son.size(); j++) {
         Node son = findAncestry(cur.son.get(j));
           // location of son in new graph
            if (son.depth < cur.depth + 1) {
              son.depth = cur.depth + 1;
            }
            son.inDegree--;//!!!
            if (son.inDegree == 0) {
              queue.offer(son);
              topoOrder.add(son);
            }
          }

      }//O(M*N)
      if (topoOrder.size() < validCnt) {
        out.println("No");
      } else {
        out.println("Yes");
        out.print(1 + " ");
        for (int j = 2; j < n + 1; j++) {
          out.print(nodes[j].ance.depth - nodes[j].father.get(0).ance.depth + " ");
        }
        out.println("");
      }
    } else out.println("No");
  }
    out.close();
}




  public static Node findAncestry(Node node){
    if(node.ance==node) {
      return node;
    } else {
    return findAncestry(node.ance);
    }
  }
}
