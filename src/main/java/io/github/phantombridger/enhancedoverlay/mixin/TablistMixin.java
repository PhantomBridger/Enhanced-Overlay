package io.github.phantombridger.enhancedoverlay.mixin;

import net.minecraft.client.gui.components.PlayerTabOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;

@Mixin(PlayerTabOverlay.class)
public class TablistMixin {
    @ModifyArg(
            method = "extractRenderState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;fill(IIIII)V",
                    ordinal = 0
            ),
            index = 4
    )
    private int tablistBackgroundOne(int color) {
        return switch (ConfigScreen.CONFIG.instance().tablistBackground) {
            case NONE -> 0;
            case CUSTOM -> ConfigScreen.CONFIG.instance().tablistBackgroundColor.getRGB();
            default -> color;
        };
    }
    @ModifyArg(
            method = "extractRenderState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;fill(IIIII)V",
                    ordinal = 1
            ),
            index = 4
    )
    private int tablistBackgroundTwo(int color) {
        return switch (ConfigScreen.CONFIG.instance().tablistBackground) {
            case NONE -> 0;
            case CUSTOM -> ConfigScreen.CONFIG.instance().tablistBackgroundColor.getRGB();
            default -> color;
        };
    }
    @ModifyArg(
            method = "extractRenderState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;fill(IIIII)V",
                    ordinal = 3
            ),
            index = 4
    )
    private int tablistBackgroundThree(int color) {
        return switch (ConfigScreen.CONFIG.instance().tablistBackground) {
            case NONE -> 0;
            case CUSTOM -> ConfigScreen.CONFIG.instance().tablistBackgroundColor.getRGB();
            default -> color;
        };
    }
    @ModifyArg(
            method = "extractRenderState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;fill(IIIII)V",
                    ordinal = 2
            ),
            index = 4
    )
    private int tablistUsernameBackground(int color) {
        return switch (ConfigScreen.CONFIG.instance().tablistUsernameBackground) {
            case NONE -> 0;
            case CUSTOM -> ConfigScreen.CONFIG.instance().tablistUsernameBackgroundColor.getRGB();
            default -> color;
        };
    }
}
