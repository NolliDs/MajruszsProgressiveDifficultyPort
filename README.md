# Majrusz's Progressive Difficulty — Port (Unofficial)

An **unofficial NeoForge 1.21.1 port** of [Majrusz's Progressive Difficulty](https://modrinth.com/mod/majruszs-progressive-difficulty), a mod that steadily increases Minecraft's difficulty the longer your world survives.

> **Disclaimer:** This is an unofficial community port, created with the help of AI. It is **not** made or endorsed by the original author. The original mod, concept, and art are by **Majrusz17**. Please support the official releases.

## Features

- **Game Stages** — the world advances through *Normal → Expert → Master*, and mobs, events and threats scale with each stage. Global or per-player, and highly configurable.
- **New mobs** — Cerberus, Tank, Cursed Armor, Creeperling, Illusioner, Giant.
- **The Undead Army** — a multi-wave siege; survive it for a Treasure Bag (empowered on Expert/Master).
- **Blood Moon** — a periodic hostile blood-red night.
- **Bleeding** — lethal damage-over-time from sharp weapons, zombies and spiders; cured with a Bandage.
- **Treasure Bags** — themed reward bags from Angler, Raid, Elder Guardian, Wither, Ender Dragon, Warden and the Undead Army.
- **New gear & items** — the Enderium tier (Shard → Ingot → tools & armor), Soul Jar, Wither Sword, Tattered armor, Recall Potion, Ender Pouch, scrolls, and more.

## Requirements

- Minecraft **1.21.1**
- **NeoForge 21.1.x** (tested on 21.1.177)
- **Java 21**
- **Majrusz Library** (1.21.1 NeoForge build) — required at runtime.

## Installation (players)

Place **both** jars into your `mods` folder:

- `majruszs-difficulty-neoforge-1.21.1-1.9.10.jar`
- `majrusz-library-neoforge-1.21.1-7.0.8.jar`

## Building from source

```bash
./gradlew :neoforge:build
```

The output jar is written to `neoforge/build/libs/`. Requires JDK 21. The Majrusz Library jars used for compilation are provided under `common/libs/` and `neoforge/libs/` (there is no public Maven release of the library for 1.21.1 NeoForge).

Only the `common` and `neoforge` modules are ported to 1.21.1; the `fabric` and `forge` modules remain from the original multi-loader project and are excluded from the build.

## What was changed for 1.21.1

- Fixed a dedicated-server crash: entity classes called the client-only `loadClient()` (stripped on servers by the RuntimeDistCleaner) → switched to `load()`.
- Registered spawn placements for the custom mobs via `RegisterSpawnPlacementsEvent`.
- Fixed the Undead Army / creeperling / zombie-leader loot tables (`"value"` → `"name"` for item entries, per the 1.21 format).
- Fixed the Gradle build so `./gradlew build` passes cleanly.

## Credits & License

- Original mod, concept and art: **Majrusz17** — <https://modrinth.com/mod/majruszs-progressive-difficulty>
- Released under the original project's **MIT License** (see [LICENSE](LICENSE)).
