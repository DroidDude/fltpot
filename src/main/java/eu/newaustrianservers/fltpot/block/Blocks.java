package eu.newaustrianservers.fltpot.block;

import eu.newaustrianservers.fltpot.Main;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Blocks extends net.minecraft.world.level.block.Blocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MODID);

    public static final RegistryObject<Block> STRANGE_DUST_ORE = BLOCKS.register("strange_dust_ore", () -> new OreBlock(BlockBehaviour.Properties.copy(DIAMOND_ORE), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> DEEPSLATE_STRANGE_DUST_ORE = BLOCKS.register("deepslate_strange_dust_ore", () -> new OreBlock(BlockBehaviour.Properties.copy(DEEPSLATE_DIAMOND_ORE), UniformInt.of(3, 7)));

}
