package edu.project4.FractalBuilder;

import edu.project4.Forms.Classes.Disk;
import edu.project4.Settings.Enums.FileFormat;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    public static final Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings({"MagicNumber", "MultipleStringLiterals"})
    public static void main(String[] args) {
        for (int i = 1; i <= 14; i++) {
            Fractal fractal = new Fractal(1920, 1080, 4);
            long start = System.nanoTime();
            fractal.createFractal(100000, 1000, 2,
                List.of(
                    Disk::doTransform
                ), i);
            fractal.save("fractal_image" + i, FileFormat.PNG);
            long end = System.nanoTime();
            LOGGER.info("thread number " + i + ": completed in " + (end - start) / 1000000000 + " sec.");
        }
    }

    private Main() {
    }
}
