# Wood Stairs

Wood stairs for Minecraft Java 26.2 / Fabric, with 12 vanilla and 14 Biomes O Plenty wood types. Straight, inner, and outer corners are supported.

## Install

1. Install `mods/WoodStairs-Alternative-built-in.jar` in your Minecraft `mods` folder. Install only one Wood Stairs JAR at a time; the other JARs in this repository are earlier versions kept for reference.
2. Use Minecraft 26.2, Java 25+, Fabric Loader 0.19.3+, and Fabric API. Install Biomes O Plenty to use its wood families.
3. The default style uses plank tops and vertical risers, with bark on exterior sides.
4. For bark risers, open **Options > Resource Packs** and enable **Wood Stairs (Alternative)**. This pack is included in the JAR; no separate download is necessary.
5. The built-in **Wood Stairs (Programmer Art)** pack is also available. When using both, place **Alternative** above **Programmer Art**.

The standalone `resourcepacks/WoodStairs-Bark-Risers.zip` is retained as an optional separate copy for the older plank-step JAR. It is unnecessary with the newest JAR.

## Compare

### Default: plank risers

![Default plank risers](renders/WoodStairs-all-types-labeled.png)

### Alternative: bark risers

![Alternative bark risers](renders/WoodStairs-bark-risers-labeled.png)

## Files

- `mods/`: current installable JAR and earlier iterations.
- `WoodStairs-mod-resources.zip`: editable latest JAR contents, including both built-in packs. Extract into a `mod/` folder before using the packaging script. A `mod/` working folder is also kept locally.
- `resourcepacks/`: standalone bark-riser pack from the earlier iteration.
- `renders/`: labeled and unlabeled Blockbench-rendered PNGs. Blockbench project files are excluded.
- `original/WoodStairs.zip`: original supplied compiled mod archive.
- `tools/Package-Mod.ps1`: packages `mod/` into the current JAR using PowerShell/.NET.
- `tools/AddBuiltinPack.java`: reproducible bytecode patch utility for the original compiled entrypoint, using ASM 9.9.1 core and tree on the classpath. Run `javac -cp "asm-9.9.1.jar;asm-tree-9.9.1.jar" AddBuiltinPack.java`, then `java -cp ".;asm-9.9.1.jar;asm-tree-9.9.1.jar" AddBuiltinPack input.class output.class`. It refuses to patch an already patched input.

The supplied archive contained compiled Java, not a Java source/Gradle project. The archived `mod/` contents are an editable resource and compiled-class distribution, not a complete source build. Original block registration code is preserved; the added initialization call registers the optional Alternative pack.

## Validation

- JDK 25 class-file verification passed with Minecraft and Fabric dependencies.
- Resource pack metadata, model JSON, and archive paths were validated.
- Both visual styles were rendered in Blockbench using game textures.
- The new built-in pack registration has not yet been tested by launching Minecraft.

Minecraft and Biomes O Plenty textures shown in the renders belong to their respective authors. This repository does not bundle their raw texture files.

