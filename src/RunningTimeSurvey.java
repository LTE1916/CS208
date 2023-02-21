import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.io.File;
import java.lang.reflect.Method;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
public class RunningTimeSurvey {
    //             task name            function name             run times upper
    static String[][] taskList = {
            {  "LinearTimeTest",        "linearTime",             "100000000"},
            {  "LinearTimeTest",        "linearTimeCollections",  "10000000"},
            { "NlognTimeTest",       "NlognTime",              "1000000"},
              { "QuadraticTimeTest",   "QuadraticTime",          "100000"},
              { "CubicTimeTest",       "CubicTime",              "1000"},
              { "ExponentialTimeTest", "ExponentialTime",        "29"},
              { "FactorialTimeTest",   "FactorialTime",          "12"}
    
    };
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String osName = System.getProperty("os.name");
        System.out.println(osName);
        try {
            File xlsFile = new File("RunningTimeSurvey.xls");
            // Create a workbook
            WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
            // Create a worksheet
            WritableSheet sheet = workbook.createSheet("RunningTime", 0);
            // the first row
            int max_upper=0,max_giant_upper=0;
            for(String[]taskInfo:taskList)
            {
                max_upper=Math.max(max_upper,Integer.parseInt(taskInfo[2]));
                if (taskInfo[0].equals("ExponentialTimeTest") || taskInfo[0].equals("FactorialTimeTest")) {
                    max_giant_upper=Math.max(max_giant_upper,Integer.parseInt(taskInfo[2]));
                }
            }
            for (int j = 1, n = 10; n <= max_upper; j++,n*=10) {
                sheet.addCell(new Label(j + 1, 0, "n = " + n));
            }
            int next_row=1;
            for (int i = 0; i < taskList.length; i++) {
                String[] taskInfo = taskList[i];
                if (taskInfo[0].equals("ExponentialTimeTest") || taskInfo[0].equals("FactorialTimeTest")) {
                    continue;
                }
                Class<?> me = Class.forName(Thread.currentThread().getStackTrace()[1].getClassName());
                Method method = me.getMethod(taskInfo[1], int.class);
                int upper = Integer.parseInt(taskInfo[2]);
                sheet.addCell(new Label(0, i + 1, taskInfo[0]));
                sheet.addCell(new Label(1, i + 1, taskInfo[1]));
                next_row=i+1;
                for (int j = 1, n = 1; Math.pow(10, j) <= upper; j++) {
                    n = 10 * n;
                    Long time = (Long) method.invoke(null, n);
                    // add data to sheet
                    sheet.addCell(new Label(j + 1, i + 1, time.toString()));
                }
            }
            ++next_row;
            for (int j = 1, n = 10; n <= max_giant_upper; j++,++n) {
                sheet.addCell(new Label(j + 1, next_row, "n = " + n));
            }
            ++next_row;
            for(String[]taskInfo:taskList)
            {
                if (!(taskInfo[0].equals("ExponentialTimeTest") || taskInfo[0].equals("FactorialTimeTest"))) {
                    continue;
                }
                Class<?> me = Class.forName(Thread.currentThread().getStackTrace()[1].getClassName());
                Method method = me.getMethod(taskInfo[1], int.class);
                int upper = Integer.parseInt(taskInfo[2]);
                sheet.addCell(new Label(0, next_row, taskInfo[0]));
                sheet.addCell(new Label(1, next_row, taskInfo[1]));
                for (int j = 1, n = 10; n <= upper; j++) {
                    ++n;
                    Long time = (Long) method.invoke(null, n);
                    // add data to sheet
                    sheet.addCell(new Label(j + 1, next_row, time.toString()));
                }
                ++next_row;
            }
            workbook.write();
            workbook.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static long linearTimeCollections(int n) {
        ArrayList<Long> arrayList = new ArrayList<Long>(n);
        generateArrayList(n, arrayList);
        long timeStart = System.currentTimeMillis();
        getMax(n, arrayList);
        long timeEnd = System.currentTimeMillis();
        long timeCost = timeEnd - timeStart;
        return timeCost;
    }
    public static long linearTime(int n) {
        long[] list = new long[n];
        generateList(n, list);//randomly
        long timeStart = System.currentTimeMillis();
        getMax(n, list);
        long timeEnd = System.currentTimeMillis();
        return timeEnd - timeStart;
    }
    public static long getMax(long n, long[] list) {
        long max = list[0];
        for (int i = 1; i < n; i++) {
            if (list[i] > max) {
                max = list[i];
            }
        }
        return max;
    }
    public static void generateList(int n, long[] list) {
        for (int i = 0; i < n; i++) {
            list[i] = i;
        }
        // shuffle
        Random rnd = new Random();
        for (int i = list.length; i > 1; i--) {
            int j = rnd.nextInt(i);
            long temp = list[j];
            list[j] = list[i - 1];
            list[i - 1] = temp;
        }
    }
    public static void generateArrayList(int n, ArrayList<Long> arrayList) {
        for (long i = 0; i < n; i++) {
            arrayList.add(i);
        }
        // shuffle
        Collections.shuffle(arrayList);
    }
    public static long getMax(long n, ArrayList<Long> arrayList) {
        long max = arrayList.get(0);
        for (int i = 1; i < n; i++) {
            if (arrayList.get(i) > max) {
                max = arrayList.get(i);
            }
        }
        return max;
    }
    public static long NlognTime(int n) {
        int[]array=new int[n];
        generateList(n,array);
        int[]tmp=new int[n];
        //TODO:generate your test input data here
        long timeStart = System.currentTimeMillis();
        mergeSort(array,0, array.length-1, tmp);
        //TODO: write an algorithm
        long timeEnd = System.currentTimeMillis();
        long timeCost = timeEnd - timeStart;
        return timeCost;
    }
    public static void generateList(int n, int[] list) {
        for (int i = 0; i < n; i++) {
            list[i] = i;
        }
        // shuffle
        Random rnd = new Random();
        for (int i = list.length; i > 1; i--) {
            int j = rnd.nextInt(i);
            int temp = list[j];
            list[j] = list[i - 1];
            list[i - 1] = temp;
        }
    }
    public static void merge(int[] arr,int low,int mid,int high,int[] tmp){
        int i = 0;
        int j = low,k = mid+1;  //左边序列和右边序列起始索引
        while(j <= mid && k <= high){
            if(arr[j] < arr[k]){
                tmp[i++] = arr[j++];
            }else{
                tmp[i++] = arr[k++];
            }
        }
        //若左边序列还有剩余，则将其全部拷贝进tmp[]中
        while(j <= mid){
            tmp[i++] = arr[j++];
        }
        
        while(k <= high){
            tmp[i++] = arr[k++];
        }
        
        for(int t=0;t<i;t++){
            arr[low+t] = tmp[t];
        }
    }
    public static void mergeSort(int[] arr,int low,int high,int[] tmp){
        if(low<high){
            int mid = (low+high)/2;
            mergeSort(arr,low,mid,tmp); //对左边序列进行归并排序
            mergeSort(arr,mid+1,high,tmp);  //对右边序列进行归并排序
            merge(arr,low,mid,high,tmp);    //合并两个有序序列
        }
    }
    
    public static long QuadraticTime(int n) {
        int []array=new int[n];
        generateList(n,array);
        //TODO:generate your test input data here
        long timeStart = System.currentTimeMillis();
        bubble(array);
        //TODO: write an algorithm
        long timeEnd = System.currentTimeMillis();
        long timeCost = timeEnd - timeStart;
        return timeCost;
    }
    public static void bubble(int[]array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if(array[i]>array[j]){
                    int tmp= array[i];
                    array[i]=array[j];
                    array[j]=tmp;
                }
            }
        }
    }
    
    public static long CubicTime(int n) {
        int[][]arr=new int[n][n];
        Random random=new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j]= random.nextInt();
            }
        }
        //TODO:generate your test input data here
        long timeStart = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            bubble(arr[i]);
        }
        //TODO: write an algorithm
        long timeEnd = System.currentTimeMillis();
        long timeCost = timeEnd - timeStart;
        return timeCost;
    }
    
    public static long ExponentialTime(int n) {
        //TODO:generate your test input data here
        int []arr=new int[n];
        generateList(n,arr);
        long timeStart = System.currentTimeMillis();
        int ans=fib(n);
        generateAllBinaryStrings(n, arr, 0);
        //TODO: write an algorithm
        long timeEnd = System.currentTimeMillis();
        long timeCost = timeEnd - timeStart;
        return timeCost;
    }
    public static void generateAllBinaryStrings(int n, int arr[], int i) {
        if (i == n)
        {
            return;
        }
        
        arr[i] = 0;
        generateAllBinaryStrings(n, arr, i + 1);
        
        arr[i] = 1;
        generateAllBinaryStrings(n, arr, i + 1);
    }
    public static int fib(int n){
        if(n<=1){
            return n ;
        }else return fib(n-1)+fib(n-2)+fib(n-3)+fib(n-4);
    }
    
    public static long FactorialTime(int n) {
        long ans=0;
        //TODO:generate your test input data here
        long timeStart = System.currentTimeMillis();
        ans = anInt(n);
        //TODO: write an algorithm
        long timeEnd = System.currentTimeMillis();
        long timeCost = timeEnd - timeStart;
        return timeCost;
    }
    public static long anInt(long n){
        if(n<1){
            return 1;
        }
        long cnt=0;
        for (int i = 0; i < n; i++) {
        cnt+=anInt(n-1);
        }
        return cnt;
    }
}



