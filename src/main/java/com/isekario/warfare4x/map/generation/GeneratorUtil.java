package com.isekario.warfare4x.map.generation;

import com.isekario.warfare4x.map.GridType;
import com.isekario.warfare4x.util.PerlinNoise;

public final class GeneratorUtil
{
    private static int mapWidth;
    private static int mapHeight;

    public static int getMapWidth() {
        return mapWidth;
    }

    public static void setMapWidth(int mapWidth) {
        GeneratorUtil.mapWidth = mapWidth;
    }

    public static int getMapHeight() {
        return mapHeight;
    }

    public static void setMapHeight(int mapHeight) {
        GeneratorUtil.mapHeight = mapHeight;
    }

    //Observed min is -0.6 / max is 0.6
    private static GridType valueToGrid(double value) {
        if(value < -0.3)
            return GridType.OCEAN_DEEP;
        if(value < 0)
            return GridType.OCEAN_COAST;
        if(value < 0.3)
            return GridType.BEACH;
        return GridType.GRASS;
    }

    /**
     *
     * @param zoom - The frequency of the noise
     * @return
     */
    public static GridType[][] generate(int zoom) {
        GridType[][] grid = new GridType[mapWidth][mapHeight];
        double nx, ny;

        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                nx = x/(double)mapWidth - 0.5;
                ny = y/(double)mapHeight - 0.5;
                grid[y][x] = valueToGrid(PerlinNoise.noise(nx*zoom, ny*zoom));
            }
        }
        return grid;
    }
}
