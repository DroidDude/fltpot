package eu.newaustrianservers.fltpot.potion;

import eu.newaustrianservers.fltpot.Main;
import eu.newaustrianservers.fltpot.effect.Effects;
import eu.newaustrianservers.fltpot.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

public class Potions extends net.minecraft.potion.Potions {

    public static final DeferredRegister<Potion> POTIONS = new DeferredRegister<>(ForgeRegistries.POTION_TYPES, Main.MODID);

    public static final RegistryObject<Potion> FLIGHT_POTION = POTIONS.register("flight_potion", () -> new Potion( new EffectInstance(Effects.FLIGHT.get(), 2400)));
    public static final RegistryObject<Potion> LONG_FLIGHT_POTION = POTIONS.register("long_flight_potion", () -> new Potion( new EffectInstance(Effects.FLIGHT.get(), 9600)));

    public static void addBrewingRecipe() {

        BrewingRecipeRegistry.addRecipe(new FlightPotion(null, null, null));
        BrewingRecipeRegistry.addRecipe(new LongFlightPotion(null, null, null));

    }


    public static class FlightPotion extends BrewingRecipe {

        private static Ingredient INPUT = Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.AWKWARD));
        private static Ingredient REAGENT = Ingredient.fromItems(Items.MAGIC_POWDER.get());
        private static ItemStack OUTPUT = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), FLIGHT_POTION.get());

        public FlightPotion(@Nonnull Ingredient start, @Nonnull Ingredient input, @Nonnull ItemStack output) {

            super(INPUT, REAGENT, OUTPUT);
        }

        @Override
        public boolean isInput(@Nonnull ItemStack stack) {

            return PotionUtils.getPotionFromItem(stack) == Potions.AWKWARD;
        }

        @Nonnull
        @Override
        public ItemStack getOutput(@Nonnull ItemStack input, @Nonnull ItemStack ingredient)
        {
            if (isInput(input) && isIngredient(ingredient)) return PotionUtils.addPotionToItemStack(new ItemStack(input.getItem()), FLIGHT_POTION.get());
            return ItemStack.EMPTY;
        }
    }


    public static class LongFlightPotion extends BrewingRecipe {

        private static Ingredient INPUT = Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.FLIGHT_POTION.get()));
        private static Ingredient REAGENT = Ingredient.fromItems(Items.REDSTONE);
        private static ItemStack OUTPUT = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), LONG_FLIGHT_POTION.get());

        public LongFlightPotion(@Nonnull Ingredient start, @Nonnull Ingredient input, @Nonnull ItemStack output) {

            super(INPUT, REAGENT, OUTPUT);
        }

        @Override
        public boolean isInput(@Nonnull ItemStack stack) {

            return PotionUtils.getPotionFromItem(stack) == Potions.FLIGHT_POTION.get();
        }

        @Nonnull
        @Override
        public ItemStack getOutput(@Nonnull ItemStack input, @Nonnull ItemStack ingredient)
        {
            if (isInput(input) && isIngredient(ingredient)) return PotionUtils.addPotionToItemStack(new ItemStack(input.getItem()), LONG_FLIGHT_POTION.get());
            return ItemStack.EMPTY;
        }
    }

}
