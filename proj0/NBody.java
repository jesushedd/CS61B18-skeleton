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

    private static double getCordsToMove(double ratio, double pixels){
        return  ratio * pixels;
    }

    private static void drawnBackGround(int coordPixelRatio, double radius){
        String background = ".\\images\\starfield.jpg";
        double s = getCordsToMove(coordPixelRatio, 256);
        StdDraw.picture(- radius +s, -radius + s  , background);
        StdDraw.picture(- radius +s, radius - s  , background);
        StdDraw.picture( radius -s, radius - s  , background);
        StdDraw.picture(radius -s, -radius + s  , background);

    }

    public static void main(String[] args) {

        //saving args
        double T = Double.parseDouble(args[0]);//time of simulation
        double dT = Double.parseDouble(args[1]);//time steps
        String filename = args[2];

        //Reading simulation input
        double radius = readRadius(filename);
        //System.out.printf("%.0f",radius);
        Planet[] planets = readPlanets(filename);
        int numberOfPlanets = planets.length;

        // canvas size and set scale to radius
        int canvWidth = 900;
        int canvHeight = 900;
        StdDraw.setCanvasSize(canvWidth, canvHeight);
        StdDraw.setScale(-radius, radius);
        int coordPixelRatio = (int ) (2 * radius / 900);

        //Draw background
        drawnBackGround(coordPixelRatio, radius);

        //Drawn planets in array
        for (Planet p : planets){
            p.drawn();
        }

        //Create animation
        StdDraw.enableDoubleBuffering();
        double[] xForces = new double[numberOfPlanets];
        double[] yForces = new double[numberOfPlanets];
        double time = 0;
        while (true) {
            for (int i = 0; i < numberOfPlanets; i++) {//get net forces for each planet
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            drawnBackGround(coordPixelRatio, radius);

            for (int j=0; j < numberOfPlanets; j++){// update planets params, drawn planets
                Planet currentPlanet = planets[j];
                currentPlanet.update(dT, xForces[j], yForces[j]);
                currentPlanet.drawn();
            }
            StdDraw.show();
            StdDraw.pause(17);
            time += dT;
            System.out.println("day " + (int)time / 86400);

        }








    }
}
