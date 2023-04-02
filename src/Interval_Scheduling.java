import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javafx.util.Pair;

public class Interval_Scheduling {
 static Map<Pair<Integer,Integer>,String> map = new HashMap<>();
  public static void main(String []args){
//    ArrayList<String> strings = new ArrayList<>();
//    ArrayList<Integer> aList = new ArrayList<>();
//    ArrayList<Integer> bList = new ArrayList<>();
//    Scanner in = new Scanner(System.in);
//    while (in.hasNext())){
//      String label = in.next();
//      int a = in.nextInt();
//      int b = in.nextInt();
//      strings.add(label);
//      aList.add(a);
//      bList.add(b);
//    }
//    int [][]arr = new int[aList.size()][2];
//    for (int i = 0; i < aList.size(); i++) {
//      arr[i][0]=aList.get(i);
//      arr[i][1]=bList.get(i);
//    }

    Scanner in = new Scanner(System.in);
    int n = 8;
    int [][] arr = new int[n][2];

    for (int i = 0; i < n; i++) {
      String label = in.next();
      int a = in.nextInt();
      int b = in.nextInt();
      arr[i][0]=a;
      arr[i][1]=b;
      Pair<Integer,Integer> pair = new Pair<>(a,b);
      map.put(pair,label);
    }
    ArrayList<String>arrayList=eraseOverlapIntervals(arr);
    arrayList.forEach(s -> System.out.print(s+" "));
  }

    public static ArrayList<String> eraseOverlapIntervals(int[][] intervals) {
    ArrayList<String> arrayList = new ArrayList<>();

      Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[1]));

      int n = intervals.length;
      int right = intervals[0][1];
      int ans = 1;
      arrayList.add(map.get(new Pair<>(intervals[0][0], intervals[0][1])));
      for (int i = 1; i < n; ++i) {
        if (intervals[i][0] >= right) {
          arrayList.add(map.get(new Pair<>(intervals[i][0], intervals[i][1])));
          ++ans;
          right = intervals[i][1];
        }
      }
      return arrayList;
    }

}
