package com.isekario.warfare4x.map;

import javafx.scene.paint.Color;

public enum GridType
{
    NULL(Color.BLACK),
    OCEAN_DEEP(Color.DARKBLUE),
    OCEAN_COAST(Color.BLUE),
    BEACH(Color.YELLOW),
    GRASS(Color.GREEN);

    final Color color;

    GridType(Color color)
    {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
