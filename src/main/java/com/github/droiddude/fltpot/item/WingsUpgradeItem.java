package com.github.droiddude.fltpot.item;

import com.github.droiddude.fltpot.Main;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class WingsUpgradeItem extends SmithingTemplateItem {

    public WingsUpgradeItem(Component pAppliesTo,
                            Component pIngredients,
                            Component pUpgradeDescription,
                            Component pBaseSlotDescription,
                            Component pAdditionsSlotDescription,
                            List<ResourceLocation> pBaseSlotEmptyIcons,
                            List<ResourceLocation> pAdditionalSlotEmptyIcons,
                            FeatureFlag... pRequiredFeatures) {

        super(pAppliesTo, pIngredients, pUpgradeDescription, pBaseSlotDescription, pAdditionsSlotDescription, pBaseSlotEmptyIcons, pAdditionalSlotEmptyIcons, pRequiredFeatures);

    }
    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
    private static final Component WINGS_UPGRADE_APPLIES_TO = Component.translatable(
                    Util.makeDescriptionId("item", new ResourceLocation(Main.MOD_ID, "smithing_template.wings_upgrade.applies_to"))
            )
            .withStyle(DESCRIPTION_FORMAT);
    private static final Component WINGS_UPGRADE_INGREDIENTS = Component.translatable(
                    Util.makeDescriptionId("item", new ResourceLocation(Main.MOD_ID, "smithing_template.wings_upgrade.ingredients"))
            )
            .withStyle(DESCRIPTION_FORMAT);
    private static final Component WINGS_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(
            Util.makeDescriptionId("item", new ResourceLocation(Main.MOD_ID, "smithing_template.wings_upgrade.base_slot_description"))
    );
    private static final Component WINGS_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(
            Util.makeDescriptionId("item", new ResourceLocation(Main.MOD_ID, "smithing_template.wings_upgrade.additions_slot_description"))
    );
    private static final Component WINGS_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation(Main.MOD_ID, "wings_upgrade")))
            .withStyle(TITLE_FORMAT);
    private static final ResourceLocation EMPTY_SLOT_WINGS = new ResourceLocation(Main.MOD_ID, "item/empty_armor_slot_wings");
    private static final ResourceLocation EMPTY_SLOT_INGOT = new ResourceLocation("item/empty_slot_ingot");

    public static SmithingTemplateItem createWingsUpgradeTemplate() {
        return new SmithingTemplateItem(WINGS_UPGRADE_APPLIES_TO, WINGS_UPGRADE_INGREDIENTS, WINGS_UPGRADE, WINGS_UPGRADE_BASE_SLOT_DESCRIPTION, WINGS_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createWingsUpgradeIconList(), createWingsUpgradeMaterialList());
    }

    private static List<ResourceLocation> createWingsUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }

    private static List<ResourceLocation> createWingsUpgradeIconList() {
        return List.of(EMPTY_SLOT_WINGS);
    }

}
