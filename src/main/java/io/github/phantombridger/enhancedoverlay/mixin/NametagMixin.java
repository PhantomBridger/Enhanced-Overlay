package io.github.phantombridger.enhancedoverlay.mixin;

import net.minecraft.client.renderer.feature.NameTagFeatureRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;

@Mixin(NameTagFeatureRenderer.class)
public class NametagMixin {
    @ModifyArg(
            method = "prepareText",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/Font;prepareText(Lnet/minecraft/util/FormattedCharSequence;FFIZZI)Lnet/minecraft/client/gui/Font$PreparedText;"
            ),
            index = 4
    )
    private static boolean nametagTextShadow(boolean shadow) {
        return switch (ConfigScreen.CONFIG.instance().nametagTextShadow) {
            case ENABLED -> true;
            case DISABLED -> false;
            default -> shadow;
        };
    }
    @ModifyArg(
            method = "prepareText",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/Font;prepareText(Lnet/minecraft/util/FormattedCharSequence;FFIZZI)Lnet/minecraft/client/gui/Font$PreparedText;"
            ),
            index = 6
    )
    private static int removeNametagBackground(int color) {
        if (ConfigScreen.CONFIG.instance().removeNametagBackground) {
            return 0;
        } else {
            return color;
        }
    }
}
