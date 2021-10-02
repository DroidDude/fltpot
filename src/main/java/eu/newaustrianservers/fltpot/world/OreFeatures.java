package eu.newaustrianservers.fltpot.world;

import com.google.common.collect.ImmutableList;
import eu.newaustrianservers.fltpot.Main;
import eu.newaustrianservers.fltpot.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ReplaceBlockConfiguration;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class OreFeatures{

    public static final ImmutableList<OreConfiguration.TargetBlockState> ORE_STRANGE_DUST_TARGET_LIST = ImmutableList.of(OreConfiguration.target(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, Blocks.STRANGE_DUST_ORE.get().defaultBlockState()), OreConfiguration.target(OreConfiguration.Predicates.DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_STRANGE_DUST_ORE.get().defaultBlockState()));

    public static final ConfiguredFeature<?, ?> FEATURE_STRANGE_DUST_ORE = Feature.ORE.configured(new OreConfiguration(ORE_STRANGE_DUST_TARGET_LIST, 5)).rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(7)).squared();

    //public static final DeferredRegister<Feature<?>> ORES = DeferredRegister.create(ForgeRegistries.FEATURES, Main.MODID);

    //public static final RegistryObject<Feature<?>> ORE_STRANGE_DUST = ORES.register("ore_strange_dust", () -> FEATURE_STRANGE_DUST_ORE.feature);

}
