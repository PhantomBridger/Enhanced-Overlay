package io.github.phantombridger.enhancedoverlay.mixin;

import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class ParticleMixin {
    @Inject(method = "tickEffects", at = @At("HEAD"), cancellable = true)
    private void removeFirstPersonEffectParticles(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;

        Minecraft minecraft = Minecraft.getInstance();
        if (ConfigScreen.CONFIG.instance().hideFirstPersonEffectParticles && entity == minecraft.player &&
                minecraft.options.getCameraType().isFirstPerson()) {
            ci.cancel();
        }
    }
}
