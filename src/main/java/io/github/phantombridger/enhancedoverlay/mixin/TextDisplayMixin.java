package io.github.phantombridger.enhancedoverlay.mixin;

import net.minecraft.client.renderer.entity.DisplayRenderer;
import net.minecraft.client.renderer.state.OptionsRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DisplayRenderer.TextDisplayRenderer.class)
public class TextDisplayMixin {
    @ModifyVariable(
            method = "submitInner",
            at = @At(value = "STORE", ordinal = 0),
            ordinal = 2
    )
    private boolean textDisplayTextShadow(boolean shadow) {
        return switch (ConfigScreen.CONFIG.instance().textDisplayTextShadow) {
            case ENABLED -> true;
            case DISABLED -> false;
            default -> shadow;
        };
    }
    @ModifyVariable(
            method = "submitInner",
            at = @At(value = "STORE", ordinal = 0),
            ordinal = 1
    )
    private boolean removeTextDisplayBackgroundPartOne(boolean original) {
        if (ConfigScreen.CONFIG.instance().removeTextDisplayBackground) {
            return true;
        }
        return original;
    }
    @Redirect(
            method = "submitInner",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/state/OptionsRenderState;getBackgroundOpacity(F)F"
            )
    )
    private float removeTextDisplayBackgroundPartTwo(OptionsRenderState instance, float defaultOpacity) {
        if (ConfigScreen.CONFIG.instance().removeTextDisplayBackground) {
            return 0.0F;
        }
        return instance.getBackgroundOpacity(defaultOpacity);
    }
}