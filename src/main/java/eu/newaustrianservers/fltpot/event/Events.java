package eu.newaustrianservers.fltpot.event;

import eu.newaustrianservers.fltpot.effect.Effects;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Events {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void doFlying(TickEvent.PlayerTickEvent event){

        Player player = event.player;
        CompoundTag tag = player.getPersistentData();
        boolean wasFlying = tag.getBoolean("wasFlying");

        if(!player.isCreative() && !player.isSpectator()){

            if(player.hasEffect(Effects.FLIGHT.get()) && !player.hasEffect(Effects.LEVITATION)){

                if(!player.getAbilities().mayfly){

                    player.getAbilities().mayfly = true;
                    tag.putBoolean("wasFlying", true);
                    player.onUpdateAbilities();

                }

                if(!player.isOnGround() && player.getAbilities().flying && player.getY() <= player.yOld && !Minecraft.getInstance().options.keyJump.isDown()){

                    Vec3 vec = new Vec3(0d, -0.1d, 0d);
                    player.move(MoverType.PLAYER, vec);

                } else if(player.isOnGround()){

                    player.getAbilities().flying = false;
                    player.onUpdateAbilities();

                }

            } else if(wasFlying && !player.hasEffect(Effects.FLIGHT.get()) || player.hasEffect(Effects.LEVITATION)){

                player.getAbilities().mayfly = false;
                player.getAbilities().flying = false;
                tag.putBoolean("wasFlying", false);
                player.onUpdateAbilities();

            }

        }

    }

    @SubscribeEvent
    public static void addNBTData(PlayerEvent.PlayerLoggedInEvent event){

        Player player = event.getPlayer();
        CompoundTag tag = player.getPersistentData();
        Tag modeTag = tag.get("wasFlying");

        if(modeTag == null){

            tag.putBoolean("wasFlying", false);

        }

    }

    @SubscribeEvent
    public static void fallDamageFlightPotion(PlayerFlyableFallEvent event) {

        double distance = event.getDistance();
        Player player = event.getPlayer();

        if (distance >= 3 && !player.isCreative() && player.hasEffect(Effects.FLIGHT.get())){

            float damage = (float)Math.floor(distance) - 2;
            player.hurt(DamageSource.FALL, damage);

        }

    }

}
