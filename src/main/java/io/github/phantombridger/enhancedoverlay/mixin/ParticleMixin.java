package io.github.phantombridger.enhancedoverlay.mixin;

import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(LivingEntity.class)
public class ParticleMixin {
    @ModifyVariable(method = "tickEffects", at = @At("STORE"), ordinal = 0)
    private List<ParticleOptions> removeFirstPersonEffectParticles(List<ParticleOptions> particles) {
        LivingEntity self = (LivingEntity)(Object)this;

        Minecraft minecraft = Minecraft.getInstance();
        if (ConfigScreen.CONFIG.instance().hideFirstPersonEffectParticles && self == minecraft.player && minecraft.options.getCameraType().isFirstPerson()) {
            return List.of();
        }
        return particles;
    }
}