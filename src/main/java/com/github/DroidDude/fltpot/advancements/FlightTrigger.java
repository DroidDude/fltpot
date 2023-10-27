package com.github.DroidDude.fltpot.advancements;

import com.github.DroidDude.fltpot.Main;
import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;

public class FlightTrigger extends SimpleCriterionTrigger<FlightTrigger.TriggerInstance> {
    static final ResourceLocation ID = new ResourceLocation(Main.MOD_ID, "flight");

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public FlightTrigger.TriggerInstance createInstance(JsonObject pJson, ContextAwarePredicate pPredicate, DeserializationContext pDeserializationContext) {

        DistancePredicate distancepredicate = DistancePredicate.fromJson(pJson.get("distance"));
        MinMaxBounds.Ints minmaxbounds$ints = MinMaxBounds.Ints.fromJson(pJson.get("duration"));
        return new FlightTrigger.TriggerInstance(pPredicate, distancepredicate, minmaxbounds$ints);

    }

    public void trigger(ServerPlayer pPlayer, Vec3 pStartPos, int pDuration) {

        this.trigger(pPlayer, (triggerInstance) -> triggerInstance.matches(pPlayer, pStartPos, pDuration));

    }


    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final DistancePredicate distance;
        private final MinMaxBounds.Ints duration;

        public TriggerInstance(ContextAwarePredicate pPlayer, DistancePredicate pDistance, MinMaxBounds.Ints pDuration) {

            super(FlightTrigger.ID, pPlayer);
            this.distance = pDistance;
            this.duration = pDuration;

        }

        public static FlightTrigger.TriggerInstance flown(DistancePredicate pDistance) {

            return new FlightTrigger.TriggerInstance(ContextAwarePredicate.ANY, pDistance, MinMaxBounds.Ints.ANY);

        }

        public boolean matches(ServerPlayer pPlayer, Vec3 pStartPos, int pDuration) {

            if (!this.distance.matches(pStartPos.x, pStartPos.y, pStartPos.z, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ())) {

                return false;

            } else {

                return this.duration.matches(pDuration);

            }

        }

        @Override
        public JsonObject serializeToJson(SerializationContext pConditions) {

            JsonObject jsonobject = super.serializeToJson(pConditions);
            jsonobject.add("distance", this.distance.serializeToJson());
            jsonobject.add("duration", this.duration.serializeToJson());
            return jsonobject;

        }

    }

}
