package eu.newaustrianservers.fltpot.item;

import eu.newaustrianservers.fltpot.Main;
import eu.newaustrianservers.fltpot.block.Blocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Items extends net.minecraft.world.item.Items {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    //items
    public static final RegistryObject<Item> STRANGE_DUST = ITEMS.register("strange_dust", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> MAGIC_POWDER = ITEMS.register("magic_powder", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_BREWING)));
    public static final RegistryObject<Item> MAGIC_NUGGET = ITEMS.register("magic_nugget", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> MAGIC_INGOT = ITEMS.register("magic_ingot", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));

    //blocks
    public static final RegistryObject<BlockItem> STRANGE_DUST_ORE = ITEMS.register("strange_dust_ore", () -> new BlockItem(Blocks.STRANGE_DUST_ORE.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> DEEPSLATE_STRANGE_DUST_ORE = ITEMS.register("deepslate_strange_dust_ore", () -> new BlockItem(Blocks.DEEPSLATE_STRANGE_DUST_ORE.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

}
