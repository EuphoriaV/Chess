import java.util.ArrayList;

public class Knight extends Figure{
    public Knight(int i, int j, boolean isBlack) {
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