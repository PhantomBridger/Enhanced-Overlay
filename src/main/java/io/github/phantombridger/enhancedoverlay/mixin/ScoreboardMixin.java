package io.github.phantombridger.enhancedoverlay.mixin;

import io.github.phantombridger.enhancedoverlay.config.BackgroundColorMode;
import io.github.phantombridger.enhancedoverlay.config.TextShadowMode;
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
    private int scoreboardTitleBackground(int color) {
        if (ConfigScreen.CONFIG.instance().scoreboardBackground == BackgroundColorMode.NONE) {
            return 0;
        } else if  (ConfigScreen.CONFIG.instance().scoreboardBackground == BackgroundColorMode.CUSTOM) {
            return ConfigScreen.CONFIG.instance().scoreboardBackgroundColor.getRGB();
        } else if (ConfigScreen.CONFIG.instance().scoreboardBackground == BackgroundColorMode.DEFAULT) {
            return color;
        } else {
            return color; // added this so it doesn't break if the value is not None, Custom or DEFAULT
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
    private int scoreboardMainBackground(int color) {
        if (ConfigScreen.CONFIG.instance().scoreboardBackground == BackgroundColorMode.NONE) {
            return 0;
        } else if  (ConfigScreen.CONFIG.instance().scoreboardBackground == BackgroundColorMode.CUSTOM) {
            return ConfigScreen.CONFIG.instance().scoreboardBackgroundColor.getRGB();
        } else if (ConfigScreen.CONFIG.instance().scoreboardBackground == BackgroundColorMode.DEFAULT) {
            return color;
        } else {
            return color; // added this so it doesn't break if the value is not None, Custom or DEFAULT
        }
    }
    // Force scoreboard text shadow
    @ModifyArg(
            method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIIZ)V"
            ),
            index = 5
    )
    private boolean scoreboardTextShadow(boolean shadow) {
        if (ConfigScreen.CONFIG.instance().scoreboardTextShadow == TextShadowMode.ENABLED) {
            return true;
        } else if  (ConfigScreen.CONFIG.instance().scoreboardTextShadow == TextShadowMode.DISABLED){
            return false;
        } else if (ConfigScreen.CONFIG.instance().scoreboardTextShadow == TextShadowMode.DEFAULT) {
            return shadow;
        } else {
            return shadow; // added this so it doesn't break if the value is not ENABLED, DISABLED or DEFAULT
        }
    }
}