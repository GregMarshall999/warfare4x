package com.isekario.warfare4x.map.generation;

import com.almasb.fxgl.entity.Entity;
import com.isekario.warfare4x.util.PerlinNoise;

import static com.almasb.fxgl.dsl.FXGL.spawn;
import static com.isekario.warfare4x.map.MapUtil.getMapUnitSize;

public final class MapGeneratorUtil
{
    private static int mapWidth;
    private static int mapHeight;

    public static int getMapWidth() {
        return mapWidth;
    }

    public static void setMapWidth(int mapWidth) {
        MapGeneratorUtil.mapWidth = mapWidth;
    }

    public static int getMapHeight() {
        return mapHeight;
    }

    public static void setMapHeight(int mapHeight) {
        MapGeneratorUtil.mapHeight = mapHeight;
    }

    /**
     * Takes a noise value and passes it through a terrain generator filter
     * Values appear to fluctuate between -1.0 and 1.0
     * @param value - Noise value
     * @return - Terrain type from the filtered noise
     */
    private static Entity valueToGrid(double value, double y, double x) {
        if(value < -0.5)
            return spawn("oceanDeep", x*getMapUnitSize(), y*getMapUnitSize());
        if(value < 0)
            return spawn("oceanCoast", x*getMapUnitSize(), y*getMapUnitSize());
        if(value < 0.5)
            return spawn("beach", x*getMapUnitSize(), y*getMapUnitSize());
        return spawn("grass", x*getMapUnitSize(), y*getMapUnitSize());
    }

    /**
     *
     * @param zoom - The frequency of the noise
     * @return
     */
    public static Entity[][] generate(int zoom) {
        Entity[][] mapTiles = new Entity[mapWidth][mapHeight];
        double nx, ny;

        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                nx = x/(double)mapWidth - 0.5;
                ny = y/(double)mapHeight - 0.5;
                Entity entity = valueToGrid(PerlinNoise.noise(nx*zoom, ny*zoom), y, x);
                mapTiles[y][x] = entity;
            }
        }
        return mapTiles;
    }
}
