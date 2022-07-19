import java.util.ArrayList;

/*
 * Слон, ест и ходит по диагонали
 * */
public class Bishop extends Figure {
    public Bishop(int i, int j, boolean isBlack) {
        super(i, j, isBlack);
    }

    @Override
    public ArrayList<Pair> checkEat(ArrayList<Figure> black, ArrayList<Figure> white) {
        ArrayList<Pair> ans = new ArrayList<>();
        ArrayList<Figure> cur = (ArrayList<Figure>) black.clone();
        cur.addAll(white);
        outerloop:
        for (int i = 1; I + i < 8 && J + i < 8; i++) {
            for (Figure figure : cur) {
                if (figure.getJ() == J + i && figure.getI() == I + i) {
                    if (figure.isBlack() != isBlack()) {
                        ans.add(new Pair(I + i, J + i));
                    }
                    break outerloop;
                }
            }
        }
        outerloop:
        for (int i = 1; I - i >= 0 && J - i >= 0; i++) {
            for (Figure figure : cur) {
                if (figure.getJ() == J - i && figure.getI() == I - i) {
                    if (figure.isBlack() != isBlack()) {
                        ans.add(new Pair(I - i, J - i));
                    }
                    break outerloop;
                }
            }
        }
        outerloop:
        for (int i = 1; I + i < 8 && J - i >= 0; i++) {
            for (Figure figure : cur) {
                if (figure.getJ() == J - i && figure.getI() == I + i) {
                    if (isBlack() != figure.isBlack()) {
                        ans.add(new Pair(I + i, J - i));
                    }
                    break outerloop;
                }
            }
        }
        outerloop:
        for (int i = 1; I - i >= 0 && J + i < 8; i++) {
            for (Figure figure : cur) {
                if (figure.getJ() == J + i && figure.getI() == I - i) {
                    if (isBlack() != figure.isBlack()) {
                        ans.add(new Pair(I - i, J + i));
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
        for (int i = 1; I + i < 8 && J + i < 8; i++) {
            for (Figure figure : cur) {
                if (figure.getJ() == J + i && figure.getI() == I + i) {
                    break outerloop;
                }
            }
            ans.add(new Pair(I + i, J + i));
        }
        outerloop:
        for (int i = 1; I - i >= 0 && J - i >= 0; i++) {
            for (Figure figure : cur) {
                if (figure.getJ() == J - i && figure.getI() == I - i) {
                    break outerloop;
                }
            }
            ans.add(new Pair(I - i, J - i));
        }
        outerloop:
        for (int i = 1; I + i < 8 && J - i >= 0; i++) {
            for (Figure figure : cur) {
                if (figure.getJ() == J - i && figure.getI() == I + i) {
                    break outerloop;
                }
            }
            ans.add(new Pair(I + i, J - i));
        }
        outerloop:
        for (int i = 1; I - i >= 0 && J + i < 8; i++) {
            for (Figure figure : cur) {
                if (figure.getJ() == J + i && figure.getI() == I - i) {
                    break outerloop;
                }
            }
            ans.add(new Pair(I - i, J + i));
        }
        return ans;
    }
}