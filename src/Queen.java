import java.util.ArrayList;

/*
 * Ферзь, ходит по диагонали, горизонтали и вертикали
 * */
public class Queen extends Figure {
    public Queen(int i, int j, boolean isBlack) {
        super(i, j, isBlack);
    }

    @Override
    public ArrayList<Pair> checkEat(ArrayList<Figure> black, ArrayList<Figure> white) {
        ArrayList<Pair> ans = new Bishop(getI(), getJ(), isBlack()).checkEat(black, white);
        ans.addAll(new Rook(getI(), getJ(), isBlack()).checkEat(black, white));
        return ans;
    }

    @Override
    public ArrayList<Pair> checkGo(ArrayList<Figure> black, ArrayList<Figure> white) {
        ArrayList<Pair> ans = new Bishop(getI(), getJ(), isBlack()).checkGo(black, white);
        ans.addAll(new Rook(getI(), getJ(), isBlack()).checkGo(black, white));
        return ans;
    }
}