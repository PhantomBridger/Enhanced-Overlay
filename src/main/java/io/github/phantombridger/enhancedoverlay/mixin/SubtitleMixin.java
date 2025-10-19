package io.github.phantombridger.enhancedoverlay.mixin;

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
    private int removeSubtitlesBackground(int color) {
        return 0;
    }
}
