package com.github.droiddude.fltpot.event;

import com.github.droiddude.fltpot.Main;
import com.github.droiddude.fltpot.advancements.CriteriaTriggers;
import com.github.droiddude.fltpot.effect.Effects;
import com.github.droiddude.fltpot.item.Items;
import com.github.droiddude.fltpot.item.WingsItem;
import com.github.droiddude.fltpot.potion.Potions;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Events {

    private static final EquipmentSlot chest = EquipmentSlot.CHEST;


    private static boolean equipped(LivingEntity player, EquipmentSlot slot, Item item) {

        return item == player.getItemBySlot(slot).getItem();

    }

    public static boolean canFly(LivingEntity player, EquipmentSlot slot) {

        Item item = player.getItemBySlot(slot).getItem();

        if (item instanceof WingsItem) return ((WingsItem) item).canWingsFly(player.getItemBySlot(slot), player);

        return false;

    }

    private static Vec3 toVec3(String input) {

        input = input.substring(2, input.length() - 2).replace(" ", "");
        String[] vecString = input.split(",");
        return new Vec3(Double.parseDouble(vecString[0]), Double.parseDouble(vecString[1]), Double.parseDouble(vecString[2]));

    }


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void doFlying(TickEvent.PlayerTickEvent event){

        Player player = event.player;
        CompoundTag tag = player.getPersistentData();
        boolean wasFlying = tag.getBoolean("wasFlying");

        if(!player.isCreative() && !player.isSpectator()){

            if((player.hasEffect(Effects.FLIGHT.getHolder().get()) || equipped(player, chest, Items.WINGS.get()) && canFly(player, chest)) && !player.hasEffect(Effects.LEVITATION)){

                if(!player.getAbilities().mayfly){

                    player.getAbilities().mayfly = true;
                    tag.putBoolean("wasFlying", true);
                    player.onUpdateAbilities();

                }

                if((!player.onGround() || !player.isInWaterOrBubble() && equipped(player, chest, Items.WINGS.get())) && player.getAbilities().flying && player.getY() <= player.yOld && player.level().isClientSide){

                    if(!Minecraft.getInstance().options.keyJump.isDown()){
                        Vec3 vec = new Vec3(0d, -0.1d, 0d);
                        player.move(MoverType.SELF, vec);
                    }

                } else if((player.onGround() || player.isInWaterOrBubble() && equipped(player, chest, Items.WINGS.get()))){

                    player.getAbilities().flying = false;
                    player.onUpdateAbilities();

                }

            } else if(wasFlying && (!player.hasEffect(Effects.FLIGHT.getHolder().get()) || !equipped(player, chest, Items.WINGS.get()) || !canFly(player, chest) || player.hasEffect(Effects.LEVITATION))) {

                player.getAbilities().mayfly = false;
                player.getAbilities().flying = false;
                tag.putBoolean("wasFlying", false);
                player.onUpdateAbilities();

            }

        }

    }

    @SubscribeEvent
    public static void addNBTData(PlayerEvent.PlayerLoggedInEvent event){

        Player player = event.getEntity();
        CompoundTag tag = player.getPersistentData();

        if (tag.get("wasFlying") == null) {

            tag.putBoolean("wasFlying", false);

        }

        if (tag.get("flightTicks") == null) {

            tag.putInt("flightTicks", 0);

        }

    }

    @SubscribeEvent
    public static void fallDamageFlightPotion(PlayerFlyableFallEvent event) {

        double distance = event.getDistance();
        Player player = event.getEntity();

        if (distance > player.getAttributeValue(Attributes.SAFE_FALL_DISTANCE) && !player.isCreative() && (player.hasEffect(Effects.FLIGHT.getHolder().get()) || equipped(player, chest, Items.WINGS.get()) && canFly(player, chest))) {

            float damage = (float) Math.floor(distance) - ((float) player.getAttributeValue(Attributes.SAFE_FALL_DISTANCE) - 1f);
            player.hurt(player.damageSources().fall(), damage);

        }

        if (distance >= 2.0F) {
            player.awardStat(Stats.FALL_ONE_CM, (int)Math.round(distance * 100.0));
        }

    }


    @SubscribeEvent
    public static void flightCheck(TickEvent.PlayerTickEvent event) {

        Player player = event.player;
        CompoundTag tag = player.getPersistentData();

        if (player.isCreative() || player.isSpectator()) return;

        if(tag.get("flightStartPos") == null && player.getAbilities().flying) {

            tag.putString("flightStartPos", player.position().toString());
            tag.putInt("flightTicks", tag.getInt("flightTicks")+1);

        } else if (!player.getAbilities().flying) {

            tag.remove("flightStartPos");

        } else if (player.getAbilities().flying) {

            tag.putInt("flightTicks", tag.getInt("flightTicks")+1);

        }

        if (tag.get("flightStartPos") != null && player instanceof ServerPlayer serverPlayer) {

            CriteriaTriggers.FLIGHT.trigger(serverPlayer, toVec3(tag.get("flightStartPos").toString()), tag.getInt("flightTicks"));

        }

    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void addBrewingRecipes(BrewingRecipeRegisterEvent event) {

        event.addRecipe(new Potions.FlightPotion(null, null, null));
        event.addRecipe(new Potions.LevitationPotion(null, null, null));
        event.addRecipe(new Potions.LongFlightPotion(null, null, null));
        Main.LOGGER.info("Added Brewing Recipes.");

    }

    @SubscribeEvent
    public static void wingsTick(TickEvent.PlayerTickEvent event) {

        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;

        if(player.level().isClientSide || player.isCreative() || player.isSpectator() || !player.getAbilities().flying || !equipped(player, chest, Items.WINGS.get())) return;

        WingsItem wings = (WingsItem) player.getItemBySlot(chest).getItem();

        wings.onTick(player.getItemBySlot(chest), player);

    }

}


