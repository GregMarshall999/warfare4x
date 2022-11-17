package com.isekario.warfare4x.map.generation;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.level.text.TextLevelLoader;
import com.isekario.warfare4x.util.PerlinNoise;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.almasb.fxgl.dsl.FXGL.spawn;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;
import static com.isekario.warfare4x.map.MapUtil.getMapUnitSize;
import static com.isekario.warfare4x.util.Util.getAssetManager;
import static com.isekario.warfare4x.util.Util.getAssetsLevelsDir;
import static com.isekario.warfare4x.util.Util.getMap;
import static com.isekario.warfare4x.util.Util.getTerrainFile;
import static com.isekario.warfare4x.util.Util.setMap;

public final class MapGeneratorUtil
{
    private static int mapWidth;
    private static int mapHeight;
    private static int generationZoom;

    //region getters/setters
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

    public static int getGenerationZoom() {
        return generationZoom;
    }

    public static void setGenerationZoom(int generationZoom) {
        MapGeneratorUtil.generationZoom = generationZoom;
    }
    //endregion

    /**
     * Takes a noise value and passes it through a terrain generator filter
     * @param value - noise value
     * @return - character picked for map generation
     */
    private static char valueToChar(double value) {
        if(value < -2.0/3) //deep ocean max level
            return 'd';
        if(value < -0.75/3) //coast ocean max level
            return 'c';
        if(value < -0.5/3) //beach max level
            return 'b';
        if (value < 1.0/3) //plain max level
            return 'p';
        if (value < 2.5/3) //hill max level
            return 'h';
        return 'm'; //mountain level
    }

    /**
     * Terrain generator.
     * Updates the current terrain file in the assets/levels folder with new generation data
     */
    public static void generateTerrainFile() {
        StringBuilder mapContent = new StringBuilder();
        double nx, ny;

        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                nx = x / (double) mapWidth - 0.5;
                ny = y / (double) mapHeight - 0.5;
                mapContent.append(valueToChar(PerlinNoise.noise(nx*generationZoom, ny*generationZoom)));
            }
            mapContent.append("\n");
        }

        try {
            getAssetManager().saveStringToAsset(mapContent, getAssetsLevelsDir(), getTerrainFile());
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateMap() {
        setMap(getAssetLoader().loadLevel(getTerrainFile(), new TextLevelLoader(10, 10, '0')));
        getGameWorld().setLevel(getMap());
    }

    //region depreciated
    /**
     * Takes a noise value and passes it through a terrain generator filter
     * Values appear to fluctuate between -1.0 and 1.0
     * @param value - Noise value
     * @param y - entity y coordinate
     * @param x - entity x coordinate
     * @return - Terrain type from the filtered noise
     */
    @Deprecated
    private static Entity valueToEntity(double value, double y, double x) {
        double xPos = x*getMapUnitSize();
        double yPos = y*getMapUnitSize();

        if(value < -2.0/3)
            return spawn("oceanDeep", xPos, yPos);
        if(value < -0.75/3)
            return spawn("oceanCoast", xPos, yPos);
        if(value < -0.5/3)
            return spawn("beach", xPos, yPos);
        if (value < 1.0/3)
            return spawn("plain", xPos, yPos);
        if (value < 2.5/3)
            return spawn("hill", xPos, yPos);
        return spawn("mountain", xPos, yPos);
    }

    /**
     * Generates terrain
     * @return - Map entity list
     */
    @Deprecated
    public static Entity[][] generate() {
        Entity[][] mapTiles = new Entity[mapWidth][mapHeight];
        double nx, ny;

        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                nx = x/(double)mapWidth - 0.5;
                ny = y/(double)mapHeight - 0.5;
                Entity entity = valueToEntity(PerlinNoise.noise(nx*generationZoom, ny*generationZoom), y, x);
                mapTiles[y][x] = entity;
            }
        }

        return mapTiles;
    }
    //endregion
}
