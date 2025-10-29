package io.github.phantombridger.enhancedoverlay.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;

@Mixin(InGameHud.class)
public class ScoreboardMixin {
    // Remove scoreboard title background
    @ModifyArg(
            method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V",
                    ordinal = 0
            ),
            index = 4
    )
    private int disableScoreboardTitleBackground(int color) {
        if (ConfigScreen.CONFIG.instance().removeScoreboardBackground) {
            return 0;
        } else {
            return color;
        }
    }
    // Remove scoreboard main background
    @ModifyArg(
            method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V",
                    ordinal = 1
            ),
            index = 4
    )
    private int disableScoreboardMainBackground(int color) {
        if (ConfigScreen.CONFIG.instance().removeScoreboardBackground) {
            return 0;
        } else {
            return color;
        }
    }
    // Force scoreboard text shadow
    @ModifyArg(
            method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIIZ)I"
            ),
            index = 5
    )
    private boolean enableScoreboardTextShadow(boolean shadow) {
        if (ConfigScreen.CONFIG.instance().scoreboardTextShadow) {
            return true;
        } else {
            return shadow;
        }
    }
}