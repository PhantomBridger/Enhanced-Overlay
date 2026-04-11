package io.github.phantombridger.enhancedoverlay.mixin;

import net.minecraft.client.renderer.feature.NameTagFeatureRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;

@Mixin(NameTagFeatureRenderer.class)
public class NametagMixin {
    @ModifyArg(
            method = "renderTranslucent",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/Font;drawInBatch(Lnet/minecraft/network/chat/Component;FFIZLorg/joml/Matrix4fc;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/gui/Font$DisplayMode;II)V"
            ),
            index = 4
    )
    private boolean nametagTextShadow(boolean shadow) {
        return switch (ConfigScreen.CONFIG.instance().nametagTextShadow) {
            case ENABLED -> true;
            case DISABLED -> false;
            default -> shadow;
        };
    }
    @ModifyArg(
            method = "renderTranslucent",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/Font;drawInBatch(Lnet/minecraft/network/chat/Component;FFIZLorg/joml/Matrix4fc;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/gui/Font$DisplayMode;II)V"
            ),
            index = 8
    )
    private int removeNametagBackground(int color) {
        if (ConfigScreen.CONFIG.instance().removeNametagBackground) {
            return 0;
        } else {
            return color;
        }
    }
}
