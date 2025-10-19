# Enhanced Overlay

**Features:**

Forces text shadow for:
- Scoreboard
- Nametags
- Text Display

This is not required for tablist and subtitles because those are already rendered with text shadow in vanilla

Removes background for:
- Scoreboard
- Tablist (keeps background behind usernames but you can remove that by setting background opacity to 0 for everything in the Minecraft Accessibility Settings)
- Subtitles
- Nametags
- Text Displays

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
- Feather Client: partially compatible
    - ✅ Text Display changes work
    - ✅ Subtitle changes work with feathers subtitles mod
    - ❌ Nametag changes break with feather (background is enabled even when feathers nametag mod is disabled)
    - ✅/❌ Tablist changes work when feathers tablist mod is disabled
    - ❌ Scoreboard changes break because disabling feathers scoreboard mod removes the scoreboard entirely