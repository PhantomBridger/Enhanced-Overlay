package io.github.phantombridger.enhancedoverlay.mixin;

import net.minecraft.client.gui.hud.PlayerListHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;

@Mixin(PlayerListHud.class)
public class TablistMixin {
    // Remove tablist background one
    @ModifyArg(
            method = "render(Lnet/minecraft/client/gui/DrawContext;ILnet/minecraft/scoreboard/Scoreboard;Lnet/minecraft/scoreboard/ScoreboardObjective;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V",
                    ordinal = 0
            ),
            index = 4
    )
    private int disableTablistBackgroundOne(int color) {
        if (ConfigScreen.CONFIG.instance().removeTablistBackground) {
            return 0;
        } else {
            return color;
        }
    }

    // Remove tablist background two
    @ModifyArg(
            method = "render(Lnet/minecraft/client/gui/DrawContext;ILnet/minecraft/scoreboard/Scoreboard;Lnet/minecraft/scoreboard/ScoreboardObjective;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V",
                    ordinal = 1
            ),
            index = 4
    )
    private int disableTablistBackgroundTwo(int color) {
        if (ConfigScreen.CONFIG.instance().removeTablistBackground) {
            return 0;
        } else {
            return color;
        }
    }

    // Remove tablist username background
    @ModifyArg(
            method = "render(Lnet/minecraft/client/gui/DrawContext;ILnet/minecraft/scoreboard/Scoreboard;Lnet/minecraft/scoreboard/ScoreboardObjective;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V",
                    ordinal = 2
            ),
            index = 4
    )
    private int disableTablistUsernameBackground(int color) {
        if (ConfigScreen.CONFIG.instance().removeTablistUsernameBackground) {
            return 0;
        } else {
            return color;
        }
    }

    // Remove tablist background three
    @ModifyArg(
            method = "render(Lnet/minecraft/client/gui/DrawContext;ILnet/minecraft/scoreboard/Scoreboard;Lnet/minecraft/scoreboard/ScoreboardObjective;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V",
                    ordinal = 3
            ),
            index = 4
    )
    private int disableTablistBackgroundThree(int color) {
        if (ConfigScreen.CONFIG.instance().removeTablistBackground) {
            return 0;
        } else {
            return color;
        }
    }
}
