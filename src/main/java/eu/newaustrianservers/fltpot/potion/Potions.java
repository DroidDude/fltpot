package eu.newaustrianservers.fltpot.potion;

import eu.newaustrianservers.fltpot.Main;
import eu.newaustrianservers.fltpot.effect.Effects;
import eu.newaustrianservers.fltpot.item.Items;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

public class Potions extends net.minecraft.world.item.alchemy.Potions {


    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, Main.MODID);

    public static final RegistryObject<Potion> FLIGHT_POTION = POTIONS.register("flight_potion", () -> new Potion( new MobEffectInstance(Effects.FLIGHT.get(), 2400)));
    public static final RegistryObject<Potion> LONG_FLIGHT_POTION = POTIONS.register("long_flight_potion", () -> new Potion( new MobEffectInstance(Effects.FLIGHT.get(), 9600)));
    public static final RegistryObject<Potion> LEVITATION_POTION = POTIONS.register("levitation", () -> new Potion( new MobEffectInstance(Effects.LEVITATION, 200)));


    public static void addBrewingRecipe() {

        BrewingRecipeRegistry.addRecipe(new FlightPotion(null, null, null));
        BrewingRecipeRegistry.addRecipe(new LongFlightPotion(null, null, null));
        BrewingRecipeRegistry.addRecipe(new LevitationPotion(null, null, null));

    }


    public static class FlightPotion extends BrewingRecipe {

        private static Ingredient INPUT = Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD));
        private static Ingredient REAGENT = Ingredient.of(Items.MAGIC_POWDER.get());
        private static ItemStack OUTPUT = PotionUtils.setPotion(new ItemStack(Items.POTION), FLIGHT_POTION.get());

        public FlightPotion(@Nonnull Ingredient start, @Nonnull Ingredient input, @Nonnull ItemStack output) {

            super(INPUT, REAGENT, OUTPUT);
        }

        @Override
        public boolean isInput(@Nonnull ItemStack stack) {

            return PotionUtils.getPotion(stack) == Potions.AWKWARD;
        }

        @Nonnull
        @Override
        public ItemStack getOutput(@Nonnull ItemStack input, @Nonnull ItemStack ingredient)
        {
            if (isInput(input) && isIngredient(ingredient)) return PotionUtils.setPotion(new ItemStack(input.getItem()), FLIGHT_POTION.get());
            return ItemStack.EMPTY;
        }
    }


    public static class LongFlightPotion extends BrewingRecipe {

        private static Ingredient INPUT = Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.FLIGHT_POTION.get()));
        private static Ingredient REAGENT = Ingredient.of(Items.REDSTONE);
        private static ItemStack OUTPUT = PotionUtils.setPotion(new ItemStack(Items.POTION), LONG_FLIGHT_POTION.get());

        public LongFlightPotion(@Nonnull Ingredient start, @Nonnull Ingredient input, @Nonnull ItemStack output) {

            super(INPUT, REAGENT, OUTPUT);
        }

        @Override
        public boolean isInput(@Nonnull ItemStack stack) {

            return PotionUtils.getPotion(stack) == Potions.FLIGHT_POTION.get();
        }

        @Nonnull
        @Override
        public ItemStack getOutput(@Nonnull ItemStack input, @Nonnull ItemStack ingredient)
        {
            if (isInput(input) && isIngredient(ingredient)) return PotionUtils.setPotion(new ItemStack(input.getItem()), LONG_FLIGHT_POTION.get());
            return ItemStack.EMPTY;
        }
    }


    public static class LevitationPotion extends BrewingRecipe {

        private static Ingredient INPUT = Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.FLIGHT_POTION.get()));
        private static Ingredient REAGENT = Ingredient.of(Items.FERMENTED_SPIDER_EYE);
        private static ItemStack OUTPUT = PotionUtils.setPotion(new ItemStack(Items.POTION), LEVITATION_POTION.get());

        public LevitationPotion(@Nonnull Ingredient start, @Nonnull Ingredient input, @Nonnull ItemStack output) {

            super(INPUT, REAGENT, OUTPUT);
        }

        @Override
        public boolean isInput(@Nonnull ItemStack stack) {

            return PotionUtils.getPotion(stack) == Potions.FLIGHT_POTION.get();
        }

        @Nonnull
        @Override
        public ItemStack getOutput(@Nonnull ItemStack input, @Nonnull ItemStack ingredient)
        {
            if (isInput(input) && isIngredient(ingredient)) return PotionUtils.setPotion(new ItemStack(input.getItem()), LEVITATION_POTION.get());
            return ItemStack.EMPTY;
        }
    }
}
