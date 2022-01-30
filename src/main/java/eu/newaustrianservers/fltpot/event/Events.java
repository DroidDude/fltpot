package eu.newaustrianservers.fltpot.event;

import eu.newaustrianservers.fltpot.effect.Effects;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Events {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void doFlying(TickEvent.PlayerTickEvent event) {

        PlayerEntity player = event.player;
        CompoundNBT tag = player.getPersistentData();
        boolean wasFlying = tag.getBoolean("wasFlying");

        if (!player.isCreative() && !player.isSpectator()) {

            if (player.hasEffect(Effects.FLIGHT.get()) && !player.hasEffect(Effects.LEVITATION)) {

                if (!player.abilities.mayfly) {

                    player.abilities.mayfly = true;
                    tag.putBoolean("wasFlying", true);
                    player.onUpdateAbilities();

                }

                if (!player.isOnGround() && player.abilities.flying && player.getY() <= player.yOld) {

                    Vector3d vec = new Vector3d(0d, -0.1d, 0d);
                    player.move(MoverType.PLAYER, vec);

                } else if (player.isOnGround()) {

                    player.abilities.flying = false;
                    player.onUpdateAbilities();

                }

            } else if (wasFlying && !player.hasEffect(Effects.FLIGHT.get()) || player.hasEffect(Effects.LEVITATION)) {

                player.abilities.mayfly = false;
                player.abilities.flying = false;
                tag.putBoolean("wasFlying", false);
                player.onUpdateAbilities();

            }

        }

    }

    @SubscribeEvent
    public static void addNBTData(PlayerEvent.PlayerLoggedInEvent event) {

        PlayerEntity player = event.getPlayer();
        CompoundNBT tag = player.getPersistentData();
        INBT modeTag = tag.get("wasFlying");

        if (modeTag == null) {

            tag.putBoolean("wasFlying", false);

        }

    }

    @SubscribeEvent
    public static void fallDamageFlightPotion(PlayerFlyableFallEvent event) {

        double distance = event.getDistance();
        PlayerEntity player = event.getPlayer();

        if (distance >= 3 && !player.isCreative() && player.hasEffect(Effects.FLIGHT.get())) {

            float damage = (float) Math.floor(distance) - 2;
            player.hurt(DamageSource.FALL, damage);

        }

    }

}
