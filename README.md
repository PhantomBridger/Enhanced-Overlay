**Features:**

Forces text shadow for:
- Scoreboard
- Nametags
- Text Display

This is not required for tablist and subtitles because those are already rendered with text shadow in vanilla

Removes background for:
- Scoreboard
- Tablist
- Subtitles
- Nametags
- Text Displays

Hitbox Customization:
- If enabled it only renders selected hitboxes
- There is an option to change the hitbox color
- Player hitboxs:
  - Default hitbox
  - Sneaking hitbox
  - Swimming hitbox
  - Elytra hitbox
  - Crawling hitbox
  - Sleeping hitbox
- Entity hitboxs:
  - Boat and Chest Boat
  - Arrows (including tipped and spectral arrows)
  - Trident
  - Fishing rod bobber
  - Ender pearl
  - XP Orb
  - End Crystal
  - Other entities (this can enable or disable all other hitboxes, I didn't add options for every entity because that would be way too many settings)

Uses [YetAnotherConfigLib](https://modrinth.com/mod/yacl) for the config.

---

**Recommended Minecraft settings to use with this mod:**

In Minecraft Accessibility Settings
- Background opacity: 0%
- Text Background:
  - Chat (if you just want chat to have no background as well)
  - Everywhere (if you also want to remove the background behind usernames in tablist)


---

**Compatibility:**

- Should be compatible with most fabric mods
- Mods that modify scoreboard, tablist, subtitles, nametags or text displays: not tested but shouldn't crash
- Lunar Client: partially compatible
  - ✅ Text Display changes work
  - ✅/❌ Scoreboard changes work when lunars scoreboard mod is disabled
  - ✅/❌ Tablist changes work when lunars tablist mod is disabled
  - ✅/❌ Subtitle changes work when lunars subtitles mod is disabled
  - ❌ Nametag changes break with lunar (don't get applied even when lunars nametag mod is disabled)
  - ✅ Hitbox customization works even with lunars hitbox mod enabled, it just wont apply the lunar hitbox settings
- Feather Client: partially compatible
  - ✅ Text Display changes work
  - ✅ Subtitle changes work with feathers subtitles mod
  - ❌ Nametag changes break with feather (background is enabled even when feathers nametag mod is disabled)
  - ✅/❌ Tablist changes work when feathers tablist mod is disabled
  - ❌ Scoreboard changes break because disabling feathers scoreboard mod removes the scoreboard entirely
  - ✅/❌ Hitbox customization works when feathers hitbox mod is disabled
