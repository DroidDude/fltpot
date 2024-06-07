package com.github.droiddude.fltpot.potion;

import com.github.droiddude.fltpot.Main;
import com.github.droiddude.fltpot.effect.Effects;
import com.github.droiddude.fltpot.item.Items;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;

public class Potions extends net.minecraft.world.item.alchemy.Potions {


    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, Main.MOD_ID);

    public static final RegistryObject<Potion> FLIGHT_POTION = POTIONS.register("flight_potion", () -> new Potion( new MobEffectInstance(Effects.FLIGHT.getHolder().get(), 2400)));
    public static final RegistryObject<Potion> LONG_FLIGHT_POTION = POTIONS.register("long_flight_potion", () -> new Potion( new MobEffectInstance(Effects.FLIGHT.getHolder().get(), 9600)));
    public static final RegistryObject<Potion> LEVITATION_POTION = POTIONS.register("levitation", () -> new Potion( new MobEffectInstance(Effects.LEVITATION, 400)));


    /*public void addBrewingRecipe() {

        BrewingRecipeRegisterEvent.addRecipe(new FlightPotion(null, null, null));
        BrewingRecipeRegistry.addRecipe(new LongFlightPotion(null, null, null));
        BrewingRecipeRegistry.addRecipe(new LevitationPotion(null, null, null));

    }*/


    public static class FlightPotion extends BrewingRecipe {

        public static Ingredient INPUT = Ingredient.of(PotionContents.createItemStack(Items.POTION, Potions.AWKWARD));
        public static Ingredient REAGENT = Ingredient.of(Items.MAGIC_POWDER.get());
        public static ItemStack OUTPUT = PotionContents.createItemStack(Items.POTION, FLIGHT_POTION.getHolder().get());

        public FlightPotion(@Nonnull Ingredient start, @Nonnull Ingredient input, @Nonnull ItemStack output) {

            super(INPUT, REAGENT, OUTPUT);

        }

        @Override
        public boolean isInput(@Nonnull ItemStack stack) {

            return super.isInput(stack) && ItemStack.isSameItemSameComponents(getInput().getItems()[0], stack);

        }

        @Nonnull
        @Override
        public ItemStack getOutput(@Nonnull ItemStack input, @Nonnull ItemStack ingredient)
        {
            if (isInput(input) && isIngredient(ingredient)) return PotionContents.createItemStack(Items.POTION, FLIGHT_POTION.getHolder().get());
            return ItemStack.EMPTY;

        }

    }


    public static class LongFlightPotion extends BrewingRecipe {

        public static Ingredient INPUT = Ingredient.of(PotionContents.createItemStack(Items.POTION, Potions.FLIGHT_POTION.getHolder().get()));
        public static Ingredient REAGENT = Ingredient.of(Items.REDSTONE);
        public static ItemStack OUTPUT = PotionContents.createItemStack(Items.POTION, LONG_FLIGHT_POTION.getHolder().get());

        public LongFlightPotion(@Nonnull Ingredient start, @Nonnull Ingredient input, @Nonnull ItemStack output) {

            super(INPUT, REAGENT, OUTPUT);

        }

        @Override
        public boolean isInput(@Nonnull ItemStack stack) {

            return super.isInput(stack) && ItemStack.isSameItemSameComponents(getInput().getItems()[0], stack);
        }

        @Nonnull
        @Override
        public ItemStack getOutput(@Nonnull ItemStack input, @Nonnull ItemStack ingredient)
        {
            if (isInput(input) && isIngredient(ingredient)) return PotionContents.createItemStack(Items.POTION, LONG_FLIGHT_POTION.getHolder().get());
            return ItemStack.EMPTY;

        }

    }


    public static class LevitationPotion extends BrewingRecipe {

        public static Ingredient INPUT = Ingredient.of(PotionContents.createItemStack(Items.POTION, Potions.FLIGHT_POTION.getHolder().get()));
        public static Ingredient REAGENT = Ingredient.of(Items.FERMENTED_SPIDER_EYE);
        public static ItemStack OUTPUT = PotionContents.createItemStack(Items.POTION, LEVITATION_POTION.getHolder().get());

        public LevitationPotion(@Nonnull Ingredient start, @Nonnull Ingredient input, @Nonnull ItemStack output) {

            super(INPUT, REAGENT, OUTPUT);

        }

        @Override
        public boolean isInput(@Nonnull ItemStack stack) {

            return super.isInput(stack) && ItemStack.isSameItemSameComponents(getInput().getItems()[0], stack);

        }

        @Nonnull
        @Override
        public ItemStack getOutput(@Nonnull ItemStack input, @Nonnull ItemStack ingredient)
        {
            if (isInput(input) && isIngredient(ingredient)) return PotionContents.createItemStack(Items.POTION, LEVITATION_POTION.getHolder().get());
            return ItemStack.EMPTY;

        }

    }

}
