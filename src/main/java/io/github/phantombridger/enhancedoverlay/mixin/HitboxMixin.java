package io.github.phantombridger.enhancedoverlay.mixin;

import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexRendering;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.Color;

@Mixin(EntityRenderDispatcher.class)
public class HitboxMixin {
    @Inject(
            method = "renderHitbox(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/entity/Entity;FFFF)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void customHitboxRenderer(MatrixStack matrices, VertexConsumer vertices, Entity entity, float tickDelta, float red, float green, float blue, CallbackInfo ci) {
        if (!ConfigScreen.CONFIG.instance().customHitboxMode) {
            return; // skips the following code if custom hitbox mode is disabled
        }

        boolean shouldRender = false;

        // config checks for the hitboxes
        if (entity instanceof PlayerEntity player) {
            if (player.isGliding()) {
                shouldRender = ConfigScreen.CONFIG.instance().playerElytraHitbox;
            } else if (player.isSwimming()) {
                shouldRender = ConfigScreen.CONFIG.instance().playerSwimmingHitbox;
            } else if (player.isCrawling()) {
                shouldRender = ConfigScreen.CONFIG.instance().playerCrawlingHitbox;
            } else if (player.isSleeping()) {
                shouldRender = ConfigScreen.CONFIG.instance().playerSleepingHitbox;
            } else if (player.isSneaking()) {
                shouldRender = ConfigScreen.CONFIG.instance().playerSneakingHitbox;
            } else {
                shouldRender = ConfigScreen.CONFIG.instance().playerDefaultHitbox;
            }
        } else if (entity instanceof BoatEntity || entity instanceof ChestBoatEntity) {
            shouldRender = ConfigScreen.CONFIG.instance().boatHitbox;
        } else if (entity instanceof ArrowEntity || entity instanceof SpectralArrowEntity) {
            shouldRender = ConfigScreen.CONFIG.instance().arrowHitbox;
        } else if (entity instanceof TridentEntity) {
            shouldRender = ConfigScreen.CONFIG.instance().tridentHitbox;
        } else if (entity instanceof FishingBobberEntity) {
            shouldRender = ConfigScreen.CONFIG.instance().fishingBobberHitbox;
        } else if (entity instanceof EnderPearlEntity) {
            shouldRender = ConfigScreen.CONFIG.instance().enderPearlHitbox;
        } else if (entity instanceof ExperienceOrbEntity) {
            shouldRender = ConfigScreen.CONFIG.instance().xpOrbHitbox;
        } else if (entity instanceof EndCrystalEntity) {
            shouldRender = ConfigScreen.CONFIG.instance().endCrystalHitbox;
        } else {
            shouldRender = ConfigScreen.CONFIG.instance().otherEntityHitbox;
        }

        // cancel Vanilla Hitbox because it gets replaced
        ci.cancel();

        // cancel Hitbox Rendering for entities that were not enabled in the config
        if (!shouldRender) return;

        // Hitbox Rendering without the additional lines
        Box box = entity.getBoundingBox().offset(-entity.getX(), -entity.getY(), -entity.getZ());
        Color hitboxColor = ConfigScreen.CONFIG.instance().customHitboxColor; // custom Hitbox Color from the config
        VertexRendering.drawBox(matrices, vertices, box, hitboxColor.getRed() / 255f, hitboxColor.getGreen() / 255f, hitboxColor.getBlue() / 255f, hitboxColor.getAlpha() / 255f);
    }
}