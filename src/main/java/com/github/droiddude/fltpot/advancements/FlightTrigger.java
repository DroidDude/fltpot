package com.github.droiddude.fltpot.advancements;

import com.github.droiddude.fltpot.Main;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class FlightTrigger extends SimpleCriterionTrigger<FlightTrigger.TriggerInstance> {
    static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "flight");
    public Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer pPlayer, Vec3 pStartPos, int pDuration) {
        this.trigger(pPlayer, (p_49124_) -> {
            return p_49124_.matches(pPlayer, pStartPos, pDuration);
        });
    }


    public static record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<DistancePredicate> distance, MinMaxBounds.Ints duration) implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create((parameter) -> {
            return parameter.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player), DistancePredicate.CODEC.optionalFieldOf("distance").forGetter(TriggerInstance::distance), MinMaxBounds.Ints.CODEC.optionalFieldOf("duration", MinMaxBounds.Ints.ANY).forGetter(TriggerInstance::duration)).apply(parameter, TriggerInstance::new);
        });

        public static Criterion<TriggerInstance> flown(DistancePredicate pDistance) {

            return CriteriaTriggers.FLIGHT.createCriterion(new TriggerInstance(Optional.empty(), Optional.of(pDistance), MinMaxBounds.Ints.ANY));

        }

        public boolean matches(ServerPlayer pPlayer, Vec3 pStartPos, int pDuration) {

            if (this.distance.isPresent() && !this.distance.get().matches(pStartPos.x, pStartPos.y, pStartPos.z, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ())) {

                return false;

            } else {

                return this.duration.matches(pDuration);

            }

        }

    }

}
