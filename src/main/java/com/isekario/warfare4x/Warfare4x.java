package com.isekario.warfare4x;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.MenuItem;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.localization.Language;
import com.isekario.warfare4x.map.generation.MapGeneratorUtil;
import com.isekario.warfare4x.util.Util;
import com.isekario.warfare4x.util.entityfactories.MapFactory;
import javafx.geometry.Orientation;
import javafx.scene.control.Slider;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;

public class Warfare4x extends GameApplication {

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.set3D(false);
        gameSettings.setApplicationMode(ApplicationMode.DEVELOPER); //TODO: set to release
        gameSettings.setCloseConfirmation(false); //TODO: set to true
        gameSettings.setCredits(List.of("Author: Isekario"));
        gameSettings.setDefaultLanguage(Language.ENGLISH);
        gameSettings.setDeveloperMenuEnabled(true);
        gameSettings.setEnabledMenuItems(EnumSet.of(MenuItem.SAVE_LOAD));
        gameSettings.setFullScreenAllowed(true);
        gameSettings.setHeightFromRatio(1.5);
        gameSettings.setIntroEnabled(false); //TODO: set to true
        gameSettings.setMainMenuEnabled(false); //TODO: set to true
        gameSettings.setManualResizeEnabled(true);
        gameSettings.setPreserveResizeRatio(true);
        gameSettings.setSingleStep(true);
        gameSettings.setTitle("Warfare 4x");
        gameSettings.setVersion(Util.getVERSION());
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        getGameWorld().addEntityFactory(new MapFactory());
        MapGeneratorUtil.setMapWidth(50);
        MapGeneratorUtil.setMapHeight(50);
        vars.put("MAP", MapGeneratorUtil.generate(20));
    }

    @Override
    protected void initUI() {
        //Slider ui
        Slider mapGeneratorZoom = new Slider(0.0, 10.0, 5.0);
        mapGeneratorZoom.setOrientation(Orientation.HORIZONTAL);
        FXGL.addUINode(mapGeneratorZoom, FXGL.getAppWidth() - 200, 100);
    }

    @Override
    protected void initGame() {
        //Draw map
        /*Entity[][] map = FXGL.geto("MAP");
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                FXGL.entityBuilder()
                        .at(x*MapUtil.getMapUnitSize(), y*MapUtil.getMapUnitSize())
                        .view(new Rectangle(MapUtil.getMapUnitSize(), MapUtil.getMapUnitSize(), map[x][y].getColor()))
                        .buildAndAttach();
            }
        }*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}
