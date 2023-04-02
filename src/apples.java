import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class apples {
  public static void main(String[]args){
    QReader in = new QReader();
    QWriter out = new QWriter();
    int n = in.nextInt();
    int m = in.nextInt();
    int [] baskets = new int[m];
    long total = 0;
    long capacity = 0;
    for (int i = 0; i < m; i++) {
      int cur = in.nextInt();
      capacity+=cur;
      baskets[i]= cur;
    }
    baskets=Arrays.stream(baskets).sorted().toArray();
    int [] apples = new int[n];
    for (int i = 0; i < n; i++) {
      int cur = in.nextInt();
      total+=cur;
      apples[i]=cur;
    }
    long ans = 0;
    if(capacity<=n){
      int far =(int) capacity-1;
      for (int i = m-1; i >=0; i--) {
        int volume = baskets[i];
        ans+=apples[far];
        far-=volume;
      }
    }else {
      int far = n-1;
      while (far>=0){
        int volume = baskets[m-1];
        m--;
        ans+=apples[far];
        far-=volume;
      }
    }
    out.println(ans*2);
    out.close();
  }

}

