package io.github.phantombridger.enhancedoverlay.mixin;

import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemStack.class)
public class ItemMixin {
    @ModifyReturnValue(method = "hasFoil", at = @At("RETURN"))
    private boolean fakeEnchantGlint(boolean original) {
        Item item = ((ItemStack)(Object)this).getItem();

        if (ConfigScreen.CONFIG.instance().fakeEnchantmentGlint && ConfigScreen.CONFIG.instance().fakeEnchantmentGlintItems.contains(item)) {
            return true;
        } else {
            return original;
        }
    }
}
