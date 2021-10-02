package eu.newaustrianservers.fltpot;

import eu.newaustrianservers.fltpot.block.Blocks;
import eu.newaustrianservers.fltpot.effect.Effects;
import eu.newaustrianservers.fltpot.item.Items;
import eu.newaustrianservers.fltpot.potion.Potions;
import eu.newaustrianservers.fltpot.world.OreFeatures;
import net.minecraft.data.worldgen.SurfaceBuilders;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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

    }

    private void setup(final FMLCommonSetupEvent event) {

        Potions.addBrewingRecipe();
        LOGGER.info("Setup method registered.");

    }
}