import java.util.ArrayList;

public class Bishop extends Figure{
    public Bishop(int i, int j, boolean isBlack) {
        super(i, j, isBlack);
    }

    @Override
    public ArrayList<Pair> checkEat(ArrayList<Figure> black, ArrayList<Figure> white) {
        return null;
    }

    @Override
    public ArrayList<Pair> checkGo(ArrayList<Figure> black, ArrayList<Figure> white) {
        return null;
    }
}