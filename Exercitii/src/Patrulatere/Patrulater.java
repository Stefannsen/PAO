package Patrulatere;

public abstract class Patrulater {
    public abstract double area();
    public abstract double perimeter();

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
