package io.github.phantombridger.enhancedoverlay.mixin;

import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DebugScreenOverlay.class)
public class DebugScreenMixin {
    @Redirect(
            method = "extractLines",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)V"
            )
    )
    private void debugScreenTextShadow(GuiGraphicsExtractor guiGraphics, net.minecraft.client.gui.Font font, String text, int x, int y, int color, boolean shadow) {
        switch (ConfigScreen.CONFIG.instance().debugScreenTextShadow) {
            case ENABLED -> guiGraphics.text(font, text, x, y, color, true);
            case DISABLED -> guiGraphics.text(font, text, x, y, color, false);
            default -> guiGraphics.text(font, text, x, y, color, shadow);
        }
    }
    @ModifyArg(
            method = "extractLines",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;fill(IIIII)V"
            ),
            index = 4
    )
    private int debugScreenBackground(int color) {
        return switch (ConfigScreen.CONFIG.instance().debugScreenBackground) {
            case NONE -> 0;
            case CUSTOM -> ConfigScreen.CONFIG.instance().debugScreenBackgroundColor.getRGB();
            default -> color;
        };
    }
}
