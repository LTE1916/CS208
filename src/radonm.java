import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class radonm {
    public static void main(String[]args) {
        Random random = new Random();
        for (int k = 0; k < 100000; k++) {
            int N = random.nextInt(100);
            System.out.println(N);
            for (int i = 0; i < N; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
            for (int i = 0; i < N; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
            for (int i = 0; i < N; i++) {
                ArrayList<Integer> arrayList = new ArrayList<>();
                int j = 0;
                while (j < N) {
                    int cur = random.nextInt(N);
                    if (!arrayList.contains(cur)) {
                        System.out.print(cur + " ");
                        arrayList.add(cur);
                        j++;
                    }
                }
                System.out.println();
            }
            for (int i = 0; i < N; i++) {
                ArrayList<Integer> arrayList = new ArrayList<>();
                int j = 0;
                while (j < N) {
                    int cur = random.nextInt(N);
                    if (!arrayList.contains(cur)) {
                        System.out.print(cur + " ");
                        arrayList.add(cur);
                        j++;
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
