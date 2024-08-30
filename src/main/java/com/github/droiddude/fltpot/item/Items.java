package com.github.droiddude.fltpot.item;

import com.github.droiddude.fltpot.Main;
import com.github.droiddude.fltpot.block.Blocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Items extends net.minecraft.world.item.Items {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);

    //blocks
    public static final RegistryObject<BlockItem> STRANGE_DUST_ORE = ITEMS.register("strange_dust_ore", () -> new BlockItem(Blocks.STRANGE_DUST_ORE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> DEEPSLATE_STRANGE_DUST_ORE = ITEMS.register("deepslate_strange_dust_ore", () -> new BlockItem(Blocks.DEEPSLATE_STRANGE_DUST_ORE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MAGIC_ALLOY_BLOCK = ITEMS.register("magic_alloy_block", () -> new BlockItem(Blocks.MAGIC_ALLOY_BLOCK.get(), new Item.Properties()));

    //items
    public static final RegistryObject<Item> STRANGE_DUST = ITEMS.register("strange_dust", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MAGIC_POWDER = ITEMS.register("magic_powder", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MAGIC_NUGGET = ITEMS.register("magic_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MAGIC_INGOT = ITEMS.register("magic_ingot", () -> new Item(new Item.Properties()));

    //wearables
    public static final RegistryObject<Item> WINGS = ITEMS.register("wings", () -> new WingsItem((new Item.Properties().durability(432).rarity(Rarity.RARE))));

    //templates
    public static final RegistryObject<Item> WINGS_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("wings_upgrade_smithing_template", WingsUpgradeItem::createWingsUpgradeTemplate);

}
