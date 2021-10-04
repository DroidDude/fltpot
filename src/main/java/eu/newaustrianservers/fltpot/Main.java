package eu.newaustrianservers.fltpot;

import eu.newaustrianservers.fltpot.block.Blocks;
import eu.newaustrianservers.fltpot.effect.Effects;
import eu.newaustrianservers.fltpot.item.Items;
import eu.newaustrianservers.fltpot.potion.Potions;
import eu.newaustrianservers.fltpot.world.OreGeneration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Main.MODID)
public class Main {

    public static final String MODID = "fltpot";
    public static final Logger LOGGER = LogManager.getLogger();

    public Main(){

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        Blocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        LOGGER.info("Registered Blocks.");
        Items.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        LOGGER.info("Registered Items.");
        Effects.EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        LOGGER.info("Registered Effects.");
        Potions.POTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
        LOGGER.info("Registered Potions.");
    }

    private void setup(final FMLCommonSetupEvent event) {

        Potions.addBrewingRecipe();
        OreGeneration.gen();

    }


}
