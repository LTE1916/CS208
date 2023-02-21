import java.util.Scanner;

public class Pipei {
    public static void main(String[] args) {
        
      //  System.out.println("请输入人数：");
        Scanner scanner=new Scanner(System.in);
        int NUM=scanner.nextInt();
        String A=scanner.nextLine();
        String B=scanner.nextLine();
        People man[]=new People[NUM];
        People lady[]=new People[NUM];
        
        //初始化男士心中女士的排名
        for(int i=0;i<NUM;i++) {
            man[i]=new People();
            int  a[]=new int[NUM];                                      //创建数组存放喜欢异性的排名
            for(int j=0;j<NUM;j++) {
             //   System.out.println("请输入第"+i+"名男士喜欢的第"+j+"名女士：");
                int lover=scanner.nextInt();
                a[j]=lover;
            }
            man[i].setRank(a);
        }
        //初始化女士心中男士的排名
        for(int i=0;i<NUM;i++) {
            lady[i]=new People();
            int a[]=new int[NUM];                                       //创建数组存放喜欢异性的排名
            for(int j=0;j<NUM;j++) {
         //       System.out.println("请输入第"+i+"名女士喜欢的第"+j+1+"名男士：");
                a[j]=scanner.nextInt();
            }
            lady[i].setRank(a);
        }
        boolean flag;
        while (true) {
            flag=true;
            //男士表白
            for(int i=0;i<NUM;i++) {
                if(man[i].getYuehui()==false) {
                    flag=false;
                    int location=man[i].getRank()[man[i].getBetter()];
                    man[i].setBetter(man[i].getBetter()+1);
                    if(lady[location].getYuehui()==false) {
                        man[i].setYuehui(true);
                        lady[location].setYuehui(true);
                        man[i].setPresent(location);
                        lady[location].setPresent(i);
                    }else if (lady[location].getYuehui()==true) {
                        int before = 0,latter = 0;
                        for(int j=0;j<NUM;j++) {
                            if(lady[location].getRank()[j]==i) {
                                latter=j;
                            }
                            if(lady[location].getRank()[j]==lady[location].getPresent()) {
                                before=j;
                            }
                        }
                        if(latter>before) {     //喜欢原来
                        
                        }
                        if(before>latter) {     //喜欢小三
                            man[lady[location].getPresent()].setPresent(100);
                            man[lady[location].getPresent()].setYuehui(false);
                            lady[location].setPresent(i);
                            man[i].setPresent(location);
                            man[i].setYuehui(true);
                        }
                    }
                }
            }
            if(flag) {
                break;
            }
        }
        scanner.close();
        for(int i=0;i<NUM;i++) {
            System.out.println(i+" "+man[i].getPresent());
        }
    }
}
