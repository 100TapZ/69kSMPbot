package br.com.brforgers.mods.disfabric.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;

public interface PlayerDeathCallback {
    Event<PlayerDeathCallback> EVENT = EventFactory.createArrayBacked(PlayerDeathCallback.class, callbacks -> (playerEntity, damageSource) -> {
        for (PlayerDeathCallback callback : callbacks) {
            callback.onPlayerDeath(playerEntity, damageSource);
        }
    });

    void onPlayerDeath(ServerPlayerEntity playerEntity, DamageSource damageSource);
}
