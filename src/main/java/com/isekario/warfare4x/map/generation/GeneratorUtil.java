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

    /**
     * Takes a noise value and passes it through a terrain generator filter
     * Values appear to fluctuate between -1.0 and 1.0
     * @param value - Noise value
     * @return - Terrain type from the filtered noise
     */
    private static GridType valueToGrid(double value) {
        if(value < -0.5)
            return GridType.OCEAN_DEEP;
        if(value < 0)
            return GridType.OCEAN_COAST;
        if(value < 0.5)
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
