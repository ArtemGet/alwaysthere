package io.github.artemget.alwaysthere;

import java.awt.MouseInfo;
import java.awt.Point;

public class MoleMouse implements Mole {
    private final Point position;

    public MoleMouse(Point position) {
        this.position = position;
    }

    @Override
    public boolean detected() {
        return !MouseInfo.getPointerInfo().getLocation().equals(this.position);
    }
}
