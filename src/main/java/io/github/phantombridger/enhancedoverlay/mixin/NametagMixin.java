package io.github.phantombridger.enhancedoverlay.mixin;

import io.github.phantombridger.enhancedoverlay.config.BackgroundColorMode;
import io.github.phantombridger.enhancedoverlay.config.TextShadowMode;
import net.minecraft.client.render.entity.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;

@Mixin(EntityRenderer.class)
public class NametagMixin {
    // Force nametag shadow
    @ModifyArg(
            method = "renderLabelIfPresent(Lnet/minecraft/client/render/entity/state/EntityRenderState;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/text/Text;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/client/font/TextRenderer$TextLayerType;II)V"
            ),
            index = 4
    )
    private boolean nametagTextShadow(boolean shadow) {
        if (ConfigScreen.CONFIG.instance().nametagTextShadow == TextShadowMode.ENABLED) {
            return true;
        } else if  (ConfigScreen.CONFIG.instance().nametagTextShadow == TextShadowMode.DISABLED){
            return false;
        } else if (ConfigScreen.CONFIG.instance().nametagTextShadow == TextShadowMode.DEFAULT) {
            return shadow;
        } else {
            return shadow; // added this so it doesn't break if the value is not ENABLED, DISABLED or DEFAULT
        }
    }

    // Remove nametag background
    @ModifyArg(
            method = "renderLabelIfPresent(Lnet/minecraft/client/render/entity/state/EntityRenderState;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/text/Text;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/client/font/TextRenderer$TextLayerType;II)V"
            ),
            index = 8
    )
    private int nametagBackground(int color) {
        if (ConfigScreen.CONFIG.instance().nametagBackground == BackgroundColorMode.NONE) {
            return 0;
        } else if  (ConfigScreen.CONFIG.instance().nametagBackground == BackgroundColorMode.CUSTOM) {
            return ConfigScreen.CONFIG.instance().nametagBackgroundColor.getRGB();
        } else if (ConfigScreen.CONFIG.instance().nametagBackground == BackgroundColorMode.DEFAULT) {
            return color;
        } else {
            return color; // added this so it doesn't break if the value is not None, Custom or DEFAULT
        }
    }
}
