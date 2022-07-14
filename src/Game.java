import javax.swing.*;
import java.util.ArrayList;

public class Game {
    private boolean blackTurn = false;
    private int curI = -1, curJ = -1;
    private Figure currentFigure;
    private final ArrayList<Figure> black = new ArrayList<>();
    private final ArrayList<Figure> white = new ArrayList<>();
    private ArrayList<Pair> eat = new ArrayList<>();
    private ArrayList<Pair> go = new ArrayList<>();

    public Game() {
        black.add(new Rook(0, 0, true));
        black.add(new Knight(0, 1, true));
        black.add(new Bishop(0, 2, true));
        black.add(new Queen(0, 3, true));
        black.add(new King(0, 4, true));
        black.add(new Bishop(0, 5, true));
        black.add(new Knight(0, 6, true));
        black.add(new Rook(0, 7, true));
        for (int i = 0; i < 8; i++) {
            black.add(new Pawn(1, i, true));
        }
        white.add(new Rook(7, 0, false));
        white.add(new Knight(7, 1, false));
        white.add(new Bishop(7, 2, false));
        white.add(new Queen(7, 3, false));
        white.add(new King(7, 4, false));
        white.add(new Bishop(7, 5, false));
        white.add(new Knight(7, 6, false));
        white.add(new Rook(7, 7, false));
        for (int i = 0; i < 8; i++) {
            white.add(new Pawn(6, i, false));
        }
        Timer timer = new Timer(5, null);
        timer.addActionListener(e -> {
            if (currentFigure != null) {
                eat = currentFigure.checkEat(black, white);
                go = currentFigure.checkGo(black, white);
            } else {
                eat = null;
                go = null;
            }
        });
        timer.start();
    }

    public void act() {
        ArrayList<Figure> cur1, cur2;
        if (blackTurn) {
            cur1 = black;
            cur2 = white;
        } else {
            cur1 = white;
            cur2 = black;
        }
        for (Figure figure : cur1) {
            if (figure.getJ() == curJ && figure.getI() == curI) {
                currentFigure = figure;
                return;
            }
        }
        if (currentFigure != null) {
            for (Pair pair : go) {
                if (pair.getJ() == curJ && pair.getI() == curI) {
                    currentFigure.setI(curI);
                    currentFigure.setJ(curJ);
                    blackTurn = !blackTurn;
                    currentFigure = null;
                    return;
                }
            }
            for (Pair pair : eat) {
                if (pair.getJ() == curJ && pair.getI() == curI) {
                    currentFigure.setI(curI);
                    currentFigure.setJ(curJ);
                    blackTurn = !blackTurn;
                    currentFigure = null;
                    for (Figure figure : cur2) {
                        if (figure.getJ() == curJ && figure.getI() == curI) {
                            cur2.remove(figure);
                            return;
                        }
                    }
                }
            }
        }
    }

    public void setCurI(int curI) {
        this.curI = curI;
    }

    public void setCurJ(int curJ) {
        this.curJ = curJ;
    }

    public Figure getCurrentFigure() {
        return currentFigure;
    }

    public ArrayList<Figure> getBlack() {
        return black;
    }

    public ArrayList<Figure> getWhite() {
        return white;
    }

    public ArrayList<Pair> getEat() {
        return eat;
    }

    public ArrayList<Pair> getGo() {
        return go;
    }

}
