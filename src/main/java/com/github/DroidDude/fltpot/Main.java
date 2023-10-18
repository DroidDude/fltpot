package com.github.DroidDude.fltpot;

import com.github.DroidDude.fltpot.block.Blocks;
import com.github.DroidDude.fltpot.creativetab.CreativeTab;
import com.github.DroidDude.fltpot.effect.Effects;
import com.github.DroidDude.fltpot.item.Items;
import com.github.DroidDude.fltpot.potion.Potions;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Main.MODID)
public class Main{

    public static final String MODID = "fltpot";
    public static final Logger LOGGER = LogManager.getLogger();

    public Main(){

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        Effects.EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        LOGGER.info("Registered Effects.");
        Potions.POTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
        LOGGER.info("Registered Potions.");
        Items.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        LOGGER.info("Registered Items.");
        Blocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        LOGGER.info("Registered Blocks.");
        CreativeTab.TABS.register(FMLJavaModLoadingContext.get().getModEventBus());
        LOGGER.info("Registered Creative Tabs.");

    }

    private void setup(final FMLCommonSetupEvent event) {

        Potions.addBrewingRecipe();
        LOGGER.info("Setup method registered.");

    }

}