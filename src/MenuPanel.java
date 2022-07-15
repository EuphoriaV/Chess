import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    private final int width;
    private final int height;
    private int numOfButton = 0;
    private JButton exit, rules, game;

    public int getNumOfButton() {
        return numOfButton;
    }

    public void setNumOfButton(int numOfButton) {
        this.numOfButton = numOfButton;
    }

    public MenuPanel(int w, int h) {
        width = w;
        height = h;
        setLayout(null);
        setPreferredSize(new Dimension(width, height));
        Color brown = new Color(78, 52, 35), white = new Color(255, 253, 208);
        setBackground(white);
        LineBorder border = new LineBorder(brown, 10);
        game = new JButton("Играть");
        game.setBounds(width / 3, height / 7, width / 3, height / 7);
        game.setFont(new Font("Verdana", Font.BOLD, 72));
        game.setForeground(brown);
        game.setBackground(white);
        game.setBorder(border);
        game.setFocusable(false);
        game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numOfButton = 1;
            }
        });
        rules = new JButton("Правила");
        rules.setBounds(width / 3, 3 * height / 7, width / 3, height / 7);
        rules.setFont(new Font("Verdana", Font.BOLD, 72));
        rules.setForeground(brown);
        rules.setBackground(white);
        rules.setBorder(border);
        rules.setFocusable(false);
        rules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numOfButton = 2;
            }
        });
        exit = new JButton("Выйти");
        exit.setBounds(width / 3, 5 * height / 7, width / 3, height / 7);
        exit.setFont(new Font("Verdana", Font.BOLD, 72));
        exit.setForeground(brown);
        exit.setBackground(white);
        exit.setBorder(border);
        exit.setFocusable(false);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numOfButton = 3;
            }
        });
        add(game);
        add(rules);
        add(exit);
    }
}
