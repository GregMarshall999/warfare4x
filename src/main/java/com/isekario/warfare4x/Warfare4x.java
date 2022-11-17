package com.isekario.warfare4x;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.MenuItem;
import com.almasb.fxgl.localization.Language;
import com.isekario.warfare4x.map.generation.MapGeneratorUtil;
import com.isekario.warfare4x.util.Util;
import com.isekario.warfare4x.util.entityfactories.MapFactory;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.addUINode;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.isekario.warfare4x.map.generation.MapGeneratorUtil.generateTerrainFile;
import static com.isekario.warfare4x.map.generation.MapGeneratorUtil.setGenerationDetail;
import static com.isekario.warfare4x.map.generation.MapGeneratorUtil.setGenerationZoom;
import static com.isekario.warfare4x.map.generation.MapGeneratorUtil.updateMap;
import static com.isekario.warfare4x.util.PerlinNoise.regenerateSeed;
import static com.isekario.warfare4x.util.Util.getAssetManager;

public class Warfare4x extends GameApplication {

    @Override
    protected void onPreInit() {
        //directory checks
        try {
            getAssetManager().assetsDirCheck();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        //Map generation
        MapGeneratorUtil.setMapWidth(100);
        MapGeneratorUtil.setMapHeight(100);
        setGenerationZoom(10);
        setGenerationDetail(3);
        generateTerrainFile();
    }

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
    }

    @Override
    protected void initUI() {
        //Slider ui
        Slider mapGeneratorZoom = new Slider(0.0, 20.0, 10.0);
        mapGeneratorZoom.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            setGenerationZoom(newValue.intValue());
            generateTerrainFile();
            updateMap();
        });
        mapGeneratorZoom.setOrientation(Orientation.HORIZONTAL);
        addUINode(mapGeneratorZoom, getAppWidth() - 200, 100);

        //Seed regen UI
        Button regenSeed = new Button("Regenerate map");
        regenSeed.setOnAction(actionEvent -> {
            regenerateSeed();
            generateTerrainFile();
            updateMap();
        });
        addUINode(regenSeed, getAppWidth() - 200, 200);
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new MapFactory());
        updateMap();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
