import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Interval_covering {

  static class Interval {

    int start;
    int end;
    public  Interval(int start,int end){
      this.start=start;
      this.end=end;
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    List<Interval> arrayList = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      int s = in.nextInt();
      int e = in.nextInt();
      Interval interval = new Interval(s,e);
      arrayList.add(interval);
    }
    arrayList.sort(Comparator.comparingInt(o -> o.start));
    int result = 1;
  //  int rangeLeft = arrayList.get(0).start;
    int rangeRight = arrayList.get(0).end;

    for (int i = 1; i < n; i++) {
      if (arrayList.get(i).start <= rangeRight) {
        rangeRight = Math.min(arrayList.get(i).end, rangeRight);
      } else {
        result++;
   //     rangeLeft = arrayList.get(i).start;
        rangeRight = arrayList.get(i).end;
      }

    }
    System.out.println(result);
  }

}

