package com.github.droiddude.fltpot;

import com.github.droiddude.fltpot.advancements.CriteriaTriggers;
import com.github.droiddude.fltpot.block.Blocks;
import com.github.droiddude.fltpot.creativetab.CreativeTab;
import com.github.droiddude.fltpot.effect.Effects;
import com.github.droiddude.fltpot.item.Items;
import com.github.droiddude.fltpot.potion.Potions;
import com.github.droiddude.fltpot.registry.ModelLayerRegistry;
import com.github.droiddude.fltpot.renderer.entity.layers.WingsLayer;
import com.github.droiddude.fltpot.renderer.item.ItemProperties;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

@Mod(Main.MOD_ID)
public class Main{

    public static final String MOD_ID = "fltpot";
    public static final Logger LOGGER = LogManager.getLogger();


    public Main(){

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        if (FMLEnvironment.dist == Dist.CLIENT) {

            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onAddLayers);
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerLayerDefinitions);

        }

        CreativeTab.TABS.register(FMLJavaModLoadingContext.get().getModEventBus());
        LOGGER.info("Registered Creative Tabs.");

        Effects.EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        LOGGER.info("Registered Effects.");

        Potions.POTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
        LOGGER.info("Registered Potions.");

        Items.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        LOGGER.info("Registered Items.");

        Blocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        LOGGER.info("Registered Blocks.");

    }


    private void setup(final FMLCommonSetupEvent event) {

        Potions.addBrewingRecipe();
        LOGGER.info("Added Brewing Recipes.");

        if (FMLEnvironment.dist == Dist.CLIENT) {

            ItemProperties.registerProperties();
            LOGGER.info("Registered Item Properties.");

        }

        CriteriaTriggers.registerCriteriaTriggers();
        LOGGER.info("Registered Criteria Triggers.");

        LOGGER.info("Setup done.");

    }

    @OnlyIn(Dist.CLIENT)
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void onAddLayers(EntityRenderersEvent.AddLayers event) {

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


    @OnlyIn(Dist.CLIENT)
    @SuppressWarnings("UnstableApiUsage")
    public void registerLayerDefinitions(EntityRenderersEvent.RegisterRenderers event) {

        Map<ModelLayerLocation, LayerDefinition> layers = ModelLayerRegistry.createLayerDefinitions();

        layers.forEach((location, definition) -> ForgeHooksClient.registerLayerDefinition(location, () -> definition));
        Main.LOGGER.info("Registered Renderer Layers.");

    }

}