package com.github.droiddude.fltpot.renderer.entity.layers;

import com.github.droiddude.fltpot.Main;
import com.github.droiddude.fltpot.item.Items;
import com.github.droiddude.fltpot.model.WingsModel;
import com.github.droiddude.fltpot.registry.ModelLayerRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WingsLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation WINGS_LOCATION = new ResourceLocation(Main.MOD_ID,"textures/entity/wings.png");
    private final WingsModel<T> wingsModel;

    public WingsLayer(RenderLayerParent<T, M> pRenderer, EntityModelSet pModelSet) {

        super(pRenderer);

        this.wingsModel = new WingsModel<>(pModelSet.bakeLayer(ModelLayerRegistry.WINGS));

    }


    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

        ItemStack itemstack = pLivingEntity.getItemBySlot(EquipmentSlot.CHEST);

        if (shouldRender(itemstack, pLivingEntity)) {

            ResourceLocation resourcelocation = getWingsTexture(itemstack, pLivingEntity);

            pMatrixStack.pushPose();
            pMatrixStack.translate(0.0F, 0.0F, 0.125F);
            this.getParentModel().copyPropertiesTo(this.wingsModel);
            this.wingsModel.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
            VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(pBuffer, RenderType.armorCutoutNoCull(resourcelocation), false, itemstack.hasFoil());
            this.wingsModel.renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            pMatrixStack.popPose();

        }

    }


    public boolean shouldRender(ItemStack stack, T entity) {

        return stack.getItem() == Items.WINGS.get();

    }


    public ResourceLocation getWingsTexture(ItemStack stack, T entity) {

        return WINGS_LOCATION;

    }

}
