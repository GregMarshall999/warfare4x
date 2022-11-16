package com.isekario.warfare4x.util.entityfactories;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.isekario.warfare4x.map.MapType.BEACH;
import static com.isekario.warfare4x.map.MapType.GRASS;
import static com.isekario.warfare4x.map.MapType.OCEAN_COAST;
import static com.isekario.warfare4x.map.MapType.OCEAN_DEEP;
import static com.isekario.warfare4x.map.MapUtil.getMapUnitSize;

public final class MapFactory implements EntityFactory
{
    @Spawns("oceanDeep")
    public Entity newOceanDeep(SpawnData data) {
        return entityBuilder()
                .from(data)
                .view(new Rectangle(getMapUnitSize(), getMapUnitSize(), OCEAN_DEEP.getColor()))
                .build();
    }

    @Spawns("oceanCoast")
    public Entity newOceanCoast(SpawnData data) {
        return entityBuilder()
                .from(data)
                .view(new Rectangle(getMapUnitSize(), getMapUnitSize(), OCEAN_COAST.getColor()))
                .build();
    }

    @Spawns("beach")
    public Entity newBeach(SpawnData data) {
        return entityBuilder()
                .from(data)
                .view(new Rectangle(getMapUnitSize(), getMapUnitSize(), BEACH.getColor()))
                .build();
    }

    @Spawns("grass")
    public Entity newGrass(SpawnData data) {
        return entityBuilder()
                .from(data)
                .view(new Rectangle(getMapUnitSize(), getMapUnitSize(), GRASS.getColor()))
                .build();
    }
}