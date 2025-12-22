package io.github.phantombridger.enhancedoverlay.config;

public enum BackgroundColorMode {
    DEFAULT("Default"),
    NONE("None"),
    CUSTOM("Custom");

    private final String displayName;
    BackgroundColorMode(String displayName) { this.displayName = displayName; }
    public String getDisplayName() { return displayName; }
}