import java.util.ArrayList;

public class Rook extends Figure {

    public Rook(int i, int j, boolean isBlack) {
        super(i, j, isBlack);
    }

    @Override
    public ArrayList<Pair> checkEat(ArrayList<Figure> black, ArrayList<Figure> white) {
        ArrayList<Pair> ans = new ArrayList<>();
        ArrayList<Figure> cur = (ArrayList<Figure>) black.clone();
        cur.addAll(white);
        outerloop:
        for (int i = J + 1; i < 8; i++) {
            for (Figure figure : cur) {
                if (figure.getI() == I && figure.getJ() == i) {
                    if (isBlack != figure.isBlack()) {
                        ans.add(new Pair(figure.getI(), figure.getJ()));
                    }
                    break outerloop;
                }
            }
        }
        outerloop:
        for (int i = J - 1; i >= 0; i--) {
            for (Figure figure : cur) {
                if (figure.getI() == I && figure.getJ() == i) {
                    if (isBlack != figure.isBlack()) {
                        ans.add(new Pair(figure.getI(), figure.getJ()));
                    }
                    break outerloop;
                }
            }
        }
        outerloop:
        for (int i = I + 1; i < 8; i++) {
            for (Figure figure : cur) {
                if (figure.getI() == i && figure.getJ() == J) {
                    if (isBlack != figure.isBlack()) {
                        ans.add(new Pair(figure.getI(), figure.getJ()));
                    }
                    break outerloop;
                }
            }
        }
        outerloop:
        for (int i = I - 1; i >= 0; i--) {
            for (Figure figure : cur) {
                if (figure.getI() == i && figure.getJ() == J) {
                    if (isBlack != figure.isBlack()) {
                        ans.add(new Pair(figure.getI(), figure.getJ()));
                    }
                    break outerloop;
                }
            }
        }
        return ans;
    }

    @Override
    public ArrayList<Pair> checkGo(ArrayList<Figure> black, ArrayList<Figure> white) {
        ArrayList<Pair> ans = new ArrayList<>();
        ArrayList<Figure> cur = (ArrayList<Figure>) black.clone();
        cur.addAll(white);
        outerloop:
        for (int i = J + 1; i < 8; i++) {
            for (Figure figure : cur) {
                if (figure.getI() == I && figure.getJ() == i) {
                    break outerloop;
                }
            }
            ans.add(new Pair(I, i));
        }
        outerloop:
        for (int i = J - 1; i >= 0; i--) {
            for (Figure figure : cur) {
                if (figure.getI() == I && figure.getJ() == i) {
                    break outerloop;
                }
            }
            ans.add(new Pair(I, i));
        }
        outerloop:
        for (int i = I + 1; i < 8; i++) {
            for (Figure figure : cur) {
                if (figure.getI() == i && figure.getJ() == J) {
                    break outerloop;
                }
            }
            ans.add(new Pair(i, J));
        }
        outerloop:
        for (int i = I - 1; i >= 0; i--) {
            for (Figure figure : cur) {
                if (figure.getI() == i && figure.getJ() == J) {
                    break outerloop;
                }
            }
            ans.add(new Pair(i, J));
        }
        return ans;
    }
}
