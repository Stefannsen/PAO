package Patrulatere;

public class Paralelogram extends Patrulater {
    protected double l;
    protected double L;
    protected double shAngle;
    protected double obAngle;

    public Paralelogram(double l, double L, double shAngle){
        this.l = l;
        this.L = L;
        this.shAngle = shAngle;
        this.obAngle = 180 - shAngle;
    }

    @Override
    public double area() {
        double h;
        h = l * Math.cos((90 - shAngle) * Math.PI / 180);
        return h * L;
    }

    @Override
    public double perimeter() {
        return 2 * (l + L);
    }

}

class PTest {
    public static void main(String[] args){
        Paralelogram p = new Paralelogram(3, 5, 30);
        System.out.println(p.perimeter() + " " + p.area());
    }
}
