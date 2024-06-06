public class myDemo {
    public static void main(String[] args) {
        In file = new In("national_salt_production.txt");
        System.out.println(args[0]);
        while (!file.isEmpty()) {
            int rank = file.readInt();
            String country = file.readString();
            System.out.println(rank + " " +country);
        }
    }
}
