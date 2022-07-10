
public class Planet{
    private static final double G = 6.67e-11;
    /** double xxPos: Its current x position
    double yyPos: Its current y position
    double xxVel: Its current velocity in the x direction
    double yyVel: Its current velocity in the y direction
    double mass: Its mass
    String imgFileName: T */
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    /** */
    /**  Create a Body object using constructor*/
    public Body(double xP, double yP, double xV,
                double yV, double m, String img){
                    xxPos = xP;
                    yyPos = yP;
                    xxVel = xV;
                    yyVel = yV;
                    mass = m;
                    imgFileName = img;
                }
   /** The second constructor takes in a Body object
   and initialize an identical Body object */
    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b){
        /** Calculated the distance between this body and body b;
            r = sqrt(dx^2+dy^2)
         */
        double dx = this.xxPos-b.xxPos;
        double dy = this.yyPos-b.yyPos;
        double r = Math.sqrt(dx*dx+dy*dy);
        return r;
    }

    public double calcForceExertedBy(Body b){
        /** Calculte the force between this body and body b;
        F=G⋅m1⋅m2/r^2 */
        double r = this.calcDistance(b);
        double F = G*this.mass*b.mass/(r*r);
        return F;
    }

    public double calcForceExertedByX(Body b){
        /** Calculte the force exerted by Body b in the X direction;
        Fx=F⋅dx/r
        check the signs */
        double r = this.calcDistance(b);
        double dx = b.xxPos-this.xxPos;
        double F = this.calcForceExertedBy(b);
        double Fx = F*dx/r;
        return Fx;
    }

    public double calcForceExertedByY(Body b){
        /** Calculte the force exerted by Body b in the Y direction;
        Fx=F⋅dy/r
        check the signs */
        double r = this.calcDistance(b);
        double dy = b.yyPos-this.yyPos;
        double F = this.calcForceExertedBy(b);
        double Fy = F*dy/r;
        return Fy;
    }

    public double calcNetForceExertedByX(Body[] allBodys){
        /** calculates the net X force
        exerted by all bodies in that array upon the current Body

        use the .equals method to
        ignore any body in the array that is equal to the current body

        Fnet,x=F1,x+F2,x*/
        double FnetX = 0;
        for (Body b : allBodys){
            if (!(this.equals(b))){
                FnetX += this.calcForceExertedByX(b);
            }
        }
        return FnetX;
    }

    public double calcNetForceExertedByY(Body[] allBodys){
        /** calculates the net Y force
        exerted by all bodies in that array upon the current Body */
        double FnetY = 0;
        for (Body b : allBodys){
            if (!(this.equals(b))){
                FnetY += this.calcForceExertedByY(b);
            }
        }
        return FnetY;
    }

    public void update(double dt, double Fx, double Fy){
        /** add a method that determines how much the forces exerted on the body w
        ill cause that body to accelerate,
        and the resulting change in the body’s velocity and position
        in a small period of time dt.
        For example, samh.update(0.005, 10, 3)
        would adjust the velocity and position
        if an x-force of 10 Newtons and a y-force of 3 Newtons
        were applied for 0.005 seconds.*/

        /** 1. Calculate the acceleration
        using the provided x- and y-forces
        a= F/m */
        double ax = Fx/this.mass;
        double ay = Fy/this.mass;
        /** 2. Calculate the new velocity.
        (vx+dt⋅ax,vy+dt⋅ay).*/
        this.xxVel += dt*ax;
        this.yyVel += dt*ay;
        /** 3. Calculate the new position. T
        the new position is (px+dt⋅vx,py+dt⋅vy).*/
        this.xxPos += dt*this.xxVel;
        this.yyPos += dt*this.yyVel;

    }

    public void draw(){
        /** a planet draw itself at its appropriate position*/
        String imageToDraw = "./images/"+this.imgFileName;
        double x = this.xxPos;
        double y = this.yyPos;
        StdDraw.picture(x, y, imageToDraw);
    }


}
