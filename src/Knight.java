import java.util.ArrayList;

/*
 * Конь, ходит и ест буквой Г
 * */
public class Knight extends Figure {
    public Knight(int i, int j, boolean isBlack) {
        super(i, j, isBlack);
    }

    @Override
    public ArrayList<Pair> checkEat(ArrayList<Figure> black, ArrayList<Figure> white) {
        ArrayList<Pair> ans = new ArrayList<>();
        ArrayList<Figure> cur = (ArrayList<Figure>) black.clone();
        cur.addAll(white);
        for (Figure figure : cur) {
            if (Math.abs(getI() - figure.getI()) * Math.abs(getJ() - figure.getJ()) == 2 && isBlack() != figure.isBlack()) {
                ans.add(new Pair(figure.getI(), figure.getJ()));
            }
        }
        return ans;
    }

    @Override
    public ArrayList<Pair> checkGo(ArrayList<Figure> black, ArrayList<Figure> white) {
        ArrayList<Pair> ans = new ArrayList<>();
        ArrayList<Figure> cur = (ArrayList<Figure>) black.clone();
        cur.addAll(white);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boolean good = true;
                for (Figure figure : cur) {
                    if (figure.getI() == i && figure.getJ() == j) {
                        good = false;
                        break;
                    }
                }
                if (good && Math.abs(getI() - i) * Math.abs(getJ() - j) == 2) {
                    ans.add(new Pair(i, j));
                }
            }
        }
        return ans;
    }
}