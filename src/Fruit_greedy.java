import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;

public class Fruit_greedy {
  public static void main(String[]args){
    QReader in = new QReader();
    QWriter out = new QWriter();
    int n = in.nextInt();
    ArrayList<Fruit>fruits = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      int l = in.nextInt();
      int r = in.nextInt();
      int v = in.nextInt();
      Fruit fruit = new Fruit(l,r,v);
      fruits.add(fruit);
    }
    ArrayList<Fruit>sortByl =new ArrayList<>(fruits);
    fruits.sort((o1, o2) -> o2.v-o1.v);
    sortByl.sort(Comparator.comparingInt(o->o.l));
    int[] set = new int[n];
    int x = 0;
    for (int i = 0; i < n; i++) {
      x = Math.max(x+1,sortByl.get(i).l);
      set[i] = x;
    }

    int []T = Arrays.stream(set).sorted().toArray();


    Map<Integer,Fruit> match = new HashMap<>();
    ArrayList<Fruit> S = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      int left = fruits.get(i).l;
      int index = 0;
      while (T[index]<left){
        index++;
      }
      if (LinearMatch(fruits.get(i),left,match,T,index)){
        S.add(fruits.get(i));
      }
    }
    long ans = 0;
    for (Fruit fruit : S) {
      ans += fruit.v;
    }
    out.println(ans);
    out.close();
  }
  public static boolean LinearMatch(Fruit fruit, int x, Map<Integer,Fruit> match,int []T,int index){
    if (x>fruit.r){ return false;}
    if(match.get(x) == null){
      match.put(x,fruit);
      return true;
    }
    Fruit tmp = match.get(x);
    index++;
    if(fruit.r>tmp.r){
      return LinearMatch(fruit,T[index], match,T,index);
    }else {
      if(LinearMatch(tmp,T[index], match,T,index)){
        match.put(x,fruit);
        return true;
      }
    }
    return false;
  }

  static class Fruit{
    int l;
    int r;
    int v;
    Fruit(int l,int r, int v){
      this.l=l;
      this.r=r;
      this.v=v;
    }
  }

}
class QReader {
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private StringTokenizer tokenizer = new StringTokenizer("");

  private String innerNextLine() {
    try {
      return reader.readLine();
    } catch (IOException e) {
      return null;
    }
  }

  public boolean hasNext() {
    while (!tokenizer.hasMoreTokens()) {
      String nextLine = innerNextLine();
      if (nextLine == null) {
        return false;
      }
      tokenizer = new StringTokenizer(nextLine);
    }
    return true;
  }

  public String nextLine() {
    tokenizer = new StringTokenizer("");
    return innerNextLine();
  }

  public String next() {
    hasNext();
    return tokenizer.nextToken();
  }

  public int nextInt() {
    return Integer.parseInt(next());
  }

  public long nextLong() {
    return Long.parseLong(next());
  }
}
class QWriter implements Closeable {
  private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

  public void print(Object object) {
    try {
      writer.write(object.toString());
    } catch (IOException e) {
      return;
    }
  }

  public void println(Object object) {
    try {
      writer.write(object.toString());
      writer.write("\n");
    } catch (IOException e) {
      return;
    }
  }

  @Override
  public void close() {
    try {
      writer.close();
    } catch (IOException e) {
      return;
    }
  }
}