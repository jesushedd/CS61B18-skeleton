public class Dog {
    private int size;
    public String race;

    public Dog(int s, String r) {
        size = s;
        race = "chihuhua";
    }

    /** Makes a noise. */
    public String noise() {
        if (size < 10) {
            return "yip";}
        return "bark";
    }
}
