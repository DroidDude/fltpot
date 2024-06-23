package com.github.droiddude.fltpot.event;

import com.github.droiddude.fltpot.Main;
import com.github.droiddude.fltpot.registry.ModelLayerRegistry;
import com.github.droiddude.fltpot.renderer.entity.layers.WingsLayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.EntityRenderersEvent;

import java.util.Map;

public class ClientEvents {

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {

        event.getSkins().forEach(skin -> {

            PlayerRenderer renderer = event.getPlayerSkin(skin);

            if (renderer != null) renderer.addLayer(new WingsLayer<>((RenderLayerParent) renderer, Minecraft.getInstance().getEntityModels()));

        });
        Main.LOGGER.info("Added Render Layers to Entity Renderer.");

        Minecraft.getInstance().getEntityRenderDispatcher().renderers.forEach((entityType, entityRenderer) -> {

            if (entityRenderer instanceof LivingEntityRenderer<?,?> livingEntityRenderer && !(livingEntityRenderer instanceof PlayerRenderer)) {

                livingEntityRenderer.addLayer(new WingsLayer<>((RenderLayerParent) livingEntityRenderer, Minecraft.getInstance().getEntityModels()));

            }

        });
        Main.LOGGER.info("Added Render Layers to Player Renderer.");

    }


    @SuppressWarnings("UnstableApiUsage")
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterRenderers event) {

        Map<ModelLayerLocation, LayerDefinition> layers = ModelLayerRegistry.createLayerDefinitions();

        layers.forEach((location, definition) -> ForgeHooksClient.registerLayerDefinition(location, () -> definition));
        Main.LOGGER.info("Registered Renderer Layers.");

    }

}
