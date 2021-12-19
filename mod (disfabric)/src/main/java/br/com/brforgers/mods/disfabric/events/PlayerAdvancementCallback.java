package br.com.brforgers.mods.disfabric.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.advancement.Advancement;
import net.minecraft.server.network.ServerPlayerEntity;

public interface PlayerAdvancementCallback {
    Event<PlayerAdvancementCallback> EVENT = EventFactory.createArrayBacked(PlayerAdvancementCallback.class, callbacks -> (playerEntity, advancement) -> {
        for (PlayerAdvancementCallback callback : callbacks) {
            callback.onPlayerAdvancement(playerEntity, advancement);
        }
    });

    void onPlayerAdvancement(ServerPlayerEntity playerEntity, Advancement advancement);
}
