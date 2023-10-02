package pair;

public class Pair<Left,Right> {
    private Left l;
    private Right r;
    public Pair(Left l, Right r){
        this.l = l;
        this.r = r;
    }
    public Left getL(){ return l; }
    public Right getR(){ return r; }
    public void setL(Left l){ this.l = l; }
    public void setR(Right r){ this.r = r; }
}