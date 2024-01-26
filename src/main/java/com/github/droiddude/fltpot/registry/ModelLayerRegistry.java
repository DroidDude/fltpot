package com.github.droiddude.fltpot.registry;

import com.github.droiddude.fltpot.Main;
import com.github.droiddude.fltpot.model.WingsModel;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class ModelLayerRegistry {

    public static final ModelLayerLocation WINGS = register("wings");

    public static Map<ModelLayerLocation, LayerDefinition> createLayerDefinitions() {

        ImmutableMap.Builder<ModelLayerLocation, LayerDefinition> builder = ImmutableMap.builder();

        builder.put(WINGS, WingsModel.createLayer());

        return builder.build();

    }

    private static ModelLayerLocation register(String path){

        return new ModelLayerLocation(new ResourceLocation(Main.MOD_ID, path), "main");

    }

}
