package com.github.DroidDude.fltpot.creativetab;

import com.github.DroidDude.fltpot.Main;
import com.github.DroidDude.fltpot.item.Items;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CreativeTab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Main.MODID);

    public static final List<Supplier<? extends ItemLike>> FLIGHT_POTION_TAB_ITEMS = new ArrayList<>();

    public static final RegistryObject<CreativeModeTab> Flight_Potion_TAB = TABS.register("flight_potion_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.flight_potion_tab"))
                    .icon(Items.STRANGE_DUST.get()::getDefaultInstance)
                    .displayItems((displayParameter, output) ->
                            FLIGHT_POTION_TAB_ITEMS.forEach(itemLike -> output.accept(itemLike.get())))
                    .build());

    public static <T extends Item> RegistryObject<T> addToTab(RegistryObject<T> itemLike) {
        FLIGHT_POTION_TAB_ITEMS.add(itemLike);
        return itemLike;
    }

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.getEntries().putAfter(Items.DEEPSLATE_DIAMOND_ORE.getDefaultInstance(), Items.STRANGE_DUST_ORE.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.STRANGE_DUST_ORE.get().getDefaultInstance(), Items.DEEPSLATE_STRANGE_DUST_ORE.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.getEntries().putAfter(Items.GOLD_NUGGET.getDefaultInstance(), Items.MAGIC_NUGGET.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.NETHERITE_INGOT.getDefaultInstance(), Items.MAGIC_INGOT.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.DISC_FRAGMENT_5.getDefaultInstance(), Items.STRANGE_DUST.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.GHAST_TEAR.getDefaultInstance(), Items.MAGIC_POWDER.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
}
