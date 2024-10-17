package com.example.project;
public class LinearCalculator{
    //creating the instance variables
    private int x1;
    private int x2;
    private int y1;
    private int y2;


    // The constructer has 2 strings as parameters
    public LinearCalculator(String coord1, String coord2) {
        // deletes the ( and ) from both coord1 and coord2
        // https://stackoverflow.com/questions/7552253/how-to-remove-special-characters-from-a-string
        String fcoord = coord1.replaceAll("[()]", "");
        String lcoord = coord2.replaceAll("[()]", "");


        // indexOf is used to exclude the ,
        // Interger.valueOf changes a string to an Integer
        // https://sentry.io/answers/how-do-i-convert-a-string-to-an-int-in-java/
        x1 = Integer.valueOf(fcoord.substring(0, fcoord.indexOf(",")));
        x2 = Integer.valueOf(lcoord.substring(0, lcoord.indexOf(",")));
        y1 = Integer.valueOf(fcoord.substring(fcoord.indexOf(",") + 1));
        y2 = Integer.valueOf(lcoord.substring(lcoord.indexOf(",") + 1));
    }


    // returns x1
    public int getX1(){
        return x1;
    }
    // returns y1
    public int getY1(){
        return y1;
    }
    // returns x2
    public int getX2(){
        return x2;
    }
    // returns y2
    public int getY2(){
        return y2;
    }
    // sets x1 to newx1, the parameter
    public void setX1(int newx1){
        x1 = newx1;
    }
    // sets y1 to newy1, the parameter
    public void setY1(int newy1){
        y1 = newy1;
    }
    // sets x2 to newx2, the parameter
    public void setX2(int newx2){
        x2 = newx2;
    }
    // sets y2 to newy2, the parameter
    public void setY2(int newy2){
        y2 = newy2;
    }


    // used a^2 + b^2 = c^2 for the distance
    public double distance(){
        return roundedToHundredth(Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)));
    }


    // used y = mx + b, and isolated b in order to get the y-intercept
    public double yInt(){
        // used the slope function to check if the line is horizontal, meaning the slope is undefined
        if (slope() == -999.99) {
            return -999.99;
        } else return roundedToHundredth(y1 - slope() * x1);
    }


    // used slope is equal to delta y over delta x
    public double slope(){
        // first checked if the line is horizontal
        if (x1 == x2) {
            return -999.99;
        } else return roundedToHundredth(((double) y2 - y1) / ((double) x2 - x1));
    }


    public String equation(){
        // checked if the line is horizontal
        if (x1 == x2) {
            return "undefined";
        }
        // then used the slope function to check if the slope is 0, to only include y-intercept
        if (slope() == 0) {
            return "y=" + yInt();
        // then checked if the y-intercept was zero, to not include it
        } else if (yInt() == 0) {
            return "y=" + slope() + "x";
        // then checked if the y-intercept was less than 0, to not include the "+" sign
        } else if (yInt() < 0) {
            return "y=" + slope() + "x" + yInt();
        } else return "y=" + slope() + "x+" + yInt();
    }


    // used Math.round and multiplied and divided by 100 in order to round to the hundreths
    public double roundedToHundredth(double x){
        // used 100.0 to make sure not to accidentally do integer division
        return Math.round(x * 100.0) / 100.0;
    }



    public String printInfo(){
        // used instance variables
        String str = "The two points are: (" + x1 + "," + y2 + ")";
        str += " and " + "(" + x2 + "," + y2 + ")";
        // used the methods to get these results
        str += "\nThe equation of the line between these points is: " + equation();
        str += "\nThe slope of this line is: " + slope();
        str += "\nThe y-intercept of the line is: " + yInt();
        str += "\nThe distance between the two points is: " + distance();
        // extra credit, concatenates what the functions return
        str += "\n" + findSymmetry();
        str += "\n" + Midpoint();
 
        return str;

        }

        public String findSymmetry(){
            // first checks if it's symmetric about the origin, so it won't return symmetric to x/y axis
            if (Math.abs(x1) == Math.abs(x2) && Math.abs(y1) == Math.abs(y2) && x1 != x2 && y1 != y2) {
                return "Symmetric about the origin";
            // then checks if it's symmetric about the x-axis, makes sure that both xs aren't the same
            } else if (Math.abs(x1) == Math.abs(x2) && x1 != x2) {
                return "Symmetric about the y-axis";
            // then checks if it's symmetric about the y-axis, makes sure that both ys aren't the same
            } else if (Math.abs(y1) == Math.abs(y2) && y1 != y2) {
                return "Symmetric about the x-axis";
            } else return "No symmetry";
        }

        public String Midpoint(){
            // adds both x and divides by 2 to find the middle
            double xm = (double)(x1 + x2) / 2;
             // adds both y and divides by 2 to find the middle
            double ym = (double)(y1 + y2) / 2;
            return "The midpoint of this line is: (" + xm + "," + ym + ")";
        }
    }
