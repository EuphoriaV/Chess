import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**/
public class MyFrame extends JFrame {
    private Stage curStage = Stage.INTRODUCTION;
    private final int width;
    private final int height;
    private final IntroductionPanel introPanel;
    private final MenuPanel menuPanel;

    public MyFrame() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    curStage = Stage.MENU;
                    getContentPane().removeAll();
                    add(menuPanel);
                    pack();
                    repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();
        introPanel = new IntroductionPanel(width, height);
        menuPanel = new MenuPanel(width, height);
        add(introPanel);
        pack();
        Timer timer = new Timer(5, null);
        timer.addActionListener(e -> {
            // заставка
            if (curStage == Stage.INTRODUCTION) {
                introPanel.setColor(introPanel.getColor() + 2);
                introPanel.repaint();
                if (introPanel.getColor() >= 500) {
                    curStage = Stage.MENU;
                    getContentPane().removeAll();
                    add(menuPanel);
                    pack();
                }
            } else if (curStage == Stage.MENU) {
                // меню  и действия по кнопкам
                int num = menuPanel.getNumOfButton();
                if (num == 3) {
                    // выход
                    System.exit(0);
                }
                if (num == 2) {
                    // правила
                    curStage = Stage.RULES;
                    getContentPane().removeAll();
                    add(new RulesPanel(width, height));
                    pack();
                }
                if (num == 1) {
                    // игра
                    curStage = Stage.GAME;
                    getContentPane().removeAll();
                    add(new GamePanel(width, height));
                    pack();
                }
                menuPanel.setNumOfButton(0);
            }
            repaint();
        });
        timer.start();
        setVisible(true);
    }
}
