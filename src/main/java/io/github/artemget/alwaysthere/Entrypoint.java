package io.github.artemget.alwaysthere;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Entrypoint {
    private static Point position;

    public static void main(String[] args) {
        position = MouseInfo.getPointerInfo().getLocation();
        Executors.newScheduledThreadPool(1)
            .scheduleWithFixedDelay(
                () -> {
                    if (!new MoleMouse(position).detected()) {
                        position = new ActiveMouse().act();
                        System.out.println("not detect user activity - moving");
                        return;
                    }
                    System.out.println("user activity detected - skip moving");
                    position = MouseInfo.getPointerInfo().getLocation();
                },
                0,
                30,
                TimeUnit.SECONDS
            );
    }
}
