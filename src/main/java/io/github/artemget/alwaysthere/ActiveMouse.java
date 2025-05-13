package io.github.artemget.alwaysthere;

import java.awt.*;
import java.util.Random;
public class ActiveMouse implements Active {

    @Override
    public Point act() {
        DisplayMode display = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getDefaultScreenDevice()
            .getDisplayMode();
        try {
            Point point = new Point(
                new Random().nextInt(display.getWidth()),
                new Random().nextInt(display.getHeight())
            );
            smoothMove(
                new Robot(),
                MouseInfo.getPointerInfo().getLocation(),
                point
            );
            return point;
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    private static void smoothMove(Robot robot, Point start, Point end) {
        double dx = end.x - start.x;
        double dy = end.y - start.y;

        for (int i = 1; i <= 100; i++) {
            double ratio = (double) i / 100;
            // Используем квадратичную функцию для более естественного движения
            ratio = easeInOutQuad(ratio);

            int x = (int) (start.x + dx * ratio);
            int y = (int) (start.y + dy * ratio);

            robot.mouseMove(x, y);

            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static double easeInOutQuad(double t) {
        return t < 0.5 ? 2 * t * t : 1 - Math.pow(-2 * t + 2, 2) / 2;
    }
}
