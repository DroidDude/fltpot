package com.github.droiddude.fltpot.effect;

import com.github.droiddude.fltpot.Main;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Effects extends MobEffects {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Main.MOD_ID);

    public static final RegistryObject<MobEffect> FLIGHT = EFFECTS.register("flight_effect", () -> new Flight(MobEffectCategory.BENEFICIAL, 0x81fcba));

    public static class Flight extends MobEffect{

        public Flight(MobEffectCategory typeIn, int liquidColorIn) {

            super(typeIn, liquidColorIn);
        }

    }

}
