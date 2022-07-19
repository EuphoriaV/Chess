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
        // добавляем все фигуры
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

    // проверка на шах
    public boolean chechShah() {
        ArrayList<Figure> cur1, cur2;
        int kingI = -1, kingJ = -1;
        if (blackTurn) {
            cur1 = black;
            cur2 = white;
        } else {
            cur1 = white;
            cur2 = black;
        }
        for (Figure figure : cur1) {
            if (figure instanceof King) {
                kingI = figure.getI();
                kingJ = figure.getJ();
                break;
            }
        }
        for (Figure figure : cur2) {
            ArrayList<Pair> attacked = figure.checkEat(black, white);
            for (Pair pair : attacked) {
                if (pair.getI() == kingI && pair.getJ() == kingJ) {
                    return true;
                }
            }
        }
        return false;
    }

    // проверка на возможность походить
    public boolean checkGo() {
        ArrayList<Figure> cur1, cur2;
        if (blackTurn) {
            cur1 = black;
            cur2 = white;
        } else {
            cur1 = white;
            cur2 = black;
        }
        for (Figure figure : cur1) {
            int prevI = figure.getI(), prevJ = figure.getJ();
            ArrayList<Pair> goTo = figure.checkGo(black, white);
            for (Pair pair : goTo) {
                figure.setI(pair.getI());
                figure.setJ(pair.getJ());
                if (!chechShah()) {
                    figure.setI(prevI);
                    figure.setJ(prevJ);
                    return true;
                }
                figure.setI(prevI);
                figure.setJ(prevJ);
            }
        }
        return false;
    }

    public void go(Figure figureGo, int goI, int goJ) {
        ArrayList<Figure> cur1;
        if (blackTurn) {
            cur1 = black;
        } else {
            cur1 = white;
        }
        figureGo.setI(goI);
        figureGo.setJ(goJ);
        // рокировка
        if (figureGo instanceof King) {
            if (figureGo.isBlack()) {
                if (goI == 0 && goJ == 2) {
                    for (Figure figure : cur1) {
                        if (figure.getI() == 0 && figure.getJ() == 0 && figure instanceof Rook) {
                            figure.setJ(3);
                            break;
                        }
                    }
                }
                if (goI == 0 && goJ == 6) {
                    for (Figure figure : cur1) {
                        if (figure.getI() == 0 && figure.getJ() == 7 && figure instanceof Rook) {
                            figure.setJ(5);
                            break;
                        }
                    }
                }
            } else {
                if (goI == 7 && goJ == 2) {
                    for (Figure figure : cur1) {
                        if (figure.getI() == 7 && figure.getJ() == 0 && figure instanceof Rook) {
                            figure.setJ(3);
                            break;
                        }
                    }
                }
                if (goI == 7 && goJ == 6) {
                    for (Figure figure : cur1) {
                        if (figure.getI() == 7 && figure.getJ() == 7 && figure instanceof Rook) {
                            figure.setJ(5);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void eat(Figure figureEat, int eatI, int eatJ) {
        ArrayList<Figure> cur1;
        if (blackTurn) {
            cur1 = white;
        } else {
            cur1 = black;
        }
        figureEat.setI(eatI);
        figureEat.setJ(eatJ);
        for (Figure figure : cur1) {
            if (figure.getJ() == eatJ && figure.getI() == eatI) {
                cur1.remove(figure);
                break;
            }
        }
    }

    public void act() {
        //проверка
        if (!checkGo() && chechShah()) {
            System.out.println("mat");
            return;
        } else if (!checkGo() && !chechShah()) {
            System.out.println("nat");
            return;
        } else if (chechShah()) {
            System.out.println("wax");
        }
        // 1 лист текущие фигуры, 2 - вражеские
        ArrayList<Figure> cur1, cur2;
        if (blackTurn) {
            cur1 = black;
            cur2 = white;
        } else {
            cur1 = white;
            cur2 = black;
        }
        // выбрать фигуру для хода
        for (Figure figure : cur1) {
            if (figure.getJ() == curJ && figure.getI() == curI) {
                currentFigure = figure;
                return;
            }
        }
        if (currentFigure != null) {
            for (Pair pair : go) {
                if (pair.getJ() == curJ && pair.getI() == curI) {
                    int prevI = currentFigure.getI(), prevJ = currentFigure.getJ();
                    go(currentFigure, curI, curJ);
                    // если шах, то отмена
                    if (chechShah()) {
                        currentFigure.setI(prevI);
                        currentFigure.setJ(prevJ);
                    } else {
                        blackTurn = !blackTurn;
                    }
                    // пешка дошла до конца
                    if (currentFigure instanceof Pawn) {
                        if (currentFigure.isBlack() && currentFigure.getI() == 7) {
                            onSwitch = true;
                        } else if (!currentFigure.isBlack() && currentFigure.getI() == 0) {
                            onSwitch = true;
                        }
                    }
                    currentFigure = null;
                    return;
                }
            }
            for (Pair pair : eat) {
                if (pair.getJ() == curJ && pair.getI() == curI) {
                    int prevI = currentFigure.getI(), prevJ = currentFigure.getJ();
                    Figure eaten = null;
                    for (Figure figure : cur2) {
                        if (figure.getJ() == curJ && figure.getI() == curI) {
                            eaten = figure;
                        }
                    }
                    eat(currentFigure, curI, curJ);
                    // если шах то отмена
                    if (chechShah()) {
                        currentFigure.setI(prevI);
                        currentFigure.setJ(prevJ);
                        cur2.add(eaten);
                    } else {
                        blackTurn = !blackTurn;
                    }
                    // если пешка дошла до конца
                    if (currentFigure instanceof Pawn) {
                        if (currentFigure.isBlack() && currentFigure.getI() == 7) {
                            onSwitch = true;
                        } else if (!currentFigure.isBlack() && currentFigure.getI() == 0) {
                            onSwitch = true;
                        }
                    }
                    currentFigure = null;
                    return;
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

    // замена пешки
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
