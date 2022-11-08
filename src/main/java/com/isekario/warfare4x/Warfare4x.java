package com.isekario.warfare4x;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.MenuItem;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.localization.Language;
import com.isekario.warfare4x.map.GridType;
import com.isekario.warfare4x.map.MapUtil;
import com.isekario.warfare4x.map.generation.GeneratorUtil;
import com.isekario.warfare4x.util.Util;
import javafx.scene.shape.Rectangle;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;

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
        GeneratorUtil.setMapWidth(50);
        GeneratorUtil.setMapHeight(50);
        vars.put("MAP", GeneratorUtil.generate(20));
    }

    @Override
    protected void initGame() {
        GridType[][] map = FXGL.geto("MAP");

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                FXGL.entityBuilder()
                        .at(x*MapUtil.getGridSize(), y*MapUtil.getGridSize())
                        .view(new Rectangle(MapUtil.getGridSize(), MapUtil.getGridSize(), map[x][y].getColor()))
                        .buildAndAttach();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
