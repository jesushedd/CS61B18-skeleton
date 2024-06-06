public class NBody {

    /*String -> double
    * consumes a String representing the filename
    * returns the 2nd token in file as a double*/
    public static double readRadius(String filename){
        In file = new In(filename);
        file.readDouble();
        return file.readDouble();
    }

    public static Planet[] readPlanets(String filename){
        //create file pointer
        In file = new In(filename);
        //get number of planets in file
        int numberOfPlanets = file.readInt();
        // create empty array size of number of planets
        Planet[] planets = new Planet[numberOfPlanets];
        //jump radius
        file.readDouble();
        //read number of parameters for each planet and create a Planet objet with them
        for (int p = 0; p < numberOfPlanets; p ++){
           double xxCoords = file.readDouble();
           double yyCoords = file.readDouble();
           double xxVelocity = file.readDouble();
           double yyVelocity = file.readDouble();
           double mass = file.readDouble();
           String name = file.readString();
           planets[p] = new Planet(xxCoords,yyCoords, xxVelocity, yyVelocity, mass, name);
       }
        return planets;
    }

    public static void main(String[] args) {
        
    }
}
