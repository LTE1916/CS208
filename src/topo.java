import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

  public class topo {

    public static int vertexNum;
    public static int arcNum;
    public static int[][] arr;
    public static int[] inDegree;
    public static ArrayList<Integer> ans = new ArrayList<>();

    public static void main(String args[]) {
      Scanner sc = new Scanner(System.in);
      vertexNum = sc.nextInt();
      arcNum = sc.nextInt();
      sc.nextLine();
      arr = new int[vertexNum + 1][vertexNum + 1];
      inDegree = new int[vertexNum + 1];
      int vertex1, vertex2;
      for (int i = 0; i < arcNum; i++) {
        vertex1 = sc.nextInt();
        vertex2 = sc.nextInt();
        sc.nextLine();
        arr[vertex1][vertex2] = 1;
      }
      //initial inDegree
      int rudu = 0;
      for (int j = 1; j < vertexNum + 1; j++) {
        for (int i = 1; i < vertexNum + 1; i++) {
          if (arr[i][j] == 1) {
            rudu++;
          }
        }
        inDegree[j] = rudu;
        rudu = 0;
      }
      TopoSort();
    }

    public static void TopoSort() {
      // 找到当前入度为0的点
      // 1.把该点加入到ans答案数组中
      // 2.把该点所关联的边的入度都减去1
      // 3.设置该点的入度为-1，表明该点已经被放入到ans数组中

      //只要还存在入度为0的点，就进行while循环
      int vertexfound = findIndegreeZero();
      while (vertexfound != -1) {
        ans.add(vertexfound);
        inDegree[vertexfound] = -1;
        for (int j = 1; j < vertexNum + 1; j++) {
          if (arr[vertexfound][j] > 0) {
            inDegree[j]--;
          }
        }
        vertexfound = findIndegreeZero();

      }
      //输出拓扑排序的结果
//      if (ans.size() < vertexNum) {
//        //存在环
//        System.out.println("该图存在环");
//      } else {
      for (Integer an : ans) {
        System.out.print(an + " ");
      }
    }

    public static int findIndegreeZero() {
      PriorityQueue<Integer>queue = new PriorityQueue<>();
      for (int i = 1; i < vertexNum + 1; i++) {
        if (inDegree[i] == 0) {
          queue.offer(i);
        }
      }
      if(queue.isEmpty()) return -1;
      else return queue.poll();
    }
  }
