package io.github.phantombridger.enhancedoverlay.mixin;

import io.github.phantombridger.enhancedoverlay.config.BackgroundColorMode;
import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;
import io.github.phantombridger.enhancedoverlay.config.TextShadowMode;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(DebugHud.class)
public class DebugScreenMixin {
    @ModifyArg(
            method = "drawText(Lnet/minecraft/client/gui/DrawContext;Ljava/util/List;Z)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;IIIZ)V"
            ),
            index = 5
    )
    private boolean debugScreenTextShadow(boolean shadow) {
        if (ConfigScreen.CONFIG.instance().debugScreenTextShadow == TextShadowMode.ENABLED) {
            return true;
        } else if  (ConfigScreen.CONFIG.instance().debugScreenTextShadow == TextShadowMode.DISABLED){
            return false;
        } else if (ConfigScreen.CONFIG.instance().debugScreenTextShadow == TextShadowMode.DEFAULT) {
            return shadow;
        } else {
            return shadow; // added this so it doesn't break if the value is not ENABLED, DISABLED or DEFAULT
        }
    }
    @ModifyArg(
            method = "drawText(Lnet/minecraft/client/gui/DrawContext;Ljava/util/List;Z)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V"
            ),
            index = 4
    )
    private int debugScreenBackground(int color) {
        if (ConfigScreen.CONFIG.instance().debugScreenBackground == BackgroundColorMode.NONE) {
            return 0;
        } else if  (ConfigScreen.CONFIG.instance().debugScreenBackground == BackgroundColorMode.CUSTOM) {
            return ConfigScreen.CONFIG.instance().debugScreenBackgroundColor.getRGB();
        } else if (ConfigScreen.CONFIG.instance().debugScreenBackground == BackgroundColorMode.DEFAULT) {
            return color;
        } else {
            return color; // added this so it doesn't break if the value is not None, Custom or DEFAULT
        }
    }
}
