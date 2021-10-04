package eu.newaustrianservers.fltpot.world;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber
public class OreGeneration {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void gen() {
        for (Biome biome : ForgeRegistries.BIOMES) {
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, OreFeatures.FEATURE_STRANGE_DUST_ORE);
        }
    }

}
