package io.github.phantombridger.enhancedoverlay.config;

public enum TextShadowMode {
    ENABLED("Enabled"),
    DISABLED("Disabled"),
    DEFAULT("Default");

    private final String displayName;
    TextShadowMode(String displayName) { this.displayName = displayName; }
    public String getDisplayName() { return displayName; }
}