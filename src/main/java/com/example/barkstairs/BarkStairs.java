package com.example.barkstairs;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents.ModifyOutput;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public final class BarkStairs implements ModInitializer {
   public static final String MOD_ID = "bark_stairs";
   public static final Block OAK_WOOD_STAIRS = register("oak_wood_stairs", Blocks.OAK_PLANKS);
   public static final Block SPRUCE_WOOD_STAIRS = register("spruce_wood_stairs", Blocks.SPRUCE_PLANKS);
   public static final Block BIRCH_WOOD_STAIRS = register("birch_wood_stairs", Blocks.BIRCH_PLANKS);
   public static final Block JUNGLE_WOOD_STAIRS = register("jungle_wood_stairs", Blocks.JUNGLE_PLANKS);
   public static final Block ACACIA_WOOD_STAIRS = register("acacia_wood_stairs", Blocks.ACACIA_PLANKS);
   public static final Block DARK_OAK_WOOD_STAIRS = register("dark_oak_wood_stairs", Blocks.DARK_OAK_PLANKS);
   public static final Block MANGROVE_WOOD_STAIRS = register("mangrove_wood_stairs", Blocks.MANGROVE_PLANKS);
   public static final Block CHERRY_WOOD_STAIRS = register("cherry_wood_stairs", Blocks.CHERRY_PLANKS);
   public static final Block PALE_OAK_WOOD_STAIRS = register("pale_oak_wood_stairs", Blocks.PALE_OAK_PLANKS);
   public static final Block BAMBOO_BLOCK_STAIRS = register("bamboo_block_stairs", Blocks.BAMBOO_PLANKS);
   public static final Block CRIMSON_HYPHAE_STAIRS = register("crimson_hyphae_stairs", Blocks.CRIMSON_PLANKS);
   public static final Block WARPED_HYPHAE_STAIRS = register("warped_hyphae_stairs", Blocks.WARPED_PLANKS);
   private static final List<Block> BOP_WOOD_STAIRS = new ArrayList<>();

   private static Block register(String name, Block vanillaPlanks) {
      Identifier id = Identifier.fromNamespaceAndPath("bark_stairs", name);
      BlockItemId blockItemId = BlockItemId.create(id, id);
      Function<Properties, Block> factory = properties -> new StairBlock(vanillaPlanks.defaultBlockState(), properties);
      Block block = factory.apply(Properties.ofFullCopy(vanillaPlanks).setId(blockItemId.block()));
      Registry.register(BuiltInRegistries.BLOCK, blockItemId.block(), block);
      BlockItem item = new BlockItem(block, new net.minecraft.world.item.Item.Properties().useBlockDescriptionPrefix().setId(blockItemId.item()));
      Registry.register(BuiltInRegistries.ITEM, blockItemId.item(), item);
      return block;
   }

   private static void registerBopWoodStairs() {
      if (FabricLoader.getInstance().isModLoaded("biomesoplenty")) {
         String[] woods = new String[]{
            "origin_oak", "fir", "pine", "maple", "redwood", "mahogany", "jacaranda", "palm", "willow", "dead", "magic", "umbran", "hellbark", "empyreal"
         };

         for (String wood : woods) {
            BOP_WOOD_STAIRS.add(register(wood + "_wood_stairs", Blocks.OAK_PLANKS));
         }
      }
   }

   public void onInitialize() {
      registerAlternativePack();
      boolean programmerArtRegistered = ResourceLoader.registerBuiltinPack(
         Identifier.fromNamespaceAndPath("bark_stairs", "programmer_art"),
         (ModContainer)FabricLoader.getInstance().getModContainer("bark_stairs").orElseThrow(),
         Component.literal("Wood Stairs (Programmer Art)"),
         PackActivationType.NORMAL
      );
      if (!programmerArtRegistered) {
         throw new IllegalStateException("Failed to register built-in resource pack: resourcepacks/programmer_art");
      }

      registerBopWoodStairs();
      CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register((ModifyOutput)entries -> {
         entries.accept(OAK_WOOD_STAIRS);
         entries.accept(SPRUCE_WOOD_STAIRS);
         entries.accept(BIRCH_WOOD_STAIRS);
         entries.accept(JUNGLE_WOOD_STAIRS);
         entries.accept(ACACIA_WOOD_STAIRS);
         entries.accept(DARK_OAK_WOOD_STAIRS);
         entries.accept(MANGROVE_WOOD_STAIRS);
         entries.accept(CHERRY_WOOD_STAIRS);
         entries.accept(PALE_OAK_WOOD_STAIRS);
         entries.accept(BAMBOO_BLOCK_STAIRS);
         entries.accept(CRIMSON_HYPHAE_STAIRS);
         entries.accept(WARPED_HYPHAE_STAIRS);
         BOP_WOOD_STAIRS.forEach(block -> entries.accept(block.asItem()));
      });
   }

   private static void registerAlternativePack() {
      ResourceLoader.registerBuiltinPack(
         Identifier.fromNamespaceAndPath("bark_stairs", "alternative"),
         (ModContainer)FabricLoader.getInstance().getModContainer("bark_stairs").orElseThrow(),
         Component.literal("Wood Stairs (Alternative)"),
         PackActivationType.NORMAL
      );
   }
}
