package io.github.phantombridger.enhancedoverlay;

import net.fabricmc.api.ClientModInitializer;

import io.github.phantombridger.enhancedoverlay.config.ConfigScreen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnhancedOverlay implements ClientModInitializer {
	public static final String MOD_ID = "enhanced-overlay";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {

        ConfigScreen.CONFIG.load();
		Keybinds.registerAll();
		LOGGER.info("Loaded Enhanced Overlay");
	}
}