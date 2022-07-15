import javax.swing.*;
import java.util.ArrayList;

public class Game {
    private boolean blackTurn = false;
    private boolean onSwitch = false;
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
                    if (currentFigure instanceof King) {
                        if (currentFigure.isBlack()) {
                            if (curI == 0 && curJ == 2) {
                                for (Figure figure : cur1) {
                                    if (figure.getI() == 0 && figure.getJ() == 0 && figure instanceof Rook) {
                                        figure.setJ(3);
                                        break;
                                    }
                                }
                            }
                            if (curI == 0 && curJ == 6) {
                                for (Figure figure : cur1) {
                                    if (figure.getI() == 0 && figure.getJ() == 7 && figure instanceof Rook) {
                                        figure.setJ(5);
                                        break;
                                    }
                                }
                            }
                        } else {
                            if (curI == 7 && curJ == 2) {
                                for (Figure figure : cur1) {
                                    if (figure.getI() == 7 && figure.getJ() == 0 && figure instanceof Rook) {
                                        figure.setJ(3);
                                        break;
                                    }
                                }
                            }
                            if (curI == 7 && curJ == 6) {
                                for (Figure figure : cur1) {
                                    if (figure.getI() == 7 && figure.getJ() == 7 && figure instanceof Rook) {
                                        figure.setJ(5);
                                        break;
                                    }
                                }
                            }
                        }
                    } else if (currentFigure instanceof Pawn) {
                        if (currentFigure.isBlack() && currentFigure.getI() == 7) {
                            onSwitch = true;
                        } else if (!currentFigure.isBlack() && currentFigure.getI() == 0) {
                            onSwitch = true;
                        }
                    }
                    blackTurn = !blackTurn;
                    currentFigure = null;
                    return;
                }
            }
            for (Pair pair : eat) {
                if (pair.getJ() == curJ && pair.getI() == curI) {
                    currentFigure.setI(curI);
                    currentFigure.setJ(curJ);
                    if (currentFigure instanceof Pawn) {
                        if (currentFigure.isBlack() && currentFigure.getI() == 7) {
                            onSwitch = true;
                        } else if (!currentFigure.isBlack() && currentFigure.getI() == 0) {
                            onSwitch = true;
                        }
                    }
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

    public boolean isBlackTurn() {
        return blackTurn;
    }

    public boolean isOnSwitch() {
        return onSwitch;
    }

    public void switchPawn(int num) {
        onSwitch = false;
        int i = -1, j = -1;
        if (isBlackTurn()) {
            for (Figure figure : white) {
                if (figure instanceof Pawn && figure.getI() == 0) {
                    i = figure.getI();
                    j = figure.getJ();
                    white.remove(figure);
                    break;
                }
            }
            switch (num) {
                case 0 -> white.add(new Queen(i, j, false));
                case 1 -> white.add(new Rook(i, j, false));
                case 2 -> white.add(new Bishop(i, j, false));
                case 3 -> white.add(new Knight(i, j, false));
            }
        } else {
            for (Figure figure : black) {
                if (figure instanceof Pawn && figure.getI() == 7) {
                    i = figure.getI();
                    j = figure.getJ();
                    black.remove(figure);
                    break;
                }
            }
            switch (num) {
                case 0 -> black.add(new Queen(i, j, true));
                case 1 -> black.add(new Rook(i, j, true));
                case 2 -> black.add(new Bishop(i, j, true));
                case 3 -> black.add(new Knight(i, j, true));
            }
        }
    }
}
