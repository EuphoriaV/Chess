import java.util.ArrayList;

public class Pawn extends Figure {
    public Pawn(int i, int j, boolean isBlack) {
        super(i, j, isBlack);
    }

    @Override
    public ArrayList<Pair> checkEat(ArrayList<Figure> black, ArrayList<Figure> white) {
        ArrayList<Pair> ans = new ArrayList<>();
        ArrayList<Figure> cur = (ArrayList<Figure>) black.clone();
        cur.addAll(white);
        for (Figure figure : cur) {
            if (isBlack && !figure.isBlack() && figure.getI() - I == 1 && Math.abs(figure.getJ() - J) == 1) {
                ans.add(new Pair(figure.getI(), figure.getJ()));
            } else if (!isBlack && figure.isBlack() && figure.getI() - I == -1 && Math.abs(figure.getJ() - J) == 1) {
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
                if (good) {
                    if (isBlack && ((i - I == 1 && j == J) || ((i - I == 2 && j == J && I == 1)))) {
                        ans.add(new Pair(i, j));
                    } else if (!isBlack && ((i - I == -1 && j == J) || ((i - I == -2 && j == J && I == 6)))) {
                        ans.add(new Pair(i, j));
                    }
                }
            }
        }
        return ans;
    }
}
