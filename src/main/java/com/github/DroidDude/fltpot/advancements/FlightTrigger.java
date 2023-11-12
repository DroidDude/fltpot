package com.github.DroidDude.fltpot.advancements;

import com.github.DroidDude.fltpot.Main;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class FlightTrigger extends SimpleCriterionTrigger<FlightTrigger.TriggerInstance> {
    static final ResourceLocation ID = new ResourceLocation(Main.MOD_ID, "flight");

    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public TriggerInstance createInstance(JsonObject pJson, Optional<ContextAwarePredicate> pPlayer, DeserializationContext pDeserializationContext) {

        Optional<DistancePredicate> distancepredicate = DistancePredicate.fromJson(pJson.get("distance"));
        MinMaxBounds.Ints minmaxbounds$ints = MinMaxBounds.Ints.fromJson(pJson.get("duration"));
        return new TriggerInstance(pPlayer, distancepredicate, minmaxbounds$ints);

    }

    public void trigger(ServerPlayer pPlayer, Vec3 pStartPos, int pDuration) {

        this.trigger(pPlayer, (triggerInstance) -> triggerInstance.matches(pPlayer, pStartPos, pDuration));

    }


    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final Optional<DistancePredicate> distance;
        private final MinMaxBounds.Ints duration;

        public TriggerInstance(Optional<ContextAwarePredicate> pPlayer, Optional<DistancePredicate> pDistance, MinMaxBounds.Ints pDuration) {

            super(pPlayer);
            this.distance = pDistance;
            this.duration = pDuration;

        }

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

        @Override
        public JsonObject serializeToJson() {
            JsonObject jsonobject = super.serializeToJson();
            this.distance.ifPresent((p_300145_) -> {
                jsonobject.add("distance", p_300145_.serializeToJson());
            });
            jsonobject.add("duration", this.duration.serializeToJson());
            return jsonobject;
        }

    }

}
