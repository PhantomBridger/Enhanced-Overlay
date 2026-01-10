package io.github.phantombridger.enhancedoverlay.mixin;

import io.github.phantombridger.enhancedoverlay.config.BackgroundColorMode;
import net.minecraft.client.gui.components.PlayerTabOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;

@Mixin(PlayerTabOverlay.class)
public class TablistMixin {
    @ModifyArg(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V",
                    ordinal = 0
            ),
            index = 4
    )
    private int tablistBackgroundOne(int color) {
        if (ConfigScreen.CONFIG.instance().tablistBackground == BackgroundColorMode.NONE) {
            return 0;
        } else if (ConfigScreen.CONFIG.instance().tablistBackground == BackgroundColorMode.CUSTOM) {
            return ConfigScreen.CONFIG.instance().tablistBackgroundColor.getRGB();
        } else {
            return color;
        }
    }
    @ModifyArg(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V",
                    ordinal = 1
            ),
            index = 4
    )
    private int tablistBackgroundTwo(int color) {
        if (ConfigScreen.CONFIG.instance().tablistBackground == BackgroundColorMode.NONE) {
            return 0;
        } else if (ConfigScreen.CONFIG.instance().tablistBackground == BackgroundColorMode.CUSTOM) {
            return ConfigScreen.CONFIG.instance().tablistBackgroundColor.getRGB();
        } else {
            return color;
        }
    }
    @ModifyArg(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V",
                    ordinal = 3
            ),
            index = 4
    )
    private int tablistBackgroundThree(int color) {
        if (ConfigScreen.CONFIG.instance().tablistBackground == BackgroundColorMode.NONE) {
            return 0;
        } else if (ConfigScreen.CONFIG.instance().tablistBackground == BackgroundColorMode.CUSTOM) {
            return ConfigScreen.CONFIG.instance().tablistBackgroundColor.getRGB();
        } else {
            return color;
        }
    }
    @ModifyArg(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V",
                    ordinal = 2
            ),
            index = 4
    )
    private int tablistUsernameBackground(int color) {
        if (ConfigScreen.CONFIG.instance().tablistUsernameBackground == BackgroundColorMode.NONE) {
            return 0;
        } else if (ConfigScreen.CONFIG.instance().tablistUsernameBackground == BackgroundColorMode.CUSTOM) {
            return ConfigScreen.CONFIG.instance().tablistUsernameBackgroundColor.getRGB();
        } else {
            return color;
        }
    }
}
