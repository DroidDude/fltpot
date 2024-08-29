package com.github.droiddude.fltpot.creativetab;

import com.github.droiddude.fltpot.Main;
import com.github.droiddude.fltpot.item.Items;
import com.github.droiddude.fltpot.potion.Potions;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CreativeTab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Main.MOD_ID);


    @SuppressWarnings("All")
    public static final RegistryObject<CreativeModeTab> FLIGHT_POTION_TAB = TABS.register("flight_potion_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.flight_potion_tab"))
                    .icon(Items.WINGS.get()::getDefaultInstance)
                    .displayItems((displayParameter, output) -> {


                        for (RegistryObject<Item> item : Items.ITEMS.getEntries()) {
                            output.accept(item.get().getDefaultInstance());
                        }

                        for (RegistryObject<Potion> potion : Potions.POTIONS.getEntries()) {
                            output.accept(PotionContents.createItemStack(Items.POTION, potion.getHolder().get()));
                        }

                        for (RegistryObject<Potion> splashPotion : Potions.POTIONS.getEntries()) {
                            output.accept(PotionContents.createItemStack(Items.SPLASH_POTION, splashPotion.getHolder().get()));
                        }

                        for (RegistryObject<Potion> lingeringPotion : Potions.POTIONS.getEntries()) {
                            output.accept(PotionContents.createItemStack(Items.LINGERING_POTION, lingeringPotion.getHolder().get()));
                        }

                        for (RegistryObject<Potion> tippedArrow : Potions.POTIONS.getEntries()) {
                            output.accept(PotionContents.createItemStack(Items.TIPPED_ARROW, tippedArrow.getHolder().get()));
                        }

                    })
                    .build());


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
            event.getEntries().putAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE.getDefaultInstance(), Items.WINGS_UPGRADE_SMITHING_TEMPLATE.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

        }

        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {

            event.getEntries().putAfter(Items.ELYTRA.getDefaultInstance(), Items.WINGS.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

        }

    }

}
