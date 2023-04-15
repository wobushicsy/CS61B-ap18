public class Planet {
    private static final double Grativity = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        return Math.sqrt(Math.pow((this.xxPos-p.xxPos), 2) + Math.pow((this.yyPos-p.yyPos), 2));
    }

    public double calcForceExertedBy(Planet p){
        double dis = this.calcDistance(p);
        return Grativity * this.mass * p.mass / (dis * dis);
    }

    public double calcForceExertedByX(Planet p){
        double force = this.calcForceExertedBy(p);
        double distance = this.calcDistance(p);
        return force * (p.xxPos - this.xxPos) / distance;
    }

    public double calcForceExertedByY(Planet p){
        double force = this.calcForceExertedBy(p);
        double distance = this.calcDistance(p);
        return force * (p.yyPos - this.yyPos) / distance;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets)
    {
        double totalForce = 0;
        for (Planet planet : allPlanets) {
            if (this.equals(planet)) {
                continue;
            }
            totalForce += calcForceExertedByX(planet);
        }
        return totalForce;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets)
    {
        double totalForce = 0;
        for (Planet planet : allPlanets) {
            if (this.equals(planet)) {
                continue;
            }
            totalForce += calcForceExertedByY(planet);
        }
        return totalForce;
    }

    public void update(double deltat, double xForce, double yForce){
        double accelerationX = xForce / this.mass;
        double accelerationY = yForce / this.mass;
        this.xxVel += accelerationX * deltat;
        this.yyVel += accelerationY * deltat;
        this.xxPos += this.xxVel * deltat;
        this.yyPos += this.yyVel * deltat;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}