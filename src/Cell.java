public class Cell {
    private final int x0, x1, y0, y1, width, height;

    public Cell(int a, int b, int c, int d) {
        x0 = a;
        y0 = b;
        x1 = a + c;
        y1 = b + d;
        width = c;
        height = d;
    }

    public int getX0() {
        return x0;
    }

    public int getX1() {
        return x1;
    }

    public int getY0() {
        return y0;
    }

    public int getY1() {
        return y1;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
