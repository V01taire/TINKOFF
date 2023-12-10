package edu.project4.Forms.Classes;

import edu.project4.Settings.Records.Point;

public class Diamond {
    public static Point doTransform(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double theta = Math.atan2(point.y(), point.x());
        return new Point(
            Math.sin(theta) * Math.cos(r),
            Math.cos(theta) * Math.sin(r)
        );
    }

    private Diamond() {
    }
}
