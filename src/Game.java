import javax.swing.*;
import java.util.ArrayList;

public class Game {
    private int result = 0;
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
            if (result != 0) {
                timer.stop();
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
        boolean ans = false;
        ArrayList<Figure> cur1, cur2;
        if (blackTurn) {
            cur1 = black;
            cur2 = white;
        } else {
            cur1 = white;
            cur2 = black;
        }
        //можно ли спастись от мата ходом
        for (Figure figure : cur1) {
            ArrayList<Pair> goTo = figure.checkGo(black, white);
            int prevI = figure.getI(), prevJ = figure.getJ();
            for (Pair pair : goTo) {
                go(figure, pair.getI(), pair.getJ());
                if (!chechShah()) {
                    ans = true;
                }
                if (figure instanceof King && prevJ == 4 && ((figure.isBlack() && prevI == 0) || (!figure.isBlack() && prevI == 7))) {
                    if (figure.getJ() == 2) {
                        for (Figure figure1 : cur1) {
                            if (figure1 instanceof Rook && figure1.getJ() == 3 && figure1.getI() == figure.getI()) {
                                figure1.setJ(0);
                                break;
                            }
                        }
                    } else if (figure.getJ() == 6) {
                        for (Figure figure1 : cur1) {
                            if (figure1 instanceof Rook && figure1.getJ() == 5 && figure1.getI() == figure.getI()) {
                                figure1.setJ(7);
                                break;
                            }
                        }
                    }
                }
                figure.setI(prevI);
                figure.setJ(prevJ);
            }
        }
        //можно ли спастись от мата поеданием
        for (Figure figure : cur1) {
            ArrayList<Pair> eatTo = figure.checkEat(black, white);
            int prevI = figure.getI(), prevJ = figure.getJ();
            for (Pair pair : eatTo) {
                Figure eaten = null;
                for (Figure figure1 : cur2) {
                    if (pair.getJ() == figure1.getJ() && pair.getI() == figure1.getI()) {
                        eaten = figure1;
                        break;
                    }
                }
                //типо ем
                assert eaten != null;
                eaten.setI(-10);
                eaten.setJ(-10);
                figure.setI(pair.getI());
                figure.setJ(pair.getJ());
                if (!chechShah()) {
                    ans = true;
                }
                eaten.setI(figure.getI());
                eaten.setJ(figure.getJ());
                figure.setI(prevI);
                figure.setJ(prevJ);
            }
        }
        return ans;
    }

    public void go(Figure figureGo, int goI, int goJ) {
        ArrayList<Figure> cur1;
        if (blackTurn) {
            cur1 = black;
        } else {
            cur1 = white;
        }
        // рокировка
        if (figureGo instanceof King && figureGo.getJ() == 4) {
            if (figureGo.isBlack() && figureGo.getI() == 0) {
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
            } else if (!figureGo.isBlack() && figureGo.getI() == 7) {
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
        figureGo.setI(goI);
        figureGo.setJ(goJ);
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
                        // отмена рокировки
                        if (currentFigure instanceof King && prevJ == 4 && ((currentFigure.isBlack() && prevI == 0) || (!currentFigure.isBlack() && prevI == 7))) {
                            if (currentFigure.getJ() == 2) {
                                for (Figure figure : cur1) {
                                    if (figure instanceof Rook && figure.getJ() == 3 && figure.getI() == currentFigure.getI()) {
                                        figure.setJ(0);
                                        break;
                                    }
                                }
                            } else if (currentFigure.getJ() == 6) {
                                for (Figure figure : cur1) {
                                    if (figure instanceof Rook && figure.getJ() == 5 && figure.getI() == currentFigure.getI()) {
                                        figure.setJ(7);
                                        break;
                                    }
                                }
                            }
                        }
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
                }
            }
        }
        //проверка
        if (!checkGo()) {
            if (!chechShah()) {
                result = -1;
            } else {
                if (blackTurn) {
                    result = 1;
                } else {
                    result = 2;
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

    public int getResult() {
        return result;
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
        //проверка
        if (!checkGo()) {
            if (!chechShah()) {
                result = -1;
            } else {
                if (blackTurn) {
                    result = 1;
                } else {
                    result = 2;
                }
            }
        }
    }
}
