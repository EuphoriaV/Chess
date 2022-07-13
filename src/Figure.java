import java.util.ArrayList;

public abstract class Figure {
    protected int I, J;
    protected final boolean isBlack;

    public Figure(int i, int j, boolean isBlack) {
        I = i;
        J = j;
        this.isBlack = isBlack;
    }

    public int getI() {
        return I;
    }

    public void setI(int i) {
        I = i;
    }

    public int getJ() {
        return J;
    }

    public void setJ(int j) {
        J = j;
    }

    public boolean isBlack() {
        return isBlack;
    }
    public abstract ArrayList<Pair> checkEat(ArrayList<Figure> black, ArrayList<Figure> white);
    public abstract ArrayList<Pair> checkGo(ArrayList<Figure> black, ArrayList<Figure> white);
}
