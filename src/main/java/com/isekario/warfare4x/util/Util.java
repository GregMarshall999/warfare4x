package com.isekario.warfare4x.util;

import com.almasb.fxgl.entity.level.Level;
import com.isekario.warfare4x.util.assets.AssetManager;

public final class Util
{
    private static final AssetManager ASSET_MANAGER = new AssetManager();

    private static final String ASSETS_DIR = "/assets";
    private static final String ASSETS_LEVELS_DIR = ASSETS_DIR + "/levels";

    private static final String TERRAIN_FILE = "terrain.txt";

    private static final String VERSION = "1.0.0-SNAPSHOT";

    private static Level map;


    public static AssetManager getAssetManager() {
        return ASSET_MANAGER;
    }

    public static String getAssetsDir() {
        return ASSETS_DIR;
    }

    public static String getAssetsLevelsDir() {
        return ASSETS_LEVELS_DIR;
    }

    public static String getTerrainFile() {
        return TERRAIN_FILE;
    }

    public static String getVERSION() {
        return VERSION;
    }

    public static Level getMap() {
        return map;
    }

    public static void setMap(Level map) {
        Util.map = map;
    }
}
