package eu.newaustrianservers.fltpot.world;

import eu.newaustrianservers.fltpot.block.Blocks;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;

public class OreFeatures {

    public static final ConfiguredFeature<?, ?> FEATURE_STRANGE_DUST_ORE = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.STRANGE_DUST_ORE.get().getDefaultState(), 5)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(2, 1, 0, 10)));

}
