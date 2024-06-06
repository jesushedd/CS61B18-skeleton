public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    double mass;
    public String imgFileName;
    private static final double G = 6.67E-11;

    /*Constructor for Planet, consumes coordinates position(x,y), velocity(x,y)
    and mass(m)*/
    public  Planet(double xP, double yP, double xV,
                   double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /*Constructor for copying a Planet instance*/
    public Planet(Planet P) {
        xxPos = P.xxPos;
        yyPos = P.yyPos;
        xxVel = P.xxVel;
        yyVel = P.yyVel;
        mass = P.mass;
        imgFileName = P.imgFileName;
    }

    public double calcDistance(Planet toPlanet){
        return Math.sqrt(Math.pow(toPlanet.yyPos - this.yyPos, 2) + Math.pow(toPlanet.xxPos - this.xxPos, 2));
    }

    public double calcForceExertedBy(Planet byPlanet){

        return G * this.mass * byPlanet.mass / Math.pow(this.calcDistance(byPlanet),2);
    }

    public double calcForceExertedByX(Planet byPlanet){
        double xDistance = byPlanet.xxPos - this.xxPos;

        return this.calcForceExertedBy(byPlanet) * xDistance / this.calcDistance(byPlanet);
    }

    public double calcForceExertedByY(Planet byPlanet){
        double yDistance = byPlanet.yyPos - this.yyPos;

        return this.calcForceExertedBy(byPlanet) * yDistance / this.calcDistance(byPlanet);
    }

    public double calcNetForceExertedByX(Planet[] byPlanets){
        double netForce = 0;
        for (Planet planet : byPlanets) {
            if (!this.equals(planet)){
                netForce += this.calcForceExertedByX(planet);
            }
        }
        return netForce;
    }

    public double calcNetForceExertedByY(Planet[] byPlanets){
        double netForce = 0;
        for (Planet planet : byPlanets) {
            if (!this.equals(planet)){
                netForce += this.calcForceExertedByY(planet);
            }
        }
        return netForce;
    }

    public void update(double dTime, double xForce, double yForce){
        //Calculate acceleration caused by forces x and y
        double xxAcceleration = xForce / this.mass;
        double yyAcceleration = yForce / this.mass;

        //Calculate new velocities
        double newXvelocity = this.xxVel + xxAcceleration * dTime;
        double newYvelocity = this.yyVel + yyAcceleration * dTime;

        //Update new velocities
        this.xxVel = newXvelocity;
        this.yyVel = newYvelocity;

        //Calculate and update new position
        this.xxPos = this.xxPos + dTime * newXvelocity;
        this.yyPos = this.yyPos + dTime * newYvelocity;

    }

}