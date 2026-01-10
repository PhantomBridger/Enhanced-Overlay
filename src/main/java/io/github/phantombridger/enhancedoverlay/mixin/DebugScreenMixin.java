package io.github.phantombridger.enhancedoverlay.mixin;

import io.github.phantombridger.enhancedoverlay.config.BackgroundColorMode;
import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;
import io.github.phantombridger.enhancedoverlay.config.TextShadowMode;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DebugScreenOverlay.class)
public class DebugScreenMixin {
    @Redirect(
            method = "renderLines(Lnet/minecraft/client/gui/GuiGraphics;Ljava/util/List;Z)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)V"
            )
    )
    private void debugScreenTextShadow(GuiGraphics guiGraphics, net.minecraft.client.gui.Font font, String text, int x, int y, int color, boolean shadow) {
        if (ConfigScreen.CONFIG.instance().debugScreenTextShadow == TextShadowMode.ENABLED) {
            guiGraphics.drawString(font, text, x, y, color, true);
        } else if  (ConfigScreen.CONFIG.instance().debugScreenTextShadow == TextShadowMode.DISABLED){
            guiGraphics.drawString(font, text, x, y, color, false);
        } else {
            guiGraphics.drawString(font, text, x, y, color, shadow);
        }
    }
    @ModifyArg(
            method = "renderLines(Lnet/minecraft/client/gui/GuiGraphics;Ljava/util/List;Z)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V"
            ),
            index = 4
    )
    private int debugScreenBackground(int color) {
        if (ConfigScreen.CONFIG.instance().debugScreenBackground == BackgroundColorMode.NONE) {
            return 0;
        } else if  (ConfigScreen.CONFIG.instance().debugScreenBackground == BackgroundColorMode.CUSTOM) {
            return ConfigScreen.CONFIG.instance().debugScreenBackgroundColor.getRGB();
        } else {
            return color;
        }
    }
}
