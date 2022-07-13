import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MyPanel extends JPanel {
    boolean whiteTurn = false;
    int width, height, dx, size, curI = -1, curJ = -1;
    Cell[][] board;
    Point cursor;
    Figure currentFigure;
    ArrayList<Figure> black = new ArrayList<>();
    ArrayList<Figure> white = new ArrayList<>();
    ArrayList<Pair> eat = new ArrayList<>();
    ArrayList<Pair> go = new ArrayList<>();

    public MyPanel(int w, int h) {
        width = w;
        height = h;
        dx = (width - height) / 2;
        size = (height / 8);
        board = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Cell(dx + j * size, i * size, size, size);
            }
        }
        black.add(new Rook(4, 0, true));
        black.add(new Knight(4, 1, true));
        black.add(new Bishop(0, 2, true));
        black.add(new Queen(0, 3, true));
        black.add(new King(0, 4, true));
        black.add(new Bishop(4, 5, true));
        black.add(new Knight(4, 6, true));
        black.add(new Rook(0, 7, true));
        for (int i = 0; i < 8; i++) {
            black.add(new Pawn(1, i, true));
        }
        white.add(new Rook(4, 3, false));
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
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cursor = MouseInfo.getPointerInfo().getLocation();
                repaint();
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        Cell a = board[i][j];
                        if (cursor.getX() <= a.getX1() && cursor.getX() >= a.getX0() && cursor.getY() <= a.getY1() && cursor.getY() >= a.getY0()) {
                            curJ = j;
                            curI = i;
                        }
                    }
                }
                if(currentFigure == null){
                    eat = new ArrayList<>();
                    go = new ArrayList<>();
                } else {
                    eat = currentFigure.checkEat(black, white);
                    go = currentFigure.checkGo(black, white);
                }
            }
        });
        timer.start();
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (whiteTurn) {
                    for (Figure figure : white) {
                        if (figure.getJ() == curJ && figure.getI() == curI) {
                            currentFigure = figure;
                            break;
                        }
                    }
                } else {
                    for (Figure figure : black) {
                        if (figure.getJ() == curJ && figure.getI() == curI) {
                            currentFigure = figure;
                            break;
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(getImage("back.jpg"), 0, 0, width, height, null);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    g2d.setPaint(new Color(255, 253, 208));
                } else {
                    g2d.setPaint(new Color(78, 52, 35));
                }
                Cell a = board[i][j];
                g2d.fillRect(a.getX0(), a.getY0(), a.getWidth(), a.getHeight());
            }
        }
        for (Figure figure : black) {
            Cell a = board[figure.getI()][figure.getJ()];
            String name = "black/";
            if (figure instanceof Pawn) {
                name += "pawn.png";
            } else if (figure instanceof Rook) {
                name += "rook.png";
            } else if (figure instanceof Knight) {
                name += "knight.png";
            } else if (figure instanceof Bishop) {
                name += "bishop.png";
            } else if (figure instanceof Queen) {
                name += "queen.png";
            } else {
                name += "king.png";
            }
            g2d.drawImage(getImage(name), a.getX0(), a.getY0(), a.getWidth(), a.getHeight(), null);
        }
        for (Figure figure : white) {
            Cell a = board[figure.getI()][figure.getJ()];
            String name = "white/";
            if (figure instanceof Pawn) {
                name += "pawn.png";
            } else if (figure instanceof Rook) {
                name += "rook.png";
            } else if (figure instanceof Knight) {
                name += "knight.png";
            } else if (figure instanceof Bishop) {
                name += "bishop.png";
            } else if (figure instanceof Queen) {
                name += "queen.png";
            } else {
                name += "king.png";
            }
            g2d.drawImage(getImage(name), a.getX0(), a.getY0(), a.getWidth(), a.getHeight(), null);
        }
        if (currentFigure != null) {
            g2d.setPaint(Color.BLUE);
            g2d.setStroke(new BasicStroke(6));
            Cell a = board[currentFigure.getI()][currentFigure.getJ()];
            g2d.drawRect(a.getX0() + 3, a.getY0() + 3, a.getWidth() - 6, a.getHeight() - 6);
        }
        if (eat != null) {
            for (Pair pair : eat) {
                g2d.setPaint(Color.RED);
                Cell a = board[pair.getI()][pair.getJ()];
                g2d.drawRect(a.getX0() + 3, a.getY0() + 3, a.getWidth() - 6, a.getHeight() - 6);
            }
        }
        if (go != null) {
            for (Pair pair : go) {
                g2d.setPaint(Color.GREEN);
                Cell a = board[pair.getI()][pair.getJ()];
                g2d.drawRect(a.getX0() + 3, a.getY0() + 3, a.getWidth() - 6, a.getHeight() - 6);
            }
        }
    }

    public Image getImage(String name) {
        return new ImageIcon("images/" + name).getImage();
    }
}
