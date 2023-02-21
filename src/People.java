
public class People {
    private Boolean yuehui;
    private String name;
    private int[] rank;
    private int present;
    private int better;
    
    public People() {
        yuehui=false;
        better=0;
        present=100;
    }
    
    public Boolean getYuehui() {
        return yuehui;
    }
    
    public void setYuehui(Boolean yuehui) {
        this.yuehui = yuehui;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    
    
    public int[] getRank() {
        return rank;
    }
    
    public void setRank(int[] a) {
        this.rank = a;
    }
    
    public int getPresent() {
        return present;
    }
    
    public void setPresent(int present) {
        this.present = present;
    }
    
    public int getBetter() {
        return better;
    }
    
    public void setBetter(int better) {
        this.better = better;
    }
}
