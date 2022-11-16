package com.isekario.warfare4x.map;

import javafx.scene.paint.Color;

public enum MapType
{
    OCEAN_DEEP(Color.DARKBLUE),
    OCEAN_COAST(Color.BLUE),
    BEACH(Color.YELLOW),
    PLAIN(Color.LIGHTGREEN),
    HILL(Color.GREEN),
    FOREST(Color.DARKGREEN),
    MOUNTAIN(Color.GRAY);

    final Color color;

    MapType(Color color)
    {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
