import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MenuPanel extends JPanel {
    private int numOfButton = 0;

    public int getNumOfButton() {
        return numOfButton;
    }

    public void setNumOfButton(int numOfButton) {
        this.numOfButton = numOfButton;
    }

    public MenuPanel(int w, int h) {
        setLayout(null);
        setPreferredSize(new Dimension(w, h));
        Color brown = new Color(78, 52, 35), white = new Color(255, 253, 208);
        setBackground(white);
        LineBorder border = new LineBorder(brown, 10);
        JButton game = new JButton("Играть");
        game.setBounds(w / 3, h / 7, w / 3, h / 7);
        game.setFont(new Font("Verdana", Font.BOLD, 72));
        game.setForeground(brown);
        game.setBackground(white);
        game.setBorder(border);
        game.setFocusable(false);
        game.addActionListener(e -> numOfButton = 1);
        JButton rules = new JButton("Правила");
        rules.setBounds(w / 3, 3 * h / 7, w / 3, h / 7);
        rules.setFont(new Font("Verdana", Font.BOLD, 72));
        rules.setForeground(brown);
        rules.setBackground(white);
        rules.setBorder(border);
        rules.setFocusable(false);
        rules.addActionListener(e -> numOfButton = 2);
        JButton exit = new JButton("Выйти");
        exit.setBounds(w / 3, 5 * h / 7, w / 3, h / 7);
        exit.setFont(new Font("Verdana", Font.BOLD, 72));
        exit.setForeground(brown);
        exit.setBackground(white);
        exit.setBorder(border);
        exit.setFocusable(false);
        exit.addActionListener(e -> numOfButton = 3);
        add(game);
        add(rules);
        add(exit);
    }
}
