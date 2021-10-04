package eu.newaustrianservers.fltpot.block;

import eu.newaustrianservers.fltpot.Main;
import net.minecraft.block.Block;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Blocks extends net.minecraft.block.Blocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Main.MODID);

    public static final RegistryObject<Block> STRANGE_DUST_ORE = BLOCKS.register("strange_dust_ore", () -> new StrangeDustOre(Block.Properties.from(DIAMOND_ORE).hardnessAndResistance(3f).harvestLevel(3).harvestTool(ToolType.PICKAXE)));

}
