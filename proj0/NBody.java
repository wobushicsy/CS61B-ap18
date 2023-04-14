public class NBody {

    public static String imageToDraw = "imagesarfield.jpg";

    public static void main(String[] args){
        // read data
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);

        // draw background
        StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius, radius);
        StdDraw.enableDoubleBuffering();

        // draw planets
        double t = 0;
        int num = planets.length;
        while(t <= T){
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            for(int i = 0; i < num; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i = 0; i < num; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            // draw the backgroud picture
            StdDraw.picture(0, 0, "imagesarfield.jpg");

            // draw all the planets
            for (Planet planet : planets) {
                planet.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
    }

    static double readRadius(String fileName){
        In in = new In(fileName);
        int planetsNums = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    static Planet[] readPlanets(String fileName){
        In in = new In(fileName);
        int planetsNums = in.readInt();
        double radius = in.readDouble();
        double xxPos, yyPos, xxVel, yyVel, mass;
        String imgFileName;
        Planet[] planets = new Planet[planetsNums];
        int i = 0;
        for (i = 0; i < planetsNums; i ++){
            xxPos = in.readDouble();
            yyPos = in.readDouble();
            xxVel = in.readDouble();
            yyVel = in.readDouble();
            mass = in.readDouble();
            imgFileName = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }

        return planets;
    }
}

