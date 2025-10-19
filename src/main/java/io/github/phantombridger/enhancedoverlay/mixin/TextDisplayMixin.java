package io.github.phantombridger.enhancedoverlay.mixin;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.DisplayEntityRenderer.TextDisplayEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TextDisplayEntityRenderer.class)
public class TextDisplayMixin {
    // Force text display shadow
    @ModifyVariable(
            method = "render(Lnet/minecraft/client/render/entity/state/TextDisplayEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IF)V",
            at = @At(value = "STORE"),
            ordinal = 2
    )
    private boolean forceTextDisplayShadow(boolean original) {
        return true;
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
        return instance.color(0);
    }
}