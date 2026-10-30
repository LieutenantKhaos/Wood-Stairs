# Validation

## Minecraft 26.2 / Fabric

- Java 25 / Gradle 9.5.1 build passed.
- The shared local Gradle cache contained an empty merged Minecraft 26.2 JAR. Validation used the optional `-PclientOnlyCompile` flag to compile common code against the cached client game JAR. No client-specific game classes are referenced by the mod.
- JDK class-file verification passed for the original built-in-pack entrypoint.
- Block model JSON, blockstate references, and built-in pack archive paths validated.
- Default and Alternative appearances rendered in Blockbench.

## Remaining manual checks

Launch Minecraft with and without Biomes O' Plenty; test placement, corners, upside-down stairs, waterlogging, crafting, drops, both resource packs, and multiplayer synchronization. A successful compile does not confirm those in-game interactions.

## Minecraft 26.3 / Fabric

- Java 25 / Gradle 9.5.1 `clean build --offline` passed against the merged 26.3 game JAR, Fabric Loader 0.20.1, and Fabric API 0.165.0+26.3.
- Both built-in resource packs declare resource format 97.1, taken from the installed Minecraft 26.3 version metadata.
- Fabric dedicated-server `--initSettings` exited successfully with the mod and Fabric API present. This checks the loader/startup path only; it does not start a world or establish gameplay/resource-pack correctness.
- Registry identifiers and all 26 wood families retained.
