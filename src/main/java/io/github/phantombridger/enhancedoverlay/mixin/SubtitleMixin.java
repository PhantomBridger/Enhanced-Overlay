package io.github.phantombridger.enhancedoverlay.mixin;

import io.github.phantombridger.enhancedoverlay.config.BackgroundColorMode;
import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;
import net.minecraft.client.gui.hud.SubtitlesHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(SubtitlesHud.class)
public class SubtitleMixin {
    // remove subtitles background
    @ModifyArg(
            method = "render(Lnet/minecraft/client/gui/DrawContext;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V",
                    ordinal = 0
            ),
            index = 4
    )
    private int subtitleBackground(int color) {
        if (ConfigScreen.CONFIG.instance().subtitleBackground == BackgroundColorMode.NONE) {
            return 0;
        } else if  (ConfigScreen.CONFIG.instance().subtitleBackground == BackgroundColorMode.CUSTOM) {
            return ConfigScreen.CONFIG.instance().subtitleBackgroundColor.getRGB();
        } else if (ConfigScreen.CONFIG.instance().subtitleBackground == BackgroundColorMode.DEFAULT) {
            return color;
        } else {
            return color; // added this so it doesn't break if the value is not None, Custom or DEFAULT
        }
    }
}
