package com.isekario.warfare4x.map.generation;

import com.almasb.fxgl.entity.level.text.TextLevelLoader;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;
import static com.isekario.warfare4x.util.PerlinNoise.noise;
import static com.isekario.warfare4x.util.Util.getAssetManager;
import static com.isekario.warfare4x.util.Util.getAssetsLevelsDir;
import static com.isekario.warfare4x.util.Util.getMap;
import static com.isekario.warfare4x.util.Util.getTerrainFile;
import static com.isekario.warfare4x.util.Util.setMap;
import static java.lang.Math.pow;

public final class MapGeneratorUtil
{
    private static int mapWidth;
    private static int mapHeight;
    private static int generationZoom; //frequency
    private static int generationDetail; //octaves

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

    public static int getGenerationDetail() {
        return generationDetail;
    }

    public static void setGenerationDetail(int generationDetail) {
        MapGeneratorUtil.generationDetail = generationDetail;
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

    private static double noiseValueAt(double x, double y) {
        double n = 0.0;
        double octave;

        for (int i = 0; i < generationDetail; i++) {
            octave = pow(2, i);
            n += noise(x*generationZoom*octave, y*generationZoom*octave)/octave;
        }

        return n;
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
                mapContent.append(valueToChar(noiseValueAt(nx, ny)));
            }
            mapContent.append("\n");
        }

        try {
            getAssetManager().saveStringToAsset(mapContent, getAssetsLevelsDir(), getTerrainFile());
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reloads the map from the terrain file
     */
    public static void updateMap() {
        setMap(getAssetLoader().loadLevel(getTerrainFile(), new TextLevelLoader(10, 10, '0')));
        getGameWorld().setLevel(getMap());
    }
}
