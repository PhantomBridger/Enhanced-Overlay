package io.github.phantombridger.enhancedoverlay.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.platform.YACLPlatform;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ConfigScreen {
    public static final ConfigClassHandler<ConfigScreen> CONFIG = ConfigClassHandler.createBuilder(ConfigScreen.class)
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(YACLPlatform.getConfigDir().resolve("enhancedoverlay.json"))
                    .build())
            .build();

    @SerialEntry public boolean hitboxModifications = true;
    @SerialEntry public Color hitboxColor = new Color(255, 255, 255, 255);

    @SerialEntry public boolean playerDefaultHitbox = false;
    @SerialEntry public boolean playerSneakingHitbox = false;
    @SerialEntry public boolean playerElytraHitbox = true;
    @SerialEntry public boolean playerSwimmingHitbox = true;
    @SerialEntry public boolean playerCrawlingHitbox = true;

    @SerialEntry public boolean enderDragonMainHitbox = false;
    @SerialEntry public boolean enderDragonHitboxes = true;
    @SerialEntry public Color enderDragonHitboxesColor = new Color(255, 255, 255, 255);

    @SerialEntry public boolean boatHitbox = false;
    @SerialEntry public boolean arrowHitbox = true;
    @SerialEntry public boolean tridentHitbox = true;
    @SerialEntry public boolean fishingBobberHitbox = false;
    @SerialEntry public boolean enderPearlHitbox = true;
    @SerialEntry public boolean windChargeHitbox = true;
    @SerialEntry public boolean xpOrbHitbox = false;
    @SerialEntry public boolean endCrystalHitbox = false;
    @SerialEntry public boolean otherEntityHitbox = false;

    @SerialEntry public TextShadowMode scoreboardTextShadow = TextShadowMode.ENABLED;
    @SerialEntry public BackgroundColorMode scoreboardBackground = BackgroundColorMode.NONE;
    @SerialEntry public Color scoreboardBackgroundColor = new Color(0, 0, 0, 42);
    @SerialEntry public BackgroundColorMode chatInputBackground = BackgroundColorMode.CUSTOM;
    @SerialEntry public Color chatInputBackgroundColor = new Color(0, 0, 0, 42);
    @SerialEntry public boolean hideChat = false;
    @SerialEntry public BackgroundColorMode subtitleBackground = BackgroundColorMode.NONE;
    @SerialEntry public Color subtitleBackgroundColor = new Color(0, 0, 0, 42);
    @SerialEntry public BackgroundColorMode tablistBackground = BackgroundColorMode.NONE;
    @SerialEntry public Color tablistBackgroundColor = new Color(0, 0, 0, 127);
    @SerialEntry public BackgroundColorMode tablistUsernameBackground = BackgroundColorMode.DEFAULT;
    @SerialEntry public Color tablistUsernameBackgroundColor = new Color(0, 0, 0, 33);
    @SerialEntry public TextShadowMode debugScreenTextShadow = TextShadowMode.ENABLED;
    @SerialEntry public BackgroundColorMode debugScreenBackground = BackgroundColorMode.NONE;
    @SerialEntry public Color debugScreenBackgroundColor = new Color(0, 0, 0, 42);

    @SerialEntry public boolean hideFirstPersonEffectParticles = true;
    @SerialEntry public TextShadowMode nametagTextShadow = TextShadowMode.ENABLED;
    @SerialEntry public boolean removeNametagBackground = true;
    @SerialEntry public TextShadowMode textDisplayTextShadow = TextShadowMode.ENABLED;
    @SerialEntry public boolean removeTextDisplayBackground = true;

    @SerialEntry public boolean fakeEnchantmentGlint = false;
    @SerialEntry public List<Item> fakeEnchantmentGlintItems = new ArrayList<>(List.of(Items.DIAMOND_SWORD, Items.DIAMOND_AXE, Items.DIAMOND_PICKAXE, Items.DIAMOND_SPEAR, Items.DIAMOND_HELMET, Items.DIAMOND_CHESTPLATE, Items.DIAMOND_LEGGINGS, Items.DIAMOND_BOOTS, Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SPEAR, Items.NETHERITE_HELMET, Items.NETHERITE_CHESTPLATE, Items.NETHERITE_LEGGINGS, Items.NETHERITE_BOOTS));


    public static Screen configScreen(Screen parent) {
        CONFIG.load();
        return YetAnotherConfigLib.create(CONFIG, ((defaults, config, builder) -> builder
                .title(Component.literal("Enhanced Overlay"))
                .category(ConfigCategory.createBuilder()
                        .name(Component.literal("Hitboxes"))
                        .option(Option.<Boolean>createBuilder()
                                .name(Component.literal("Hitbox Modifications"))
                                .description(OptionDescription.of(Component.literal("This has to be enabled for the other hitbox options to work and it can be toggled with a keybind")))
                                .binding(defaults.hitboxModifications, () -> config.hitboxModifications, newVal -> config.hitboxModifications = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.<Color>createBuilder()
                                .name(Component.literal("Hitbox Color"))
                                .binding(defaults.hitboxColor, () -> config.hitboxColor, newVal -> config.hitboxColor = newVal)
                                .controller(opt -> ColorControllerBuilder.create(opt).allowAlpha(true))
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Component.literal("Player Hitboxes"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Player Default Hitbox"))
                                        .description(OptionDescription.of(Component.literal("This includes the normal player hitbox and should also include modded player hitboxes")))
                                        .binding(defaults.playerDefaultHitbox, () -> config.playerDefaultHitbox, newVal -> config.playerDefaultHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Player Sneaking Hitbox"))
                                        .binding(defaults.playerSneakingHitbox, () -> config.playerSneakingHitbox, newVal -> config.playerSneakingHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Player Elytra Hitbox"))
                                        .binding(defaults.playerElytraHitbox, () -> config.playerElytraHitbox, newVal -> config.playerElytraHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Player Swimming Hitbox"))
                                        .binding(defaults.playerSwimmingHitbox, () -> config.playerSwimmingHitbox, newVal -> config.playerSwimmingHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Player Crawling Hitbox"))
                                        .binding(defaults.playerCrawlingHitbox, () -> config.playerCrawlingHitbox, newVal -> config.playerCrawlingHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Component.literal("Ender Dragon Hitboxes"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Main Ender Dragon Hitbox"))
                                        .binding(defaults.enderDragonMainHitbox, () -> config.enderDragonMainHitbox, newVal -> config.enderDragonMainHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Ender Dragon Hitboxes"))
                                        .description(OptionDescription.of(Component.literal("These are the Ender Dragon Hitboxes that show where it can be damaged and they have a custom color option")))
                                        .binding(defaults.enderDragonHitboxes, () -> config.enderDragonHitboxes, newVal -> config.enderDragonHitboxes = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Color>createBuilder()
                                        .name(Component.literal("Ender Dragon Hitboxes Color"))
                                        .description(OptionDescription.of(Component.literal("This color only applies to the Ender Dragon Hitboxes option and not the Main Hitbox")))
                                        .binding(defaults.enderDragonHitboxesColor, () -> config.enderDragonHitboxesColor, newVal -> config.enderDragonHitboxesColor = newVal)
                                        .controller(opt -> ColorControllerBuilder.create(opt).allowAlpha(true))
                                        .build())
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Component.literal("Entity Hitboxes"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Boat Hitbox"))
                                        .binding(defaults.boatHitbox, () -> config.boatHitbox, newVal -> config.boatHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Arrow Hitbox"))
                                        .binding(defaults.arrowHitbox, () -> config.arrowHitbox, newVal -> config.arrowHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Trident Hitbox"))
                                        .binding(defaults.tridentHitbox, () -> config.tridentHitbox, newVal -> config.tridentHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Fishing Bobber Hitbox"))
                                        .binding(defaults.fishingBobberHitbox, () -> config.fishingBobberHitbox, newVal -> config.fishingBobberHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Ender Pearl Hitbox"))
                                        .binding(defaults.enderPearlHitbox, () -> config.enderPearlHitbox, newVal -> config.enderPearlHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Wind Charge Hitbox"))
                                        .binding(defaults.windChargeHitbox, () -> config.windChargeHitbox, newVal -> config.windChargeHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("XP Orb Hitbox"))
                                        .binding(defaults.xpOrbHitbox, () -> config.xpOrbHitbox, newVal -> config.xpOrbHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("End Crystal Hitbox"))
                                        .binding(defaults.endCrystalHitbox, () -> config.endCrystalHitbox, newVal -> config.endCrystalHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Other Entity Hitboxes"))
                                        .binding(defaults.otherEntityHitbox, () -> config.otherEntityHitbox, newVal -> config.otherEntityHitbox = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .build())
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Component.literal("HUD"))
                        .group(OptionGroup.createBuilder()
                                .name(Component.literal("Scoreboard"))
                                .option(Option.<TextShadowMode>createBuilder()
                                        .name(Component.literal("Scoreboard Text Shadow"))
                                        .binding(defaults.scoreboardTextShadow, () -> config.scoreboardTextShadow, newVal -> config.scoreboardTextShadow = newVal)
                                        .controller((opt) ->
                                                EnumControllerBuilder.create(opt)
                                                        .enumClass(TextShadowMode.class)
                                                        .formatValue(it -> Component.literal(it.getDisplayName())))
                                        .build())
                                .option(Option.<BackgroundColorMode>createBuilder()
                                        .name(Component.literal("Scoreboard Background"))
                                        .binding(defaults.scoreboardBackground, () -> config.scoreboardBackground, newVal -> config.scoreboardBackground = newVal)
                                        .controller((opt) ->
                                                EnumControllerBuilder.create(opt)
                                                        .enumClass(BackgroundColorMode.class)
                                                        .formatValue(it -> Component.literal(it.getDisplayName())))
                                        .build())
                                .option(Option.<Color>createBuilder()
                                        .name(Component.literal("Scoreboard Background Color"))
                                        .binding(defaults.scoreboardBackgroundColor, () -> config.scoreboardBackgroundColor, newVal -> config.scoreboardBackgroundColor = newVal)
                                        .controller(opt -> ColorControllerBuilder.create(opt).allowAlpha(true))
                                        .build())
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Component.literal("Chat"))
                                .option(Option.<BackgroundColorMode>createBuilder()
                                        .name(Component.literal("Chat Input Background"))
                                        .binding(defaults.chatInputBackground, () -> config.chatInputBackground, newVal -> config.chatInputBackground = newVal)
                                        .controller((opt) ->
                                                EnumControllerBuilder.create(opt)
                                                        .enumClass(BackgroundColorMode.class)
                                                        .formatValue(it -> Component.literal(it.getDisplayName())))
                                        .build())
                                .option(Option.<Color>createBuilder()
                                        .name(Component.literal("Chat Input Background Color"))
                                        .binding(defaults.chatInputBackgroundColor, () -> config.chatInputBackgroundColor, newVal -> config.chatInputBackgroundColor = newVal)
                                        .controller(opt -> ColorControllerBuilder.create(opt).allowAlpha(true))
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Hide Chat"))
                                        .description(OptionDescription.of(Component.literal("Hides the chat when enabled and can be toggled with a keybind")))
                                        .binding(defaults.hideChat, () -> config.hideChat, newVal -> config.hideChat = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Component.literal("Subtitles"))
                                .option(Option.<BackgroundColorMode>createBuilder()
                                        .name(Component.literal("Subtitles Background"))
                                        .binding(defaults.subtitleBackground, () -> config.subtitleBackground, newVal -> config.subtitleBackground = newVal)
                                        .controller((opt) ->
                                                EnumControllerBuilder.create(opt)
                                                        .enumClass(BackgroundColorMode.class)
                                                        .formatValue(it -> Component.literal(it.getDisplayName())))
                                        .build())
                                .option(Option.<Color>createBuilder()
                                        .name(Component.literal("Subtitles Background Color"))
                                        .binding(defaults.subtitleBackgroundColor, () -> config.subtitleBackgroundColor, newVal -> config.subtitleBackgroundColor = newVal)
                                        .controller(opt -> ColorControllerBuilder.create(opt).allowAlpha(true))
                                        .build())
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Component.literal("Tablist"))
                                .option(Option.<BackgroundColorMode>createBuilder()
                                        .name(Component.literal("Tablist Background"))
                                        .binding(defaults.tablistBackground, () -> config.tablistBackground, newVal -> config.tablistBackground = newVal)
                                        .controller((opt) ->
                                                EnumControllerBuilder.create(opt)
                                                        .enumClass(BackgroundColorMode.class)
                                                        .formatValue(it -> Component.literal(it.getDisplayName())))
                                        .build())
                                .option(Option.<Color>createBuilder()
                                        .name(Component.literal("Tablist Background Color"))
                                        .binding(defaults.tablistBackgroundColor, () -> config.tablistBackgroundColor, newVal -> config.tablistBackgroundColor = newVal)
                                        .controller(opt -> ColorControllerBuilder.create(opt).allowAlpha(true))
                                        .build())
                                .option(Option.<BackgroundColorMode>createBuilder()
                                        .name(Component.literal("Tablist Username Background"))
                                        .binding(defaults.tablistUsernameBackground, () -> config.tablistUsernameBackground, newVal -> config.tablistUsernameBackground = newVal)
                                        .controller((opt) ->
                                                EnumControllerBuilder.create(opt)
                                                        .enumClass(BackgroundColorMode.class)
                                                        .formatValue(it -> Component.literal(it.getDisplayName())))
                                        .build())
                                .option(Option.<Color>createBuilder()
                                        .name(Component.literal("Tablist Username Background Color"))
                                        .binding(defaults.tablistUsernameBackgroundColor, () -> config.tablistUsernameBackgroundColor, newVal -> config.tablistUsernameBackgroundColor = newVal)
                                        .controller(opt -> ColorControllerBuilder.create(opt).allowAlpha(true))
                                        .build())
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Component.literal("Debug Screen"))
                                .option(Option.<TextShadowMode>createBuilder()
                                        .name(Component.literal("Debug Screen Text Shadow"))
                                        .binding(defaults.debugScreenTextShadow, () -> config.debugScreenTextShadow, newVal -> config.debugScreenTextShadow = newVal)
                                        .controller((opt) ->
                                                EnumControllerBuilder.create(opt)
                                                        .enumClass(TextShadowMode.class)
                                                        .formatValue(it -> Component.literal(it.getDisplayName())))
                                        .build())
                                .option(Option.<BackgroundColorMode>createBuilder()
                                        .name(Component.literal("Debug Screen Background"))
                                        .binding(defaults.debugScreenBackground, () -> config.debugScreenBackground, newVal -> config.debugScreenBackground = newVal)
                                        .controller((opt) ->
                                                EnumControllerBuilder.create(opt)
                                                        .enumClass(BackgroundColorMode.class)
                                                        .formatValue(it -> Component.literal(it.getDisplayName())))
                                        .build())
                                .option(Option.<Color>createBuilder()
                                        .name(Component.literal("Debug Screen Background Color"))
                                        .binding(defaults.debugScreenBackgroundColor, () -> config.debugScreenBackgroundColor, newVal -> config.debugScreenBackgroundColor = newVal)
                                        .controller(opt -> ColorControllerBuilder.create(opt).allowAlpha(true))
                                        .build())
                                .build())
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Component.literal("Entities"))
                        .option(Option.<Boolean>createBuilder()
                                .name(Component.literal("Hide First Person Effect Particles"))
                                .description(OptionDescription.of(Component.literal("This hides your own potion effect particles while youre playing in first person.")))
                                .binding(defaults.hideFirstPersonEffectParticles, () -> config.hideFirstPersonEffectParticles, newVal -> config.hideFirstPersonEffectParticles = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Component.literal("Nametags"))
                                .option(Option.<TextShadowMode>createBuilder()
                                        .name(Component.literal("Nametag Text Shadow"))
                                        .binding(defaults.nametagTextShadow, () -> config.nametagTextShadow, newVal -> config.nametagTextShadow = newVal)
                                        .controller((opt) ->
                                                EnumControllerBuilder.create(opt)
                                                        .enumClass(TextShadowMode.class)
                                                        .formatValue(it -> Component.literal(it.getDisplayName())))
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Remove Nametag Background"))
                                        .description(OptionDescription.of(Component.literal("This can make the nametags look better but since removing the background makes it more difficult to see them through blocks compared to vanilla you can toggle it with a keybind so you can have the background disabled for sword duels for example and enabled if you want the background to be like vanilla which can be useful on SMPs")))
                                        .binding(defaults.removeNametagBackground, () -> config.removeNametagBackground, newVal -> config.removeNametagBackground = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Component.literal("Text Displays"))
                                .option(Option.<TextShadowMode>createBuilder()
                                        .name(Component.literal("Text Display Text Shadow"))
                                        .binding(defaults.textDisplayTextShadow, () -> config.textDisplayTextShadow, newVal -> config.textDisplayTextShadow = newVal)
                                        .controller((opt) ->
                                                EnumControllerBuilder.create(opt)
                                                        .enumClass(TextShadowMode.class)
                                                        .formatValue(it -> Component.literal(it.getDisplayName())))
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Remove Text Display Background"))
                                        .description(OptionDescription.of(Component.literal("This can make the text displays look better but since removing the background makes it more difficult to see them through blocks compared to vanilla you can toggle it with a keybind so you can have the background disabled for sword duels for example and enabled if you want the background to be like vanilla which can be useful on servers that replace nametags with text displays")))
                                        .binding(defaults.removeTextDisplayBackground, () -> config.removeTextDisplayBackground, newVal -> config.removeTextDisplayBackground = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .build())
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Component.literal("Items"))
                        .option(Option.<Boolean>createBuilder()
                                .name(Component.literal("Fake Enchantment Glint"))
                                .description(OptionDescription.of(Component.literal("This makes it so all items from the list below this option will be rendered with enchantment glint no matter if they are enchanted or not")))
                                .binding(defaults.fakeEnchantmentGlint, () -> config.fakeEnchantmentGlint, newVal -> config.fakeEnchantmentGlint = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .group(ListOption.<Item>createBuilder()
                                .name(Component.literal("Fake Enchantment Glint Items"))
                                .binding(defaults.fakeEnchantmentGlintItems, () -> config.fakeEnchantmentGlintItems, val -> config.fakeEnchantmentGlintItems = val)
                                .controller(ItemControllerBuilder::create)
                                .initial(Items.DIAMOND_SWORD)
                                .insertEntriesAtEnd(false)
                                .build())
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Component.literal("Reset Config Options"))
                        .option(ButtonOption.createBuilder()
                                .name(Component.literal("Reset Config"))
                                .description(OptionDescription.of(Component.literal("Resets the config to default except for the force enchant glint items list, you have to click the reset icon next to the list to reset that")))
                                .action((screen, option) -> ConfigUtils.reset(config, screen))
                                .build())
                        .option(ButtonOption.createBuilder()
                                .name(Component.literal("Set Config to Vanilla"))
                                .description(OptionDescription.of(Component.literal("Sets the config to vanilla values except for the force enchant glint items list, you have to click the remove buttons for all the items but since the items move up when you delete the first item you can just spam click")))
                                .action((screen, option) -> ConfigUtils.setToVanilla(config, screen))
                                .build())
                        .build())
        )).generateScreen(parent);
    }
}
