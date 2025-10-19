package io.github.phantombridger.enhancedoverlay.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;


@Mixin(InGameHud.class)
public class ScoreboardMixin {
    private static final boolean scoreboardModificationsEnabled = true;
    @ModifyArg(
            method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V",
                    ordinal = 0
            ),
            index = 4
    )
    private int removeScoreboardTitleBackground(int color) {
        if (scoreboardModificationsEnabled) {
            return 0;
        } else {
            return color;
        }
    }
    @ModifyArg(
            method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V",
                    ordinal = 1
            ),
            index = 4
    )
    private int removeScoreboardMainBackground(int color) {
        if (scoreboardModificationsEnabled) {
            return 0;
        } else {
            return color;
        }
    }
    @ModifyArg(
            method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIIZ)I"
            ),
            index = 5
    )
    private boolean forceScoreboardTextShadow(boolean shadow) {
        if (scoreboardModificationsEnabled) {
            return true;
        } else {
            return shadow;
        }
    }
}