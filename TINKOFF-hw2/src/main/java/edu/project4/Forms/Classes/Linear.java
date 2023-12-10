package edu.project4.Forms.Classes;

import edu.project4.Settings.Records.Point;

public class Linear {

    public static Point doTransform(Point point) {
        return new Point(
            point.x(),
            point.y()
        );
    }

    private Linear() {
    }
}
