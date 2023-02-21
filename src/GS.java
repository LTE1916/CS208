import java.util.*;

public class GS {
    
    public static class Man {
        String name;
        int proposeIndex=0;//
        boolean matched=false;
        
        public Man(String name){
            this.name=name;
        }
        
    }
    
    public static void main(String[]args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Map<String, Integer> man = new HashMap<>();
        Map<String, Integer> woman = new HashMap<>();
        String[] manName = new String[N];
        String[] womanName = new String[N];
        int[][] manPreferArray = new int[N][N];
        //    int[][] womanPreferArray=new int[N][N];
        int[][] womanPreferArrayInverse = new int[N][N];
        int[] manMatched = new int[N];
        int[] womanMatched = new int[N];
        int[] proposeIndex = new int[N];
     
        for (int i = 0; i < N; i++) {
            womanMatched[i] = -1;
            //      manMatched[i]=-1;
    
        }
        Queue<Man> freeMan = new LinkedList<Man>();
    
        for (int i = 0; i < N; i++) {
            String str = in.next();
            man.put(str, i);
            manName[i] = str;
            freeMan.offer(new Man(str));
        }
        for (int i = 0; i < N; i++) {
            String str = in.next();
            woman.put(str, i);
            womanName[i] = str;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                manPreferArray[i][j] = woman.get(in.next());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //    womanPreferArray[i][j]=man.get(in.next());
                womanPreferArrayInverse[i][man.get(in.next())] = j;
            }
        }
    
        while (!freeMan.isEmpty()) {
            Man cur = freeMan.poll();
            if (proposeIndex[man.get(cur.name)] <= N - 1) {
                int curTarget = manPreferArray[man.get(cur.name)][proposeIndex[man.get(cur.name)]];
                if (womanMatched[curTarget] == -1) {
                    womanMatched[curTarget] = man.get(cur.name);//woman engaged with man
                    cur.matched = true;
                } else if (womanPreferArrayInverse[curTarget][womanMatched[curTarget]] >
                        womanPreferArrayInverse[curTarget][man.get(cur.name)]) {
                    //
                    Man abandonedMan = new Man(manName[womanMatched[curTarget]]);
                    proposeIndex[womanMatched[curTarget]]++;
                    //  abandonedMan.proposeIndex=proposeIndex[man.get(womanName[curTarget])];
                    freeMan.offer(abandonedMan);
                    womanMatched[curTarget] = man.get(cur.name);
                    cur.matched = true;
                } else {
                    proposeIndex[man.get(cur.name)]++;//reject;
                    freeMan.offer(cur);
                }
            }
        
        }
        //if order by woman
//        for (int i = 0; i < womanMatched.length; i++) {
//            System.out.println(manName[womanMatched[i]]+" "+womanName[i]);
//        }
    
    
        //if order by man
        for (int i = 0; i < womanMatched.length; i++) {
            manMatched[womanMatched[i]] = i;
        }
        for (int i = 0; i < manMatched.length; i++) {
            System.out.println(manName[i] + " " + womanName[manMatched[i]]);
        }
    
    
        //check
//        boolean flag=true;
//        for (int i = 0; i < womanMatched.length; i++) {
//            int curMan=womanMatched[i];
//            int j=0;
//            while (manPreferArray[curMan][j]!=i){
//                int curWoman=manPreferArray[curMan][j];
//                if (womanPreferArrayInverse[curWoman][curMan]<womanPreferArrayInverse[curWoman][womanMatched[curWoman]]){
//                    flag=false;
//                    break;
//                }else j++;
//            }
//        }
//        System.out.println(flag);
//    }
    }
}
