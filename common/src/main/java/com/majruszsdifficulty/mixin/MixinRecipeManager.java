package com.majruszsdifficulty.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.majruszsdifficulty.MajruszsDifficulty;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.RecipeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin( RecipeManager.class )
public class MixinRecipeManager {
	@Inject( at = @At( "HEAD" ), method = "apply" )
	private void injectModRecipes( Map< ResourceLocation, JsonElement > map, ResourceManager rm, ProfilerFiller profiler, CallbackInfo ci ) {
		// Tattered Boots: c c / c c
		addRecipe( map, "tattered_boots", "crafting_shaped",
			"\"category\":\"equipment\",\"pattern\":[\"c c\",\"c c\"],\"key\":{\"c\":{\"item\":\"majruszsdifficulty:cloth\"}},\"result\":{\"id\":\"majruszsdifficulty:tattered_boots\",\"count\":1}" );

		// Tattered Chestplate
		addRecipe( map, "tattered_chestplate", "crafting_shaped",
			"\"category\":\"equipment\",\"pattern\":[\"c c\",\"ccc\",\"ccc\"],\"key\":{\"c\":{\"item\":\"majruszsdifficulty:cloth\"}},\"result\":{\"id\":\"majruszsdifficulty:tattered_chestplate\",\"count\":1}" );

		// Tattered Helmet
		addRecipe( map, "tattered_helmet", "crafting_shaped",
			"\"category\":\"equipment\",\"pattern\":[\"ccc\",\"c c\"],\"key\":{\"c\":{\"item\":\"majruszsdifficulty:cloth\"}},\"result\":{\"id\":\"majruszsdifficulty:tattered_helmet\",\"count\":1}" );

		// Tattered Leggings
		addRecipe( map, "tattered_leggings", "crafting_shaped",
			"\"category\":\"equipment\",\"pattern\":[\"ccc\",\"c c\",\"c c\"],\"key\":{\"c\":{\"item\":\"majruszsdifficulty:cloth\"}},\"result\":{\"id\":\"majruszsdifficulty:tattered_leggings\",\"count\":1}" );

		// Undead Army Battle Standard
		addRecipe( map, "undead_army_battle_standard", "crafting_shaped",
			"\"category\":\"equipment\",\"pattern\":[\"ccc\",\" c \",\"sss\"],\"key\":{\"c\":{\"item\":\"majruszsdifficulty:cloth\"},\"s\":{\"item\":\"minecraft:stick\"}},\"result\":{\"id\":\"majruszsdifficulty:undead_battle_standard\",\"count\":1}" );

		// Bandage
		addRecipe( map, "bandage", "crafting_shaped",
			"\"category\":\"equipment\",\"pattern\":[\"ss \",\"sss\",\" ss\"],\"key\":{\"s\":{\"item\":\"minecraft:string\"}},\"result\":{\"id\":\"majruszsdifficulty:bandage\",\"count\":4}" );

		// Bandage Reversed
		addRecipe( map, "bandage_reversed", "crafting_shaped",
			"\"category\":\"equipment\",\"pattern\":[\" ss\",\"sss\",\"ss \"],\"key\":{\"s\":{\"item\":\"minecraft:string\"}},\"result\":{\"id\":\"majruszsdifficulty:bandage\",\"count\":4}" );

		// Golden Bandage
		addRecipe( map, "bandage_golden", "crafting_shaped",
			"\"category\":\"equipment\",\"pattern\":[\"ggg\",\"gbg\",\"ggg\"],\"key\":{\"g\":{\"item\":\"minecraft:gold_nugget\"},\"b\":{\"item\":\"majruszsdifficulty:bandage\"}},\"result\":{\"id\":\"majruszsdifficulty:golden_bandage\",\"count\":1}" );

		// String from Cloth (shapeless)
		addRecipe( map, "string_from_cloth", "crafting_shapeless",
			"\"category\":\"misc\",\"ingredients\":[{\"item\":\"majruszsdifficulty:cloth\"}],\"result\":{\"id\":\"minecraft:string\",\"count\":1}" );

		// Enderium Ingot Horizontal
		addRecipe( map, "enderium_ingot_horizontal", "crafting_shaped",
			"\"category\":\"misc\",\"pattern\":[\" cc\",\"cec\",\"cc \"],\"key\":{\"c\":{\"item\":\"majruszsdifficulty:enderium_shard\"},\"e\":{\"item\":\"minecraft:ender_eye\"}},\"result\":{\"id\":\"majruszsdifficulty:enderium_ingot\",\"count\":1}" );

		// Enderium Ingot Vertical
		addRecipe( map, "enderium_ingot_vertical", "crafting_shaped",
			"\"category\":\"misc\",\"pattern\":[\"cc \",\"cec\",\" cc\"],\"key\":{\"c\":{\"item\":\"majruszsdifficulty:enderium_shard\"},\"e\":{\"item\":\"minecraft:ender_eye\"}},\"result\":{\"id\":\"majruszsdifficulty:enderium_ingot\",\"count\":1}" );

		// Enderium Ingot from Block (shapeless)
		addRecipe( map, "enderium_ingot_from_enderium_block", "crafting_shapeless",
			"\"category\":\"misc\",\"ingredients\":[{\"item\":\"majruszsdifficulty:enderium_block\"}],\"result\":{\"id\":\"majruszsdifficulty:enderium_ingot\",\"count\":9}" );

		// Enderium Block
		addRecipe( map, "enderium_block", "crafting_shaped",
			"\"category\":\"building\",\"pattern\":[\"###\",\"###\",\"###\"],\"key\":{\"#\":{\"item\":\"majruszsdifficulty:enderium_ingot\"}},\"result\":{\"id\":\"majruszsdifficulty:enderium_block\",\"count\":1}" );

		// Enderium Upgrade Smithing Template
		addRecipe( map, "enderium_upgrade_smithing_template", "crafting_shaped",
			"\"category\":\"misc\",\"pattern\":[\"#S#\",\"#C#\",\"###\"],\"key\":{\"#\":{\"item\":\"minecraft:diamond\"},\"C\":{\"item\":\"minecraft:end_stone\"},\"S\":{\"item\":\"majruszsdifficulty:enderium_upgrade_smithing_template\"}},\"result\":{\"id\":\"majruszsdifficulty:enderium_upgrade_smithing_template\",\"count\":2}" );

		// ===== SMITHING RECIPES =====
		addSmithing( map, "enderium_sword_smithing", "minecraft:netherite_sword", "majruszsdifficulty:enderium_sword" );
		addSmithing( map, "enderium_axe_smithing", "minecraft:netherite_axe", "majruszsdifficulty:enderium_axe" );
		addSmithing( map, "enderium_pickaxe_smithing", "minecraft:netherite_pickaxe", "majruszsdifficulty:enderium_pickaxe" );
		addSmithing( map, "enderium_shovel_smithing", "minecraft:netherite_shovel", "majruszsdifficulty:enderium_shovel" );
		addSmithing( map, "enderium_hoe_smithing", "minecraft:netherite_hoe", "majruszsdifficulty:enderium_hoe" );
		addSmithing( map, "enderium_helmet_smithing", "minecraft:netherite_helmet", "majruszsdifficulty:enderium_helmet" );
		addSmithing( map, "enderium_chestplate_smithing", "minecraft:netherite_chestplate", "majruszsdifficulty:enderium_chestplate" );
		addSmithing( map, "enderium_leggings_smithing", "minecraft:netherite_leggings", "majruszsdifficulty:enderium_leggings" );
		addSmithing( map, "enderium_boots_smithing", "minecraft:netherite_boots", "majruszsdifficulty:enderium_boots" );
	}

	private void addSmithing( Map< ResourceLocation, JsonElement > map, String name, String base, String result ) {
		addRecipe( map, name, "smithing_transform",
			"\"template\":{\"item\":\"majruszsdifficulty:enderium_upgrade_smithing_template\"},\"base\":{\"item\":\"" + base + "\"},\"addition\":{\"item\":\"majruszsdifficulty:enderium_ingot\"},\"result\":{\"id\":\"" + result + "\"}" );
	}

	private void addRecipe( Map< ResourceLocation, JsonElement > map, String name, String type, String data ) {
		ResourceLocation id = MajruszsDifficulty.HELPER.getLocation( name );
		if( !map.containsKey( id ) ) {
			String json = "{\"type\":\"minecraft:" + type + "\"," + data + "}";
			map.put( id, JsonParser.parseString( json ).getAsJsonObject() );
		}
	}
}
