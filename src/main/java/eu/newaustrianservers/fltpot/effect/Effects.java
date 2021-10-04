package eu.newaustrianservers.fltpot.effect;

import eu.newaustrianservers.fltpot.Main;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Effects extends net.minecraft.potion.Effects {

    public static final DeferredRegister<Effect> EFFECTS = new DeferredRegister<>(ForgeRegistries.POTIONS, Main.MODID);

    public static final RegistryObject<Effect> FLIGHT = EFFECTS.register("flight_effect", () -> new Flight(EffectType.BENEFICIAL, 0xd9fffb));

    public static class Flight extends Effect{

        public Flight(EffectType typeIn, int liquidColorIn) {

            super(typeIn, liquidColorIn);
        }

    }

}
