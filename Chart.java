import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Chart {
    protected ArrayList<Double> data;
    protected ArrayList<String> labels;

    public Chart(ArrayList<Double> data, ArrayList<String> labels) {
        this.data = data;
        this.labels = labels;
    }

    public abstract void draw(Graphics g, int x, int y, int width, int height);
}
