public class NBody {

    /*String -> double*/
    public static double readRadius(String filename){
        In file = new In(filename);
        file.readDouble();
        return file.readDouble();
    }
}
