package eu.newaustrianservers.fltpot.world;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class OreGeneration {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void gen(BiomeLoadingEvent event) {

        BiomeGenerationSettingsBuilder generation = event.getGeneration();

        if (!event.getCategory().equals(Biome.BiomeCategory.NETHER)&&!event.getCategory().equals(Biome.BiomeCategory.THEEND)) {
            generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeatures.FEATURE_STRANGE_DUST_ORE);
        }
    }

}
