import javax.swing.*;
import java.awt.*;

/*
 * Панель, где все правила шахмат
 * */
public class RulesPanel extends JPanel {
    private final int width;
    private final int height;

    public RulesPanel(int w, int h) {
        width = w;
        height = h;
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(new Color(255, 253, 208));
        g2d.fillRect(0, 0, width, height);
        g2d.setPaint(new Color(78, 52, 35));
        g2d.setFont(new Font("Verdana", Font.BOLD, 72));
        g2d.drawString("Правила шахмат", width / 3, height / 10);
        g2d.setFont(new Font("Verdana", Font.PLAIN, 30));
        g2d.drawString("1) Первыми ходят белые, ходы осуществляются поочередно", width / 10, 3 * height / 20);
        g2d.drawString("2) Фигуры могут ходить или есть в соответствии с правилами", width / 10, 4 * height / 20);
        g2d.drawString("3) Ладья ходит и ест по вертикали и горизонтали", width / 10, 5 * height / 20);
        g2d.drawString("4) Слон ходит и ест по горизонтали", width / 10, 6 * height / 20);
        g2d.drawString("5) Конь ходит и ест буквой \"Г\"", width / 10, 7 * height / 20);
        g2d.drawString("6) Ферзь ходит и ест по вертикали, горизонтали и диагонали", width / 10, 8 * height / 20);
        g2d.drawString("7) Пешка ест соседнюю спереди по диагонали клетку", width / 10, 9 * height / 20);
        g2d.drawString("8) Пешка ходит вперед на 1 клетку или на 2, если она на старте", width / 10, 10 * height / 20);
        g2d.drawString("9) Пешка на последней горизонтали меняется на ферзя, ладью, коня или слона", width / 10, 11 * height / 20);
        g2d.drawString("10) Король ходит и ест как ферзь, но на единичную длину", width / 10, 12 * height / 20);
        g2d.drawString("11) Король может совершить рокировку с ладьей, если они на стартовых", width / 10, 13 * height / 20);
        g2d.drawString("позициях, и между ними нет других фигур: король смещается", width / 10, 14 * height / 20);
        g2d.drawString("на 2 клетки в сторону ладьи, а ладья занимает клетку, перепрыгнутую королем", width / 10, 15 * height / 20);
    }
}
