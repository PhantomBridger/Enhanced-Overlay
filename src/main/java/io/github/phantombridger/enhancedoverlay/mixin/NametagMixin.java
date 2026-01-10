package io.github.phantombridger.enhancedoverlay.mixin;

import io.github.phantombridger.enhancedoverlay.config.TextShadowMode;
import net.minecraft.client.renderer.feature.NameTagFeatureRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;

@Mixin(NameTagFeatureRenderer.class)
public class NametagMixin {
    @ModifyArg(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/Font;drawInBatch(Lnet/minecraft/network/chat/Component;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/gui/Font$DisplayMode;II)V"
            ),
            index = 4
    )
    private boolean nametagTextShadow(boolean shadow) {
        if (ConfigScreen.CONFIG.instance().nametagTextShadow == TextShadowMode.ENABLED) {
            return true;
        } else if (ConfigScreen.CONFIG.instance().nametagTextShadow == TextShadowMode.DISABLED) {
            return false;
        } else {
            return shadow;
        }
    }
    @ModifyArg(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/Font;drawInBatch(Lnet/minecraft/network/chat/Component;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/gui/Font$DisplayMode;II)V"
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
