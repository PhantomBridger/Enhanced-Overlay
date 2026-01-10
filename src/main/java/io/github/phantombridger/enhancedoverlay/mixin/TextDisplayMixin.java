package io.github.phantombridger.enhancedoverlay.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.github.phantombridger.enhancedoverlay.config.TextShadowMode;
import net.minecraft.client.renderer.entity.DisplayRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;

@Mixin(DisplayRenderer.TextDisplayRenderer.class)
public class TextDisplayMixin {
    @ModifyVariable(
            method = "submitInner",
            at = @At(value = "STORE", ordinal = 0),
            ordinal = 2
    )
    private boolean textDisplayTextShadow(boolean shadow) {
        if (ConfigScreen.CONFIG.instance().textDisplayTextShadow == TextShadowMode.ENABLED) {
            return true;
        } else if  (ConfigScreen.CONFIG.instance().textDisplayTextShadow == TextShadowMode.DISABLED){
            return false;
        } else {
            return shadow;
        }
    }
    @ModifyVariable(
            method = "submitInner",
            at = @At(value = "STORE", ordinal = 0),
            ordinal = 1
    )
    private boolean removeTextDisplayBackgroundPartOne(boolean background) {
        if (ConfigScreen.CONFIG.instance().removeTextDisplayBackground) {
            return true;
        } else {
            return background;
        }
    }
    @ModifyExpressionValue(
            method = "submitInner",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/Options;getBackgroundOpacity(F)F"
            )
    )
    private float removeTextDisplayBackgroundPartTwo(float original) {
        if (ConfigScreen.CONFIG.instance().removeTextDisplayBackground) {
            return 0.0F;
        } else {
            return original;
        }
    }
}