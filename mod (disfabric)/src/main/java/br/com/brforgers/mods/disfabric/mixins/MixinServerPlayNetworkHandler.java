package br.com.brforgers.mods.disfabric.mixins;

import br.com.brforgers.mods.disfabric.events.ServerChatCallback;
import net.minecraft.network.MessageType;
import net.minecraft.network.Packet;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.server.filter.TextStream.Message;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class MixinServerPlayNetworkHandler {

    @Final
    @Shadow private MinecraftServer server;
    @Shadow public ServerPlayerEntity player;
    @Shadow private int messageCooldown;
    @Shadow public abstract void sendPacket(Packet<?> packet);
    @Shadow public abstract void disconnect(Text reason);
    @Shadow protected abstract void executeCommand(String input);


    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;broadcast(Lnet/minecraft/text/Text;Ljava/util/function/Function;Lnet/minecraft/network/MessageType;Ljava/util/UUID;)V"), method = "handleMessage", cancellable = true)
    private void handleMessage(Message message, CallbackInfo ci) {
        String string = message.getRaw();
        String msg = StringUtils.normalizeSpace(string);
        Text text = new TranslatableText("chat.type.text", this.player.getDisplayName(), msg);
        Optional<Text> eventResult = ServerChatCallback.EVENT.invoker().onServerChat(this.player, msg, text);
        if (eventResult.isPresent()) {
            this.server.getPlayerManager().broadcast(eventResult.get(), MessageType.CHAT, this.player.getUuid());
            ci.cancel();
        }
    }
}
