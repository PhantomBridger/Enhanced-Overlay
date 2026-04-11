package io.github.phantombridger.enhancedoverlay.mixin;

import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;
import net.minecraft.client.gui.screens.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ChatScreen.class)
public class ChatInputMixin {
    @ModifyArg(
            method = "extractRenderState(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;fill(IIIII)V"
            ),
            index = 4
    )
    public int chatInputBackground(int color) {
        return switch (ConfigScreen.CONFIG.instance().chatInputBackground) {
            case NONE -> 0;
            case CUSTOM -> ConfigScreen.CONFIG.instance().chatInputBackgroundColor.getRGB();
            default -> color;
        };
    }
}
