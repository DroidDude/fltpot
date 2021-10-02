package eu.newaustrianservers.fltpot.world;

import eu.newaustrianservers.fltpot.block.Blocks;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class OreFeatures {

    public static final ConfiguredFeature<?, ?> FEATURE_STRANGE_DUST_ORE = Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.STRANGE_DUST_ORE.get().defaultBlockState(), 5)).range(7).squared();

}
