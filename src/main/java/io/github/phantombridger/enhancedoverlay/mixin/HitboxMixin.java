package io.github.phantombridger.enhancedoverlay.mixin;

import net.minecraft.client.renderer.debug.EntityHitboxDebugRenderer;
import net.minecraft.gizmos.GizmoStyle;
import net.minecraft.gizmos.Gizmos;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.enderdragon.EnderDragonPart;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.entity.projectile.arrow.SpectralArrow;
import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.WindCharge;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEnderpearl;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityHitboxDebugRenderer.class)
public class HitboxMixin {
    @Inject(
            method = "showHitboxes(Lnet/minecraft/world/entity/Entity;FZ)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void customMainHitbox(Entity entity, float f, boolean bl, CallbackInfo ci) {
        if (ConfigScreen.CONFIG.instance().hitboxModifications) {
            ci.cancel();
        } else {
            return;
        }
        boolean shouldRender;
        switch (entity) {
            case Player player -> {
                if (player.isFallFlying()) shouldRender = ConfigScreen.CONFIG.instance().playerElytraHitbox;
                else if (player.isSwimming()) shouldRender = ConfigScreen.CONFIG.instance().playerSwimmingHitbox;
                else if (player.isVisuallySwimming()) shouldRender = ConfigScreen.CONFIG.instance().playerSwimmingHitbox;
                else if (player.isVisuallyCrawling()) shouldRender = ConfigScreen.CONFIG.instance().playerCrawlingHitbox;
                else if (player.isCrouching()) shouldRender = ConfigScreen.CONFIG.instance().playerSneakingHitbox;
                else shouldRender = ConfigScreen.CONFIG.instance().playerDefaultHitbox;
            }
            case Boat boat -> shouldRender = ConfigScreen.CONFIG.instance().boatHitbox;
            case ChestBoat chestBoat -> shouldRender = ConfigScreen.CONFIG.instance().boatHitbox;
            case Arrow arrow -> shouldRender = ConfigScreen.CONFIG.instance().arrowHitbox;
            case SpectralArrow spectralArrow -> shouldRender = ConfigScreen.CONFIG.instance().arrowHitbox;
            case ThrownTrident thrownTrident -> shouldRender = ConfigScreen.CONFIG.instance().tridentHitbox;
            case FishingHook fishingHook -> shouldRender = ConfigScreen.CONFIG.instance().fishingBobberHitbox;
            case ThrownEnderpearl thrownEnderpearl -> shouldRender = ConfigScreen.CONFIG.instance().enderPearlHitbox;
            case WindCharge windCharge -> shouldRender = ConfigScreen.CONFIG.instance().windChargeHitbox;
            case ExperienceOrb experienceOrb -> shouldRender = ConfigScreen.CONFIG.instance().xpOrbHitbox;
            case EndCrystal endCrystal -> shouldRender = ConfigScreen.CONFIG.instance().endCrystalHitbox;
            case EnderDragon enderDragon -> shouldRender = ConfigScreen.CONFIG.instance().enderDragonMainHitbox;
            case null -> shouldRender = false;
            default -> shouldRender = ConfigScreen.CONFIG.instance().otherEntityHitbox;
        }
        if (shouldRender) {
            int color = ConfigScreen.CONFIG.instance().hitboxColor.getRGB();
            Vec3 delta = entity.getPosition(f).subtract(entity.position());
            AABB box = entity.getBoundingBox().move(delta);
            Gizmos.cuboid(box, GizmoStyle.stroke(color));
        }
        if (entity instanceof EnderDragon dragon && ConfigScreen.CONFIG.instance().enderDragonHitboxes) {
            int color = ConfigScreen.CONFIG.instance().enderDragonHitboxesColor.getRGB();
            for (EnderDragonPart part : dragon.getSubEntities()) {
                Vec3 delta = part.getPosition(f).subtract(part.position());
                AABB box = part.getBoundingBox().move(delta);
                Gizmos.cuboid(box, GizmoStyle.stroke(color));
            }
        }
    }
}
