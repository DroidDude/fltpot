package com.github.droiddude.fltpot.item;

import com.github.droiddude.fltpot.effect.Effects;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

public class WingsItem extends Item implements Equipable {

    public WingsItem(Properties properties) {

        super(properties);

        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);

    }

    public static boolean isFlyEnabled(ItemStack itemStack) {
        return itemStack.getDamageValue() < itemStack.getMaxDamage() - 1;
    }

    public boolean isValidRepairItem(ItemStack pStack, ItemStack pRepairCandidate) {
        return pRepairCandidate.is(com.github.droiddude.fltpot.item.Items.MAGIC_INGOT.get());
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        return this.swapWithEquipmentSlot(this, pLevel, pPlayer, pUsedHand);
    }

    public boolean canWingsFly(ItemStack stack, net.minecraft.world.entity.LivingEntity entity) {
        return WingsItem.isFlyEnabled(stack);
    }

    public boolean wingsFlightTick(ItemStack stack, net.minecraft.world.entity.LivingEntity entity, int flightTicks) {

        if (!entity.level().isClientSide) {

            int nextFlightTick = flightTicks + 1;

            if (nextFlightTick % 10 == 0) {

                if (nextFlightTick % 20 == 0) {

                    stack.hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(EquipmentSlot.CHEST));

                }

            }

        }

        return true;

    }

    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_ELYTRA;
    }

    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.CHEST;
    }

    private int tick = 0;
    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {

        if(player.level().isClientSide) return;

        if (this.canWingsFly(stack, player)) {

            if (player.getAbilities().flying && !player.isPassenger() && !player.hasEffect(Effects.LEVITATION)) tick++;
            else tick = 0;

        } else tick = 0;

        this.wingsFlightTick(stack, player, tick);

    }

}
