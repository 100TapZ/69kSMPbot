package br.com.brforgers.mods.disfabric.events;

import java.util.Optional;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public interface ServerChatCallback {
    Event<ServerChatCallback> EVENT = EventFactory.createArrayBacked(ServerChatCallback.class, callbacks -> (playerEntity, rawMessage, message) -> {
        Optional<Text> msg = Optional.empty();
        for (ServerChatCallback callback : callbacks) {
            Optional<Text> callbackResult = callback.onServerChat(playerEntity, rawMessage, message);
            if (callbackResult.isPresent()) msg = callbackResult;
        }
        return msg;
    });

    Optional<Text> onServerChat(ServerPlayerEntity playerEntity, String rawMessage, Text message);
}
