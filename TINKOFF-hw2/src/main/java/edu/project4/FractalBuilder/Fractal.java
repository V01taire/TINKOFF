package edu.project4.FractalBuilder;

import edu.project4.Forms.Interfaces.FractalTransformation;
import edu.project4.Settings.Classes.CoefficientValue;
import edu.project4.Settings.Classes.Pixel;
import edu.project4.Settings.Enums.FileFormat;
import edu.project4.Settings.Records.Coefficient;
import edu.project4.Settings.Records.MatchColor;
import edu.project4.Settings.Records.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;

@SuppressWarnings("MultipleStringLiterals")
public class Fractal {
    private final Pixel[][] pixels;
    private final Coefficient[] coefficients;
    private final MatchColor[] colors;
    private final int width;
    private final int height;
    private final int coeffCount;
    private Thread[] threads;
    private final static Random RANDOM = new Random();


    public Fractal(int width, int height, int coeffCount) {
        if (width < 0 || height < 0 || coeffCount < 1) {
            throw new IllegalArgumentException("Wrong argument");
        }
        this.width = width;
        this.height = height;
        pixels = new Pixel[width][height];
        coefficients = new Coefficient[coeffCount];
        colors = new MatchColor[coeffCount];
        this.coeffCount = coeffCount;
        coefficientInit(coeffCount);
        fillPixels();
    }

    private void fillPixels() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[x][y] = new Pixel();
            }
        }
    }

    @SuppressWarnings("MagicNumber")
    private void coefficientInit(int coefficientCount) {
        for (int i = 0; i < coefficientCount; i++) {
            coefficients[i] = generateCoeff();
            colors[i] = new MatchColor(
                RANDOM.nextInt(64, 200),
                RANDOM.nextInt(64, 200),
                RANDOM.nextInt(64, 200)
            );
        }
    }

    @SuppressWarnings("MagicNumber")
    private Coefficient generateCoeff() {
        double a;
        double b;
        double c;
        double d;
        double e;
        double f;

        do {
            do {
                a = RANDOM.nextDouble(-1, 1);
                d = RANDOM.nextDouble(-1, 1);
            } while ((a * a + d * d) > 1);

            do {
                b = RANDOM.nextDouble(-1, 1);
                e = RANDOM.nextDouble(-1, 1);
            } while ((b * b + e * e) > 1);
        } while ((a * a + b * b + d * d + e * e) > (1 + (a * e - d * b) * (a * e - d * b)));

        c = RANDOM.nextDouble(-2, 2);
        f = RANDOM.nextDouble(-2, 2);
        return new Coefficient(a, b, c, d, e, f);
    }

    private void generate(int sample, int iterartion, int symmetry, List<FractalTransformation> transformations) {

        ThreadLocalRandom localRandom = ThreadLocalRandom.current();

        for (int num = 0; num < sample; num++) {

            Point point = new Point(
                localRandom.nextDouble(CoefficientValue.XMIN, CoefficientValue.XMAX),
                localRandom.nextDouble(CoefficientValue.YMIN, CoefficientValue.YMAX)
            );

            for (int step = 0; step < iterartion; step++) {

                int i = localRandom.nextInt(coeffCount);
                Coefficient currentCoefficient = coefficients[i];
                MatchColor currentColors = colors[i];

                point = new Point(
                    currentCoefficient.a() * point.x() + currentCoefficient.b() * point.y() + currentCoefficient.c(),
                    currentCoefficient.d() * point.x() + currentCoefficient.e() * point.y() + currentCoefficient.f()
                );

                point = transformations.get(localRandom.nextInt(transformations.size())).apply(point);

                double theta2 = 0.0;

                for (int s = 0; s < symmetry; s++) {

                    theta2 += 2 * Math.PI / symmetry;

                    Point rPoint = new Point(
                        point.x() * Math.cos(theta2) - point.y() * Math.sin(theta2),
                        point.x() * Math.sin(theta2) + point.y() * Math.cos(theta2)
                    );

                    if (isPointInInterval(rPoint)) {

                        int x = width - (int)
                            (((CoefficientValue.XMAX - rPoint.x())
                                / (CoefficientValue.XMAX - CoefficientValue.XMIN))
                                * width);
                        int y = height - (int)
                            (((CoefficientValue.YMAX - rPoint.y())
                                / (CoefficientValue.YMAX - CoefficientValue.YMIN))
                                * height);

                        if (isPointInWindow(x, y)) {

                            Pixel current = pixels[x][y];

                            synchronized (current) {
                                current = pixels[x][y];

                                if (current.getHitCounter() == 0) {
                                    current.setR(currentColors.r());
                                    current.setG(currentColors.g());
                                    current.setB(currentColors.b());
                                } else {
                                    current.setR((current.getR() + currentColors.r()) / 2);
                                    current.setG((current.getG() + currentColors.g()) / 2);
                                    current.setB((current.getB() + currentColors.b()) / 2);
                                }
                                current.incrementHitCounter();
                            }
                        }
                    }
                }
            }
        }
    }

    public void createFractal(int sample, int iterartion, int symmetry,
        List<FractalTransformation> transformations, int threadCount) {

        if (sample < 1 || iterartion < 1 || symmetry < 1 || transformations.isEmpty() || threadCount < 1) {
            throw new IllegalArgumentException("Wrong argument");
        }
        threads = new Thread[threadCount];

        int finalSample = sample / threadCount;

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> generate(finalSample, iterartion, symmetry, transformations));
            threads[i].start();
        }

        try {
            for (int i = 0; i < threadCount; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void render(Graphics g) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                g.setColor(new Color(pixels[x][y].getR(), pixels[x][y].getG(), pixels[x][y].getB()));
                g.drawLine(x, y, x, y);
            }
        }
    }


    private void gammaLog() {
        double max = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (pixels[x][y].getHitCounter() != 0) {
                    pixels[x][y].setNormal(Math.log10(pixels[x][y].getHitCounter()));
                    if (pixels[x][y].getNormal() > max) {
                        max = pixels[x][y].getNormal();
                    }
                }
            }
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[x][y].setNormal(pixels[x][y].getNormal() / max);
                pixels[x][y].setR((int)
                    (pixels[x][y].getR() * Math.pow(pixels[x][y].getNormal(), (1 / CoefficientValue.GAMMA))));
                pixels[x][y].setG((int)
                    (pixels[x][y].getG() * Math.pow(pixels[x][y].getNormal(), (1 / CoefficientValue.GAMMA))));
                pixels[x][y].setB((int)
                    (pixels[x][y].getB() * Math.pow(pixels[x][y].getNormal(), (1 / CoefficientValue.GAMMA))));
            }
        }
    }

    public void save(String fractalName, FileFormat format) {

        if (fractalName.isEmpty()) {
            throw new IllegalArgumentException("Wrong filename");
        }

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        gammaLog();
        render(g);
        try {
            switch (format) {
                case BMP -> ImageIO.write(image, "bmp", new File(fractalName + ".bmp"));
                case JPEG -> ImageIO.write(image, "jpeg", new File(fractalName + ".jpeg"));
                case PNG -> ImageIO.write(image, "png", new File(fractalName + ".png"));
                default -> {

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isPointInWindow(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    private boolean isPointInInterval(Point point) {
        return point.x() >= CoefficientValue.XMIN && point.x() <= CoefficientValue.XMAX
            && point.y() >= CoefficientValue.YMIN && point.y() <= CoefficientValue.YMAX;
    }

}
