package eu.newaustrianservers.fltpot.event;

import eu.newaustrianservers.fltpot.effect.Effects;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.potion.Effect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Events {

    private static boolean isActive(PlayerEntity player, Effect effect) {
        return player.isPotionActive(effect);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void doFlying(TickEvent.PlayerTickEvent event){

        PlayerEntity player = event.player;
        CompoundNBT tag = player.getPersistentData();
        boolean wasFlying = tag.getBoolean("wasFlying");

        if(!player.isCreative() && !player.isSpectator()){

            if(isActive(player, Effects.FLIGHT.get()) && !isActive(player, Effects.LEVITATION)){

                if(!player.abilities.allowFlying){

                    player.abilities.allowFlying = true;
                    tag.putBoolean("wasFlying", true);
                    player.sendPlayerAbilities();

                }

                if(!player.onGround && player.abilities.isFlying && player.getPosY() <= player.lastTickPosY && !Minecraft.getInstance().gameSettings.keyBindJump.isKeyDown()){

                    Vec3d vec = new Vec3d(0d, -0.1d, 0d);
                    player.move(MoverType.PLAYER, vec);

                } else if(player.onGround){

                    player.abilities.isFlying = false;
                    player.sendPlayerAbilities();

                }

            } else if(wasFlying && !isActive(player, Effects.FLIGHT.get()) || isActive(player, Effects.LEVITATION)){

                player.abilities.allowFlying = false;
                player.abilities.isFlying = false;
                tag.putBoolean("wasFlying", false);
                player.sendPlayerAbilities();

            }

        }

    }

    @SubscribeEvent
    public static void addNBTData(PlayerEvent.PlayerLoggedInEvent event){

        PlayerEntity player = event.getPlayer();
        CompoundNBT tag = player.getPersistentData();
        INBT modeTag = tag.get("wasFlying");

        if(modeTag == null){

            tag.putBoolean("wasFlying", false);

        }

    }

    @SubscribeEvent
    public static void fallDamageFlightPotion(PlayerFlyableFallEvent event) {

        double distance = event.getDistance();
        PlayerEntity player = event.getPlayer();

        if (distance >= 3 && !player.isCreative() && isActive(player, Effects.FLIGHT.get())){

            float damage = (float)Math.floor(distance) - 2;
            player.attackEntityFrom(DamageSource.FALL, damage);

        }

    }

}
