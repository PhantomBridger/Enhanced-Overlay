package io.github.phantombridger.enhancedoverlay.config;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.controller.ColorControllerBuilder;
import dev.isxander.yacl3.api.controller.EnumControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.platform.YACLPlatform;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.awt.Color;

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
    @SerialEntry public TextDisplayShadowMode forceTextDisplayShadow = TextDisplayShadowMode.Enable;
    @SerialEntry public boolean forceRemoveTextDisplayBackground = true;

    @SerialEntry public boolean customHitboxMode = true;
    @SerialEntry public Color customHitboxColor = new Color(255, 255, 255, 255);
    @SerialEntry public boolean playerDefaultHitbox = false;
    @SerialEntry public boolean playerSneakingHitbox = false;
    @SerialEntry public boolean playerSwimmingHitbox = true;
    @SerialEntry public boolean playerElytraHitbox = true;
    @SerialEntry public boolean playerCrawlingHitbox = true;
    @SerialEntry public boolean playerSleepingHitbox = false;
    @SerialEntry public boolean boatHitbox = true;
    @SerialEntry public boolean arrowHitbox = true;
    @SerialEntry public boolean tridentHitbox = true;
    @SerialEntry public boolean fishingBobberHitbox = false;
    @SerialEntry public boolean enderPearlHitbox = false;
    @SerialEntry public boolean xpOrbHitbox = false;
    @SerialEntry public boolean endCrystalHitbox = false;
    @SerialEntry public boolean otherEntityHitbox = false;

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
                                        .name(Text.literal("Force Enable/Disable Text Display Shadow"))
                                        .description(OptionDescription.of(Text.literal("Force Text Shadow to always be enabled or disabled for Text Displays, if set to Default it keeps the value decided by the command that was used to spawn the Text Display")))
                                        .binding(defaults.forceTextDisplayShadow, () -> config.forceTextDisplayShadow, newVal -> config.forceTextDisplayShadow = newVal)
                                        .controller((opt) ->
                                                EnumControllerBuilder.create(opt)
                                                        .enumClass(TextDisplayShadowMode.class)
                                                        .formatValue((it) -> Text.literal(it.name())))
                                        .build())
                                .build())
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Hitboxes"))
                        .tooltip(Text.literal("Configure Hitbox Modifications"))
                        .option(Option.<Boolean>createBuilder()
                                .name(Text.literal("Only render selected Hitboxes"))
                                .description(OptionDescription.of(Text.literal("This makes it so only some hitboxes are rendered and they are rendered without additional lines like the direction entities are looking at. Requires hitboxes to be enabled with F3+B")))
                                .binding(defaults.customHitboxMode, () -> config.customHitboxMode, newVal -> config.customHitboxMode = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.<Color>createBuilder()
                                .name(Text.literal("Hitbox Color"))
                                .description(OptionDescription.of(Text.literal("This changes the color of Hitboxes. Requires Only render selected Hitboxes to be enabled")))
                                .binding(defaults.customHitboxColor, () -> config.customHitboxColor, newVal -> config.customHitboxColor = newVal)
                                .controller(opt -> ColorControllerBuilder.create(opt).allowAlpha(true))
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Player Hitboxes"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Default Hitbox"))
                                        .description(OptionDescription.of(Text.literal("Renders the normal Player Hitbox")))
                                        .binding(defaults.playerDefaultHitbox, () -> config.playerDefaultHitbox, newVal -> config.playerDefaultHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Sneaking Hitbox"))
                                        .description(OptionDescription.of(Text.literal("Renders the Player sneaking Hitbox")))
                                        .binding(defaults.playerSneakingHitbox, () -> config.playerSneakingHitbox, newVal -> config.playerSneakingHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Swimming Hitbox"))
                                        .description(OptionDescription.of(Text.literal("Renders the Player swimming Hitbox")))
                                        .binding(defaults.playerSwimmingHitbox, () -> config.playerSwimmingHitbox, newVal -> config.playerSwimmingHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Elytra Hitbox"))
                                        .description(OptionDescription.of(Text.literal("Renders the Player Hitbox when flying with an Elytra")))
                                        .binding(defaults.playerElytraHitbox, () -> config.playerElytraHitbox, newVal -> config.playerElytraHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Crawling Hitbox"))
                                        .description(OptionDescription.of(Text.literal("Renders the Player crawling Hitbox")))
                                        .binding(defaults.playerCrawlingHitbox, () -> config.playerCrawlingHitbox, newVal -> config.playerCrawlingHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Sleeping Hitbox"))
                                        .description(OptionDescription.of(Text.literal("Renders the Player sleeping Hitbox")))
                                        .binding(defaults.playerSleepingHitbox, () -> config.playerSleepingHitbox, newVal -> config.playerSleepingHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Entity Hitboxes"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Boat Hitbox"))
                                        .description(OptionDescription.of(Text.literal("Renders the Hitbox of Boats and Chest Boats")))
                                        .binding(defaults.boatHitbox, () -> config.boatHitbox, newVal -> config.boatHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Arrow Hitbox"))
                                        .description(OptionDescription.of(Text.literal("Renders the Hitbox of all types of Arrows")))
                                        .binding(defaults.arrowHitbox, () -> config.arrowHitbox, newVal -> config.arrowHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Trident Hitbox"))
                                        .description(OptionDescription.of(Text.literal("Renders the Hitbox of Tridents")))
                                        .binding(defaults.tridentHitbox, () -> config.tridentHitbox, newVal -> config.tridentHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Fishing Rod Bobber Hitbox"))
                                        .description(OptionDescription.of(Text.literal("Renders the Hitbox of the Fishing Rod Bobbers")))
                                        .binding(defaults.fishingBobberHitbox, () -> config.fishingBobberHitbox, newVal -> config.fishingBobberHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Ender Pearl Hitbox"))
                                        .description(OptionDescription.of(Text.literal("Renders the Hitbox of Ender Pearls")))
                                        .binding(defaults.enderPearlHitbox, () -> config.enderPearlHitbox, newVal -> config.enderPearlHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("XP Orb Hitbox"))
                                        .description(OptionDescription.of(Text.literal("Renders the Hitbox of XP Orbs")))
                                        .binding(defaults.xpOrbHitbox, () -> config.xpOrbHitbox, newVal -> config.xpOrbHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("End Crystal Hitbox"))
                                        .description(OptionDescription.of(Text.literal("Renders the Hitbox of End Crystals")))
                                        .binding(defaults.endCrystalHitbox, () -> config.endCrystalHitbox, newVal -> config.endCrystalHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("All other Entity Hitboxes"))
                                        .description(OptionDescription.of(Text.literal("Renders every other Entity Hitbox")))
                                        .binding(defaults.otherEntityHitbox, () -> config.otherEntityHitbox, newVal -> config.otherEntityHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .build())
                        .build())
        )).generateScreen(parent);
    }
}