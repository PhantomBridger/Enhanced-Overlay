package io.github.phantombridger.enhancedoverlay.mixin;

import net.minecraft.client.gui.Hud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;

@Mixin(Hud.class)
public class ScoreboardMixin {
    @ModifyArg(
            method = "displayScoreboardSidebar",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;fill(IIIII)V"
            ),
            index = 4
    )
    private int scoreboardBackground(int color) {
        return switch (ConfigScreen.CONFIG.instance().scoreboardBackground) {
            case NONE -> 0;
            case CUSTOM -> ConfigScreen.CONFIG.instance().scoreboardBackgroundColor.getRGB();
            default -> color;
        };
    }
    @ModifyArg(
            method = "displayScoreboardSidebar",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)V"
            ),
            index = 5
    )
    private boolean scoreboardTextShadow(boolean shadow) {
        return switch (ConfigScreen.CONFIG.instance().scoreboardTextShadow) {
            case ENABLED -> true;
            case DISABLED -> false;
            default -> shadow;
        };
    }
}