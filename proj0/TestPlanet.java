public class TestPlanet {
    public static void main(String[] args) {
        Planet p1 = new Planet(76, 56, 34, 89, 2679, "jupiter.gif");

        Planet p2 = new Planet(1000, 265453, 34, 89, 6789, "jupiter.gif");
        double force = p1.calcForceExertedBy(p2);
        assert Math.abs(force - 265398.60848354) < 0.00001 : "tess failed";
        System.out.println("force is: "+  force);
    }
}
