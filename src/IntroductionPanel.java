import javax.swing.*;
import java.awt.*;

public class IntroductionPanel extends JPanel {
    private final int width;
    private final int height;
    private int color = 1;

    public IntroductionPanel(int w, int h) {
        width = w;
        height = h;
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int a = Math.min(color, 500 - color);
        g2d.setPaint(Color.BLACK);
        g2d.fillRect(0, 0, width, height);
        g2d.setPaint(new Color(a, a, a));
        g2d.setFont(new Font("Verdana", Font.BOLD, 72));
        g2d.drawString("Ilya Kuznetsov", width / 3, height / 2);
        g2d.drawString("Production", 2 * width / 5, 3 * height / 5);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
