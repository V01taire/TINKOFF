package edu.project4.Forms.Classes;

import edu.project4.Settings.Records.Point;

public class Sinusoid {

    public static Point doTransform(Point point) {
        return new Point(
            Math.sin(point.x()),
            Math.sin(point.y())
        );
    }

    private Sinusoid() {
    }
}
