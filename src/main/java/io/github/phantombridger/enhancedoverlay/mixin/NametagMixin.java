package io.github.phantombridger.enhancedoverlay.mixin;

import net.minecraft.client.renderer.SubmitNodeCollection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;

@Mixin(SubmitNodeCollection.class)
public class NametagMixin {
    @ModifyArg(
            method = "nameTag",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/feature/TextFeatureRenderer$Content$Text;<init>(FFLnet/minecraft/util/FormattedCharSequence;ZIII)V"
            ),
            index = 3
    )
    private static boolean nametagTextShadow(boolean shadow) {
        return switch (ConfigScreen.CONFIG.instance().nametagTextShadow) {
            case ENABLED -> true;
            case DISABLED -> false;
            default -> shadow;
        };
    }
    @ModifyArg(
            method = "nameTag",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/feature/TextFeatureRenderer$Content$Text;<init>(FFLnet/minecraft/util/FormattedCharSequence;ZIII)V"
            ),
            index = 5
    )
    private static int removeNametagBackground(int color) {
        if (ConfigScreen.CONFIG.instance().removeNametagBackground) {
            return 0;
        } else {
            return color;
        }
    }
}
