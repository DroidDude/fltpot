package com.github.droiddude.fltpot;

import com.github.droiddude.fltpot.advancements.CriteriaTriggers;
import com.github.droiddude.fltpot.block.Blocks;
import com.github.droiddude.fltpot.creativetab.CreativeTab;
import com.github.droiddude.fltpot.effect.Effects;
import com.github.droiddude.fltpot.event.ClientEvents;
import com.github.droiddude.fltpot.item.Items;
import com.github.droiddude.fltpot.potion.Potions;
import com.github.droiddude.fltpot.renderer.item.ItemProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Main.MOD_ID)
public class Main {

    public static final String MOD_ID = "fltpot";
    public static final Logger LOGGER = LogManager.getLogger();


    public Main(){

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        if (FMLEnvironment.dist == Dist.CLIENT) {

            FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientEvents::onAddLayers);
            FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientEvents::registerLayerDefinitions);

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

        if (FMLEnvironment.dist == Dist.CLIENT) {

            ItemProperties.registerProperties();
            LOGGER.info("Registered Item Properties.");

        }

        CriteriaTriggers.registerCriteriaTriggers();
        LOGGER.info("Registered Criteria Triggers.");

        LOGGER.info("Setup done.");

    }

}