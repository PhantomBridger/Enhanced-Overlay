package io.github.phantombridger.enhancedoverlay.mixin;

import io.github.phantombridger.enhancedoverlay.config.BackgroundColorMode;
import io.github.phantombridger.enhancedoverlay.config.TextShadowMode;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.DisplayEntityRenderer.TextDisplayEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;

@Mixin(TextDisplayEntityRenderer.class)
public class TextDisplayMixin {
    // Force text display shadow
    @ModifyVariable(
            method = "render(Lnet/minecraft/client/render/entity/state/TextDisplayEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IF)V",
            at = @At(value = "STORE"),
            ordinal = 2
    )
    private boolean textDisplayTextShadow(boolean shadow) {
        if (ConfigScreen.CONFIG.instance().textDisplayShadow == TextShadowMode.ENABLED) {
            return true;
        } else if  (ConfigScreen.CONFIG.instance().textDisplayShadow == TextShadowMode.DISABLED){
            return false;
        } else if (ConfigScreen.CONFIG.instance().textDisplayShadow == TextShadowMode.DEFAULT) {
            return shadow;
        } else {
            return shadow; // added this so it doesn't break if the value is not ENABLED, DISABLED or DEFAULT
        }
    }

    // Remove text display background
    @Redirect(
            method = "render(Lnet/minecraft/client/render/entity/state/TextDisplayEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/VertexConsumer;color(I)Lnet/minecraft/client/render/VertexConsumer;"
            )
    )
    private VertexConsumer textDisplayBackgroundColor(VertexConsumer vertexConsumer, int vanillaColor) {
        if (ConfigScreen.CONFIG.instance().textDisplayBackground == BackgroundColorMode.NONE) {
            return vertexConsumer.color(0);
        } else if (ConfigScreen.CONFIG.instance().textDisplayBackground == BackgroundColorMode.CUSTOM) {
            return vertexConsumer.color(ConfigScreen.CONFIG.instance().textDisplayBackgroundColor.getRGB());
        } else if (ConfigScreen.CONFIG.instance().textDisplayBackground == BackgroundColorMode.DEFAULT) {
            return vertexConsumer.color(vanillaColor);
        } else {
            return vertexConsumer.color(vanillaColor); // added this so it doesn't break if the value is not NONE, CUSTOM or DEFAULT
        }
    }
}