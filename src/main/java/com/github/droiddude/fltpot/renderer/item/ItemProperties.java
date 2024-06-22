package com.github.droiddude.fltpot.renderer.item;

import com.github.droiddude.fltpot.item.Items;
import com.github.droiddude.fltpot.item.WingsItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ItemProperties {

    public static void registerProperties() {

        net.minecraft.client.renderer.item.ItemProperties.register(Items.WINGS.get(), ResourceLocation.withDefaultNamespace("broken"), (p_174590_, p_174591_, p_174592_, p_174593_) -> WingsItem.isFlyEnabled(p_174590_) ? 0.0F : 1.0F);

    }

}
