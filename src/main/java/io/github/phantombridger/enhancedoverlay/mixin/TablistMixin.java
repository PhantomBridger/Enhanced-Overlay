package io.github.phantombridger.enhancedoverlay.mixin;

import io.github.phantombridger.enhancedoverlay.config.BackgroundColorMode;
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
    private int tablistBackgroundOne(int color) {
        if (ConfigScreen.CONFIG.instance().tablistBackground == BackgroundColorMode.NONE) {
            return 0;
        } else if  (ConfigScreen.CONFIG.instance().tablistBackground == BackgroundColorMode.CUSTOM) {
            return ConfigScreen.CONFIG.instance().tablistBackgroundColor.getRGB();
        } else if (ConfigScreen.CONFIG.instance().tablistBackground == BackgroundColorMode.DEFAULT) {
            return color;
        } else {
            return color; // added this so it doesn't break if the value is not None, Custom or DEFAULT
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
    private int tablistBackgroundTwo(int color) {
        if (ConfigScreen.CONFIG.instance().tablistBackground == BackgroundColorMode.NONE) {
            return 0;
        } else if  (ConfigScreen.CONFIG.instance().tablistBackground == BackgroundColorMode.CUSTOM) {
            return ConfigScreen.CONFIG.instance().tablistBackgroundColor.getRGB();
        } else if (ConfigScreen.CONFIG.instance().tablistBackground == BackgroundColorMode.DEFAULT) {
            return color;
        } else {
            return color; // added this so it doesn't break if the value is not None, Custom or DEFAULT
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
    private int tablistUsernameBackground(int color) {
        if (ConfigScreen.CONFIG.instance().tablistUsernameBackground == BackgroundColorMode.NONE) {
            return 0;
        } else if  (ConfigScreen.CONFIG.instance().tablistUsernameBackground == BackgroundColorMode.CUSTOM) {
            return ConfigScreen.CONFIG.instance().tablistUsernameBackgroundColor.getRGB();
        } else if (ConfigScreen.CONFIG.instance().tablistUsernameBackground == BackgroundColorMode.DEFAULT) {
            return color;
        } else {
            return color; // added this so it doesn't break if the value is not None, Custom or DEFAULT
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
    private int tablistBackgroundThree(int color) {
        if (ConfigScreen.CONFIG.instance().tablistBackground == BackgroundColorMode.NONE) {
            return 0;
        } else if  (ConfigScreen.CONFIG.instance().tablistBackground == BackgroundColorMode.CUSTOM) {
            return ConfigScreen.CONFIG.instance().tablistBackgroundColor.getRGB();
        } else if (ConfigScreen.CONFIG.instance().tablistBackground == BackgroundColorMode.DEFAULT) {
            return color;
        } else {
            return color; // added this so it doesn't break if the value is not None, Custom or DEFAULT
        }
    }
}
