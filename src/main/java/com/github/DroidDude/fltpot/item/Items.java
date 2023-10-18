package com.github.DroidDude.fltpot.item;

import com.github.DroidDude.fltpot.Main;
import com.github.DroidDude.fltpot.block.Blocks;
import com.github.DroidDude.fltpot.creativetab.CreativeTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Items extends net.minecraft.world.item.Items {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    //blocks
    public static final RegistryObject<BlockItem> STRANGE_DUST_ORE = CreativeTab.addToTab(ITEMS.register("strange_dust_ore", () -> new BlockItem(Blocks.STRANGE_DUST_ORE.get(), new Item.Properties())));
    public static final RegistryObject<BlockItem> DEEPSLATE_STRANGE_DUST_ORE = CreativeTab.addToTab(ITEMS.register("deepslate_strange_dust_ore", () -> new BlockItem(Blocks.DEEPSLATE_STRANGE_DUST_ORE.get(), new Item.Properties())));

    //items
    public static final RegistryObject<Item> STRANGE_DUST = CreativeTab.addToTab(ITEMS.register("strange_dust", () -> new Item(new Item.Properties())));
    public static final RegistryObject<Item> MAGIC_POWDER = CreativeTab.addToTab(ITEMS.register("magic_powder", () -> new Item(new Item.Properties())));
    public static final RegistryObject<Item> MAGIC_NUGGET = CreativeTab.addToTab(ITEMS.register("magic_nugget", () -> new Item(new Item.Properties())));
    public static final RegistryObject<Item> MAGIC_INGOT = CreativeTab.addToTab(ITEMS.register("magic_ingot", () -> new Item(new Item.Properties())));

}
