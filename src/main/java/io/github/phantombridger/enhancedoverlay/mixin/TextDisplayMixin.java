package io.github.phantombridger.enhancedoverlay.mixin;

import io.github.phantombridger.enhancedoverlay.config.TextDisplayShadowMode;
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
    private boolean forceTextDisplayShadow(boolean original) {
        if (ConfigScreen.CONFIG.instance().forceTextDisplayShadow == TextDisplayShadowMode.Enable) {
            return true;
        } else if  (ConfigScreen.CONFIG.instance().forceTextDisplayShadow == TextDisplayShadowMode.Disable){
            return false;
        } else if (ConfigScreen.CONFIG.instance().forceTextDisplayShadow == TextDisplayShadowMode.Default) {
            return original;
        } else {
            return original; // this was added so it doesn't break if the value is not True, False or Default
        }
    }

    // Remove text display background
    @Redirect(
            method = "render(Lnet/minecraft/client/render/entity/state/TextDisplayEntityRenderState;"
                    + "Lnet/minecraft/client/util/math/MatrixStack;"
                    + "Lnet/minecraft/client/render/VertexConsumerProvider;IF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/VertexConsumer;color(I)Lnet/minecraft/client/render/VertexConsumer;"
            )
    )
    private VertexConsumer forceRemoveBackgroundColor(VertexConsumer instance, int j) {
        if (ConfigScreen.CONFIG.instance().forceRemoveTextDisplayBackground) {
            return instance.color(0);
        } else {
            return instance.color(j);
        }
    }
}