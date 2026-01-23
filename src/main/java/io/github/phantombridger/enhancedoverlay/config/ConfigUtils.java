package io.github.phantombridger.enhancedoverlay.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConfirmScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;

import java.awt.Color;

public class ConfigUtils {
    public static void reset(ConfigScreen config, Screen screen) {
        MutableComponent title = (MutableComponent) Component.literal("Are you sure you want to reset the config?")
                .withStyle(style -> style.withColor(TextColor.fromRgb(0x00FF00)));
        MutableComponent description = (MutableComponent) Component.literal("This cannot be undone.")
                .withStyle(style -> style.withColor(TextColor.fromRgb(0xFF0000)).withBold(true));

        Minecraft.getInstance().setScreen(new ConfirmScreen(
                result -> {
                    if (result) {
                        resetConfig(config);
                        Minecraft.getInstance().setScreen(null);
                    } else {
                        Minecraft.getInstance().setScreen(ConfigScreen.configScreen(screen));
                    }
                },
                title,
                description
        ));
    }
    public static void resetConfig(ConfigScreen config) {
        config.hitboxModifications = true;
        config.hitboxColor = new Color(255, 255, 255, 255);

        config.playerDefaultHitbox = false;
        config.playerSneakingHitbox = false;
        config.playerElytraHitbox = true;
        config.playerSwimmingHitbox = true;
        config.playerCrawlingHitbox = true;

        config.enderDragonMainHitbox = false;
        config.enderDragonHitboxes = true;
        config.enderDragonHitboxesColor = new Color(255, 255, 255, 255);

        config.boatHitbox = false;
        config.arrowHitbox = true;
        config.tridentHitbox = true;
        config.fishingBobberHitbox = false;
        config.enderPearlHitbox = true;
        config.windChargeHitbox = true;
        config.xpOrbHitbox = false;
        config.endCrystalHitbox = false;
        config.otherEntityHitbox = false;

        config.scoreboardTextShadow = TextShadowMode.ENABLED;
        config.scoreboardBackground = BackgroundColorMode.NONE;
        config.scoreboardBackgroundColor = new Color(0, 0, 0, 42);
        config.chatInputBackground = BackgroundColorMode.CUSTOM;
        config.chatInputBackgroundColor = new Color(0, 0, 0, 42);
        config.hideChat = false;
        config.subtitleBackground = BackgroundColorMode.NONE;
        config.subtitleBackgroundColor = new Color(0, 0, 0, 42);
        config.tablistBackground = BackgroundColorMode.NONE;
        config.tablistBackgroundColor = new Color(0, 0, 0, 127);
        config.tablistUsernameBackground = BackgroundColorMode.DEFAULT;
        config.tablistUsernameBackgroundColor = new Color(0, 0, 0, 33);
        config.debugScreenTextShadow = TextShadowMode.ENABLED;
        config.debugScreenBackground = BackgroundColorMode.NONE;
        config.debugScreenBackgroundColor = new Color(0, 0, 0, 42);

        config.nametagTextShadow = TextShadowMode.ENABLED;
        config.removeNametagBackground = true;
        config.textDisplayTextShadow = TextShadowMode.ENABLED;
        config.removeTextDisplayBackground = true;

        config.fakeEnchantmentGlint = true;

        ConfigScreen.CONFIG.save();
    }

    public static void setToVanilla(ConfigScreen config, Screen screen) {
        MutableComponent title = (MutableComponent) Component.literal("Are you sure you want to set the config to vanilla values?")
                .withStyle(style -> style.withColor(TextColor.fromRgb(0x00FF00)));
        MutableComponent description = (MutableComponent) Component.literal("This cannot be undone.")
                .withStyle(style -> style.withColor(TextColor.fromRgb(0xFF0000)).withBold(true));

        Minecraft.getInstance().setScreen(new ConfirmScreen(
                result -> {
                    if (result) {
                        setConfigToVanilla(config);
                        Minecraft.getInstance().setScreen(null);
                    } else {
                        Minecraft.getInstance().setScreen(ConfigScreen.configScreen(screen));
                    }
                },
                title,
                description
        ));
    }

    public static void setConfigToVanilla(ConfigScreen config) {
        config.hitboxModifications = false;
        config.hitboxColor = new Color(255, 255, 255, 255);

        config.playerDefaultHitbox = true;
        config.playerSneakingHitbox = true;
        config.playerElytraHitbox = true;
        config.playerSwimmingHitbox = true;
        config.playerCrawlingHitbox = true;

        config.enderDragonMainHitbox = true;
        config.enderDragonHitboxes = true;
        config.enderDragonHitboxesColor = new Color(0, 255, 0, 255);

        config.boatHitbox = true;
        config.arrowHitbox = true;
        config.tridentHitbox = true;
        config.fishingBobberHitbox = true;
        config.enderPearlHitbox = true;
        config.windChargeHitbox = true;
        config.xpOrbHitbox = true;
        config.endCrystalHitbox = true;
        config.otherEntityHitbox = true;

        config.scoreboardTextShadow = TextShadowMode.DEFAULT;
        config.scoreboardBackground = BackgroundColorMode.DEFAULT;
        config.scoreboardBackgroundColor = new Color(0, 0, 0, 42);
        config.chatInputBackground = BackgroundColorMode.DEFAULT;
        config.chatInputBackgroundColor = new Color(0, 0, 0, 42);
        config.hideChat = false;
        config.subtitleBackground = BackgroundColorMode.DEFAULT;
        config.subtitleBackgroundColor = new Color(0, 0, 0, 42);
        config.tablistBackground = BackgroundColorMode.DEFAULT;
        config.tablistBackgroundColor = new Color(0, 0, 0, 127);
        config.tablistUsernameBackground = BackgroundColorMode.DEFAULT;
        config.tablistUsernameBackgroundColor = new Color(0, 0, 0, 33);
        config.debugScreenTextShadow = TextShadowMode.DEFAULT;
        config.debugScreenBackground = BackgroundColorMode.DEFAULT;
        config.debugScreenBackgroundColor = new Color(0, 0, 0, 42);

        config.nametagTextShadow = TextShadowMode.DEFAULT;
        config.removeNametagBackground = false;
        config.textDisplayTextShadow = TextShadowMode.DEFAULT;
        config.removeTextDisplayBackground = false;

        config.fakeEnchantmentGlint = false;

        ConfigScreen.CONFIG.save();
    }
}
