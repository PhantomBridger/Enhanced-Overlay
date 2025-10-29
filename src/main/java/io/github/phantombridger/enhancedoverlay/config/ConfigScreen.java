package io.github.phantombridger.enhancedoverlay.config;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.controller.EnumControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.platform.YACLPlatform;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ConfigScreen {
    public static final ConfigClassHandler<ConfigScreen> CONFIG = ConfigClassHandler.createBuilder(ConfigScreen.class)
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(YACLPlatform.getConfigDir().resolve("enhancedoverlay.json"))
                    .build())
            .build();

    @SerialEntry public boolean removeNametagBackground = true;
    @SerialEntry public boolean nametagShadow = true;
    @SerialEntry public boolean removeScoreboardBackground = true;
    @SerialEntry public boolean scoreboardTextShadow = true;
    @SerialEntry public boolean removeSubtitleBackground = true;
    @SerialEntry public boolean removeTablistBackground = true;
    @SerialEntry public boolean removeTablistUsernameBackground = false;
    @SerialEntry public TextDisplayShadowMode forceTextDisplayShadow = TextDisplayShadowMode.True;
    @SerialEntry public boolean forceRemoveTextDisplayBackground = true;

    public static Screen configScreen(Screen parent) {
        return YetAnotherConfigLib.create(CONFIG, ((defaults, config, builder) -> builder
                .title(Text.literal("Enhanced Overlay"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("HUD Modifications"))
                        .tooltip(Text.literal("Configure HUD Modifications"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Nametags"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Remove Nametag Background"))
                                        .description(OptionDescription.of(Text.literal("Removes the Background of Nametags")))
                                        .binding(defaults.removeNametagBackground, () -> config.removeNametagBackground, newVal -> config.removeNametagBackground = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Nametag Text Shadow"))
                                        .description(OptionDescription.of(Text.literal("Adds Text Shadow to Nametags")))
                                        .binding(defaults.nametagShadow, () -> config.nametagShadow, newVal -> config.nametagShadow = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Scoreboard"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Remove Scoreboard Background"))
                                        .description(OptionDescription.of(Text.literal("Removes the Background of the Scoreboard")))
                                        .binding(defaults.removeScoreboardBackground, () -> config.removeScoreboardBackground, newVal -> config.removeScoreboardBackground = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Scoreboard Text Shadow"))
                                        .description(OptionDescription.of(Text.literal("Adds Text Shadow to the Scoreboard")))
                                        .binding(defaults.scoreboardTextShadow, () -> config.scoreboardTextShadow, newVal -> config.scoreboardTextShadow = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Subtitles"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Remove Subtitle Background"))
                                        .description(OptionDescription.of(Text.literal("Removes the Background of the Subtitles")))
                                        .binding(defaults.removeSubtitleBackground, () -> config.removeSubtitleBackground, newVal -> config.removeSubtitleBackground = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Tablist"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Remove Tablist Background"))
                                        .description(OptionDescription.of(Text.literal("Removes the Background of the Tablist")))
                                        .binding(defaults.removeTablistBackground, () -> config.removeTablistBackground, newVal -> config.removeTablistBackground = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Remove Tablist Username Background"))
                                        .description(OptionDescription.of(Text.literal("Removes the Username background of the Tablist")))
                                        .binding(defaults.removeTablistUsernameBackground, () -> config.removeTablistUsernameBackground, newVal -> config.removeTablistUsernameBackground = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Text Displays"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Force Remove Text Display Background"))
                                        .description(OptionDescription.of(Text.literal("Makes it so Text Displays are always rendered without a Background")))
                                        .binding(defaults.forceRemoveTextDisplayBackground, () -> config.forceRemoveTextDisplayBackground, newVal -> config.forceRemoveTextDisplayBackground = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<TextDisplayShadowMode>createBuilder()
                                        .name(Text.literal("Force Text Display Shadow"))
                                        .description(OptionDescription.of(Text.literal("Makes it so Text Displays are always rendered with Text Shadow")))
                                        .binding(defaults.forceTextDisplayShadow, () -> config.forceTextDisplayShadow, newVal -> config.forceTextDisplayShadow = newVal)
                                        .controller((opt) ->
                                                EnumControllerBuilder.create(opt)
                                                        .enumClass(TextDisplayShadowMode.class)
                                                        .formatValue((it) -> Text.literal(it.name())))
                                        .build())
                                .build())
                        .build())
        )).generateScreen(parent);
    }
}