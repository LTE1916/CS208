import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Breaker {

  public static void main(String[]args){
    QReader in = new QReader();
    QWriter out = new QWriter();
    String str = in.nextLine();
    char[] chars = str.toCharArray();
    int n = in.nextInt();
    String[] strings = new String[n];
    for (int i = 0; i < n; i++) {
      strings[i] = in.nextLine();
    }
    ArrayList<Interval> coverings = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      char[]dest = strings[i].toCharArray();
      int []tmp = kmp(chars,dest);
      int j = 0;
      while (j<tmp.length&&tmp[j]!=0){
        coverings.add(new Interval(tmp[j],tmp[j]+strings[i].length()-1));
        j++;
      }
    }


    coverings.sort(Comparator.comparingInt(o -> o.start));
    if(coverings.size()>0) {
      int result = 1;
      int rangeRight = coverings.get(0).end;
      for (int i = 1; i < coverings.size(); i++) {
        if (coverings.get(i).start <= rangeRight) {
          rangeRight = Math.min(coverings.get(i).end, rangeRight);
        } else {
          result++;
          rangeRight = coverings.get(i).end;
        }

      }
      out.println(result);
    }else out.println(0);
    out.close();
  }

  public static int[] kmp(char[] str, char[] dest){

    int[] next = kmpnext(dest);
    int[] ans = new int[str.length-dest.length+1];
    //System.out.println("next ="+ Arrays.toString(next));
    int index = 0;
    for(int i = 0, j = 0; i < str.length; i++){

      while(j > 0 && str[i] != dest[j]){
        j = next[j-1];
      }
      if(str[i]== dest[j]){
        j++;
      }
      if(j == dest.length){
        int tmp = i-j+1;
        ans[index] = tmp+1;
        index++;
        j = next[j-1];
      }
    }
    return ans;
  }


  public static int[] kmpnext(char[] dest){
    int[] next = new int[dest.length];
    next[0] = 0;

    for(int i = 1,j = 0; i < dest.length; i++){
      while(j > 0 && dest[j] != dest[j]){
        j = next[j - 1];
      }
      if(dest[j] == dest[j]){
        j++;
      }
      next[i] = j;
    }
    return next;
  }
}
class Interval {
  int start;
  int end;
  public  Interval(int start,int end){
    this.start=start;
    this.end=end;
  }
}
