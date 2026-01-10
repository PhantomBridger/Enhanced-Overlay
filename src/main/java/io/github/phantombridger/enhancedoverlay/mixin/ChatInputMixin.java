package io.github.phantombridger.enhancedoverlay.mixin;

import io.github.phantombridger.enhancedoverlay.config.BackgroundColorMode;
import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;
import net.minecraft.client.gui.screens.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ChatScreen.class)
public class ChatInputMixin {
    @ModifyArg(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V"
            ),
            index = 4
    )
    public int chatInputBackground(int color) {
        if (ConfigScreen.CONFIG.instance().chatInputBackground == BackgroundColorMode.NONE) {
            return 0;
        } else if  (ConfigScreen.CONFIG.instance().chatInputBackground == BackgroundColorMode.CUSTOM) {
            return ConfigScreen.CONFIG.instance().chatInputBackgroundColor.getRGB();
        } else {
            return color;
        }
    }
}
