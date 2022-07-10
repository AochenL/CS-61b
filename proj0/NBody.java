
public class NBody{
    public static double readRadius(String filename){
        /** Given a file name as a String,
        return a double corresponding to
        the radius of the universe in that file*/
        In in = new In(filename);
        in.readInt();
        double r = in.readDouble();
        return r;
    }

    public static Body[] readBodies(String filename){
        /**Given a file name,
         return an array of Bodys corresponding to
         the bodies in the file */
         In in = new In(filename);
         /** integer N represents the number of planets*/
         int N = in.readInt();
         in.readDouble();

         Body[] allBodys = new Body[N];

         for (int i = 0; i < N; i++ ){
             double xP = in.readDouble();
             double yP = in.readDouble();
             double xV = in.readDouble();
             double yV = in.readDouble();
             double m = in.readDouble();
             String img = in.readString();
             allBodys[i] = new Body(xP,yP,xV,yV,m,img);
         };

         return allBodys;
    }

    public static void drawBackgroundImage(double radius){
        StdDraw.enableDoubleBuffering();


    }

    public static void main(String[] args){

        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        /** Read everything from the files */
        double radius = readRadius(filename);
        Body[] allBodys = readBodies(filename);
        /** draw drawBackground image */
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        StdDraw.show();
        StdDraw.pause(20);
        /** draw each one of the bodies in the Bodys array */
        for (Body b : allBodys){
            b.draw();
        }
        StdDraw.enableDoubleBuffering();

        /** Create a variable that represents time.
        Set it to 0. Set up a loop to loop
        until this time variable reaches
        (and includes) the T from above.*/
        double Time = 0.0;
        int N = allBodys.length;

        for (Time = 0; Time <= T; Time += dt){
            /** 1. Create an xForces array and yForces array.*/
            double[] xForces = new double[N];
            double[] yForces = new double[N];
            /** 2. Calculate the net x and y forces for each Body,
            storing these in the xForces and yForces arrays respectively*/
            for (int i = 0 ; i < N ; i++){
                xForces[i] = allBodys[i].calcNetForceExertedByX(allBodys);
                yForces[i] = allBodys[i].calcNetForceExertedByY(allBodys);
            }
            /**3. update each body’s position,
            velocity, and acceleration. */
            for (int j = 0 ; j < N ; j++){
                allBodys[j].update(dt, xForces[j], yForces[j]);
            }
            /** 4. Draw the background image.*/
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");            /** 5. Draw all of the Bodys.*/
            for (Body b: allBodys){
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(20);
        }

        StdOut.printf("%d\n", allBodys.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allBodys.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            allBodys[i].xxPos, allBodys[i].yyPos, allBodys[i].xxVel,
            allBodys[i].yyVel, allBodys[i].mass, allBodys[i].imgFileName);
        }


    }

}
