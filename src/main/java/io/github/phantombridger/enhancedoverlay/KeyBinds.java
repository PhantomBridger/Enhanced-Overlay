package io.github.phantombridger.enhancedoverlay;

import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class Keybinds {
    public static KeyMapping TOGGLE_HIDE_CHAT;
    public static KeyMapping TOGGLE_HITBOX_MODIFICATIONS;
    public static KeyMapping TOGGLE_NAMETAG_BACKGROUND;
    public static KeyMapping TOGGLE_TEXT_BACKGROUND;
    public static KeyMapping OPEN_CONFIG;

    public static void registerConfigKeybind() {
        OPEN_CONFIG = new KeyMapping(
                "key.enhanced_overlay.open_config",
                GLFW.GLFW_KEY_UNKNOWN,
                KeyMapping.Category.MISC
        );
        KeyBindingHelper.registerKeyBinding(OPEN_CONFIG);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (OPEN_CONFIG.consumeClick()) {
                if (client.player != null) {
                    client.setScreen(ConfigScreen.configScreen(client.screen));
                }
            }
        });
    }

    public static void registerToggleBind(
            KeyMapping keyMapping,
            BooleanSupplier getter,
            Consumer<Boolean> setter,
            Component messagePrefix,
            String trueLabel,
            String falseLabel
    ) {
        KeyBindingHelper.registerKeyBinding(keyMapping);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyMapping.consumeClick()) {
                boolean newValue = !getter.getAsBoolean();
                setter.accept(newValue);

                if (client.player != null) {
                    client.player.displayClientMessage(
                            messagePrefix.copy()
                                    .append(": ")
                                    .append(Component.literal(newValue ? trueLabel : falseLabel)),
                            true
                    );
                }
            }
        });
    }
    public static void registerAll() {
        registerConfigKeybind();

        TOGGLE_HIDE_CHAT = new KeyMapping(
                "key.enhanced_overlay.toggle_hide_chat",
                GLFW.GLFW_KEY_UNKNOWN,
                KeyMapping.Category.MISC
        );
        registerToggleBind(
                TOGGLE_HIDE_CHAT,
                () -> ConfigScreen.CONFIG.instance().hideChat,
                val -> ConfigScreen.CONFIG.instance().hideChat = val,
                Component.literal("Chat"),
                "Hidden",
                "Shown"
        );

        TOGGLE_HITBOX_MODIFICATIONS = new KeyMapping(
                "key.enhanced_overlay.toggle_hitbox_modifications",
                GLFW.GLFW_KEY_UNKNOWN,
                KeyMapping.Category.MISC
        );
        registerToggleBind(
                TOGGLE_HITBOX_MODIFICATIONS,
                () -> ConfigScreen.CONFIG.instance().hitboxModifications,
                val -> ConfigScreen.CONFIG.instance().hitboxModifications = val,
                Component.literal("Hitbox Modifications"),
                "Enabled", "Disabled"
        );

        TOGGLE_NAMETAG_BACKGROUND = new KeyMapping(
                "key.enhanced_overlay.toggle_nametag_background",
                GLFW.GLFW_KEY_UNKNOWN,
                KeyMapping.Category.MISC
        );
        registerToggleBind(
                TOGGLE_NAMETAG_BACKGROUND,
                () -> ConfigScreen.CONFIG.instance().removeNametagBackground,
                val -> ConfigScreen.CONFIG.instance().removeNametagBackground = val,
                Component.literal("Nametag Background"),
                "None", "Vanilla"
        );

        TOGGLE_TEXT_BACKGROUND = new KeyMapping(
                "key.enhanced_overlay.toggle_text_background",
                GLFW.GLFW_KEY_UNKNOWN,
                KeyMapping.Category.MISC
        );
        registerToggleBind(
                TOGGLE_TEXT_BACKGROUND,
                () -> ConfigScreen.CONFIG.instance().removeTextDisplayBackground,
                val -> ConfigScreen.CONFIG.instance().removeTextDisplayBackground = val,
                Component.literal("Text Display Background"),
                "None", "Vanilla"
        );
    }
}
