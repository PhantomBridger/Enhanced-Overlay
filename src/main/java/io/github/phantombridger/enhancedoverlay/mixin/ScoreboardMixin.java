package io.github.phantombridger.enhancedoverlay.mixin;

import io.github.phantombridger.enhancedoverlay.config.BackgroundColorMode;
import io.github.phantombridger.enhancedoverlay.config.TextShadowMode;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;

@Mixin(Gui.class)
public class ScoreboardMixin {
    @ModifyArg(
            method = "displayScoreboardSidebar",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V"
            ),
            index = 4
    )
    private int scoreboardBackground(int color) {
        if (ConfigScreen.CONFIG.instance().scoreboardBackground == BackgroundColorMode.NONE) {
            return 0;
        } else if  (ConfigScreen.CONFIG.instance().scoreboardBackground == BackgroundColorMode.CUSTOM){
            return ConfigScreen.CONFIG.instance().scoreboardBackgroundColor.getRGB();
        } else {
            return color;
        }
    }
    @ModifyArg(
            method = "displayScoreboardSidebar",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)V"
            ),
            index = 5
    )
    private boolean scoreboardTextShadow(boolean shadow) {
        if (ConfigScreen.CONFIG.instance().scoreboardTextShadow == TextShadowMode.ENABLED) {
            return true;
        } else if  (ConfigScreen.CONFIG.instance().scoreboardTextShadow == TextShadowMode.DISABLED){
            return false;
        } else {
            return shadow;
        }
    }
}