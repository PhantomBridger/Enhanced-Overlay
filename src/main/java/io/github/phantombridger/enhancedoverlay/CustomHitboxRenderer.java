package io.github.phantombridger.enhancedoverlay;

import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexRendering;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.*;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.awt.Color;
import java.util.Objects;

public class CustomHitboxRenderer {
    public static void renderCustomHitboxes() {
        WorldRenderEvents.BEFORE_ENTITIES.register(context -> {
            if (!ConfigScreen.CONFIG.instance().customHitboxMode) return;

            MinecraftClient mc = MinecraftClient.getInstance();
            MatrixStack matrices = Objects.requireNonNull(context.matrixStack());
            VertexConsumer vertices = Objects.requireNonNull(context.consumers()).getBuffer(RenderLayer.getLines());
            float tickDelta = context.tickCounter().getTickProgress(false);

            int playerChunkX = mc.player.getChunkPos().x;
            int playerChunkZ = mc.player.getChunkPos().z;

            int hitboxRenderDistance = ConfigScreen.CONFIG.instance().customHitboxRenderDistance;

            boolean renderPlayerDefaultHitbox = ConfigScreen.CONFIG.instance().playerDefaultHitbox;
            boolean renderPlayerElytraHitbox = ConfigScreen.CONFIG.instance().playerElytraHitbox;
            boolean renderPlayerSwimmingHitbox = ConfigScreen.CONFIG.instance().playerSwimmingHitbox;
            boolean renderPlayerSleepingHitbox = ConfigScreen.CONFIG.instance().playerSleepingHitbox;
            boolean renderPlayerSneakingHitbox = ConfigScreen.CONFIG.instance().playerSneakingHitbox;
            boolean renderPlayerCrawlingHitbox = ConfigScreen.CONFIG.instance().playerCrawlingHitbox;
            boolean renderOtherEntityHitbox = ConfigScreen.CONFIG.instance().otherEntityHitbox;
            boolean renderBoatHitbox = ConfigScreen.CONFIG.instance().boatHitbox;
            boolean renderArrowHitbox = ConfigScreen.CONFIG.instance().arrowHitbox;
            boolean renderTridentHitbox = ConfigScreen.CONFIG.instance().tridentHitbox;
            boolean renderFishingBobberHitbox = ConfigScreen.CONFIG.instance().fishingBobberHitbox;
            boolean renderEnderPearlHitbox = ConfigScreen.CONFIG.instance().enderPearlHitbox;
            boolean renderXpOrbHitbox = ConfigScreen.CONFIG.instance().xpOrbHitbox;
            boolean renderEndCrystalHitbox = ConfigScreen.CONFIG.instance().endCrystalHitbox;

            Color hitboxColor = ConfigScreen.CONFIG.instance().customHitboxColor;
            Vec3d cameraPos = mc.gameRenderer.getCamera().getPos();

            for (Entity entity : context.world().getEntities()) {
                // skip own player hitbox in first person
                if (entity == mc.player && mc.options.getPerspective().isFirstPerson()) continue;

                int entityChunkX = entity.getChunkPos().x;
                int entityChunkZ = entity.getChunkPos().z;

                // skip hitboxes for entities outside the hitbox render distance from the config
                if (Math.abs(entityChunkX - playerChunkX) > hitboxRenderDistance || Math.abs(entityChunkZ - playerChunkZ) > hitboxRenderDistance) {
                    continue;
                }

                // custom hitboxes are not rendered by default
                boolean shouldRender = false;

                // player and entity checks from the config to enable custom hitboxes for specific entities or player states
                if (entity instanceof LivingEntity living) {
                    if (living instanceof PlayerEntity player) {
                        if (player.isGliding()) {
                            shouldRender = renderPlayerElytraHitbox;
                        } else if (player.isSwimming()) {
                            shouldRender = renderPlayerSwimmingHitbox;
                        } else if (player.isCrawling()) {
                            shouldRender = renderPlayerCrawlingHitbox;
                        } else if (player.isSleeping()) {
                            shouldRender = renderPlayerSleepingHitbox;
                        } else if (player.isSneaking()) {
                            shouldRender = renderPlayerSneakingHitbox;
                        } else {
                            shouldRender = renderPlayerDefaultHitbox;
                        }
                    } else {
                        shouldRender = renderOtherEntityHitbox;
                    }
                } else if (entity instanceof BoatEntity || entity instanceof ChestBoatEntity) {
                    shouldRender = renderBoatHitbox;
                } else if (entity instanceof ArrowEntity || entity instanceof SpectralArrowEntity) {
                    shouldRender = renderArrowHitbox;
                } else if (entity instanceof TridentEntity) {
                    shouldRender = renderTridentHitbox;
                } else if (entity instanceof FishingBobberEntity) {
                    shouldRender = renderFishingBobberHitbox;
                } else if (entity instanceof EnderPearlEntity) {
                    shouldRender = renderEnderPearlHitbox;
                } else if (entity instanceof ExperienceOrbEntity) {
                    shouldRender = renderXpOrbHitbox;
                } else if (entity instanceof EndCrystalEntity) {
                    shouldRender = renderEndCrystalHitbox;
                } else {
                    shouldRender = renderOtherEntityHitbox;
                }

                // if custom hitboxes are not enabled for an entity or player state skip the hitbox rendering
                if (!shouldRender) continue;

                Vec3d pos = entity.getLerpedPos(tickDelta);

                double interpolatedX = pos.x;
                double interpolatedY = pos.y;
                double interpolatedZ = pos.z;

                Box box = entity.getBoundingBox();
                Box newHitbox = new Box(
                        box.minX - entity.getX() + interpolatedX,
                        box.minY - entity.getY() + interpolatedY,
                        box.minZ - entity.getZ() + interpolatedZ,
                        box.maxX - entity.getX() + interpolatedX,
                        box.maxY - entity.getY() + interpolatedY,
                        box.maxZ - entity.getZ() + interpolatedZ
                ).offset(-cameraPos.x, -cameraPos.y, -cameraPos.z);

                VertexRendering.drawBox(
                        matrices, vertices,
                        newHitbox.minX, newHitbox.minY, newHitbox.minZ,
                        newHitbox.maxX, newHitbox.maxY, newHitbox.maxZ,
                        hitboxColor.getRed() / 255F,
                        hitboxColor.getGreen() / 255F,
                        hitboxColor.getBlue() / 255F,
                        hitboxColor.getAlpha() / 255F
                );
            }
        });
    }
}