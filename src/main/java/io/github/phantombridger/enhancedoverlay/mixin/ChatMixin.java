package io.github.phantombridger.enhancedoverlay.mixin;

import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;
import net.minecraft.client.gui.components.ChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatComponent.class)
public class ChatMixin {
    @Inject(
            method = "extractRenderState(Lnet/minecraft/client/gui/components/ChatComponent$ChatGraphicsAccess;IILnet/minecraft/client/gui/components/ChatComponent$DisplayMode;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void hideChat(final ChatComponent.ChatGraphicsAccess graphics, final int screenHeight, final int ticks, final ChatComponent.DisplayMode displayMode, CallbackInfo ci) {
        if (ConfigScreen.CONFIG.instance().hideChat) {
            ci.cancel();
        }
    }
}
