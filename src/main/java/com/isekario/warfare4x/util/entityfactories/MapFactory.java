package com.isekario.warfare4x.util.entityfactories;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.isekario.warfare4x.map.MapType;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.isekario.warfare4x.map.MapType.BEACH;
import static com.isekario.warfare4x.map.MapType.FOREST;
import static com.isekario.warfare4x.map.MapType.HILL;
import static com.isekario.warfare4x.map.MapType.MOUNTAIN;
import static com.isekario.warfare4x.map.MapType.PLAIN;
import static com.isekario.warfare4x.map.MapType.OCEAN_COAST;
import static com.isekario.warfare4x.map.MapType.OCEAN_DEEP;
import static com.isekario.warfare4x.map.MapUtil.getMapUnitSize;

public final class MapFactory implements EntityFactory
{
    private Entity buildEntity(SpawnData data, MapType type) {
        return entityBuilder()
                .from(data)
                .view(new Rectangle(getMapUnitSize(), getMapUnitSize(), type.getColor()))
                .zIndex(-1)
                .build();
    }

    @Spawns("oceanDeep")
    public Entity newOceanDeep(SpawnData data) {
        return buildEntity(data, OCEAN_DEEP);
    }

    @Spawns("oceanCoast")
    public Entity newOceanCoast(SpawnData data) {
        return buildEntity(data, OCEAN_COAST);
    }

    @Spawns("beach")
    public Entity newBeach(SpawnData data) {
        return buildEntity(data, BEACH);
    }

    @Spawns("plain")
    public Entity newPlain(SpawnData data) {
        return buildEntity(data, PLAIN);
    }

    @Spawns("hill")
    public Entity newHill(SpawnData data) {
        return buildEntity(data, HILL);
    }

    @Spawns("forest")
    public Entity newForest(SpawnData data) {
        return buildEntity(data, FOREST);
    }

    @Spawns("mountain")
    public Entity newMountain(SpawnData data) {
        return buildEntity(data, MOUNTAIN);
    }
}