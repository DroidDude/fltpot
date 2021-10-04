package eu.newaustrianservers.fltpot.world;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGeneration {

    public static void gen() {
        for (Biome biome : ForgeRegistries.BIOMES) {
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, OreFeatures.FEATURE_STRANGE_DUST_ORE);
        }
    }

}
