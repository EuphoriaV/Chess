import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    private final int width;
    private final int height;
    private Point cursor;
    private final Game game;
    private final Cell[][] board;
    private int onSwitch = -1;

    public GamePanel(int w, int h) {
        width = w;
        height = h;
        int dx = (width - height) / 2;
        int size = (height / 8);
        board = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Cell(dx + j * size, i * size, size, size);
            }
        }
        game = new Game();
        Timer timer = new Timer(5, null);
        timer.addActionListener(e -> {
            cursor = MouseInfo.getPointerInfo().getLocation();
            repaint();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Cell a = board[i][j];
                    if (cursor.getX() <= a.getX1() && cursor.getX() >= a.getX0() && cursor.getY() <= a.getY1() && cursor.getY() >= a.getY0()) {
                        game.setCurJ(j);
                        game.setCurI(i);
                    }
                }
            }
            if (game.isOnSwitch()) {
                if (game.isBlackTurn()) {
                    onSwitch = 0;
                } else {
                    onSwitch = 1;
                }
            } else {
                onSwitch = -1;
            }
        });
        timer.start();
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (onSwitch == -1) {
                    game.act();
                } else {
                    int num;
                    if (cursor.getX() < (width - height) / 2.0) {
                        if (cursor.getY() < height / 4.0) {
                            num = 0;
                        } else if (cursor.getY() < height / 2.0) {
                            num = 1;
                        } else if (cursor.getY() < 3 * height / 4.0) {
                            num = 2;
                        } else {
                            num = 3;
                        }
                        game.switchPawn(num);
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
                g2d.setPaint(Color.BLACK);
                g2d.drawRect(a.getX0(), a.getY0(), a.getWidth(), a.getHeight());
            }
        }
        if (game.getCurrentFigure() != null) {
            g2d.setPaint(Color.YELLOW);
            Cell a = board[game.getCurrentFigure().getI()][game.getCurrentFigure().getJ()];
            g2d.fillRect(a.getX0(), a.getY0(), a.getWidth(), a.getHeight());
            g2d.setPaint(Color.BLACK);
            g2d.drawRect(a.getX0(), a.getY0(), a.getWidth(), a.getHeight());
        }
        if (game.getEat() != null) {
            for (Pair pair : game.getEat()) {
                g2d.setPaint(Color.RED);
                Cell a = board[pair.getI()][pair.getJ()];
                g2d.fillRect(a.getX0(), a.getY0(), a.getWidth(), a.getHeight());
                g2d.setPaint(Color.BLACK);
                g2d.drawRect(a.getX0(), a.getY0(), a.getWidth(), a.getHeight());
            }
        }
        if (game.getGo() != null) {
            for (Pair pair : game.getGo()) {
                g2d.setPaint(Color.GREEN);
                Cell a = board[pair.getI()][pair.getJ()];
                g2d.fillRect(a.getX0(), a.getY0(), a.getWidth(), a.getHeight());
                g2d.setPaint(Color.BLACK);
                g2d.drawRect(a.getX0(), a.getY0(), a.getWidth(), a.getHeight());
            }
        }
        for (Figure figure : game.getBlack()) {
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
        for (Figure figure : game.getWhite()) {
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
        if (onSwitch > -1) {
            String directory = (onSwitch == 0) ? "white/" : "black/";
            g2d.drawImage(getImage(directory + "queen.png"), 0, 0, (width - height) / 2, height / 4, null);
            g2d.drawImage(getImage(directory + "rook.png"), 0, height / 4, (width - height) / 2, height / 4, null);
            g2d.drawImage(getImage(directory + "bishop.png"), 0, height / 2, (width - height) / 2, height / 4, null);
            g2d.drawImage(getImage(directory + "knight.png"), 0, 3 * height / 4, (width - height) / 2, height / 4, null);
        }
    }

    public Image getImage(String name) {
        return new ImageIcon("images/" + name).getImage();
    }
}
