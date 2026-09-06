package com.majruszsdifficulty.items;

import com.majruszsdifficulty.MajruszsDifficulty;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.Optional;
import java.util.function.Supplier;

public enum CustomItemTier implements Tier {
	WITHER( 360, 3, 15, 5.0f, 3.0f, ()->Ingredient.of( MajruszsDifficulty.CERBERUS_FANG_ITEM.get() ) ),
	ENDERIUM( 2137, 4, 15, 10.0f, 5.0f, ()->Ingredient.of( MajruszsDifficulty.ENDERIUM_INGOT_ITEM.get() ) );

	final int maxUses, harvestLevel, enchantability;
	final float efficiency, attackDamage;
	final Supplier< Ingredient > repairMaterial;

	CustomItemTier( int uses, int harvestLevel, int enchantability, float efficiency, float damage, Supplier< Ingredient > material ) {
		this.maxUses = uses;
		this.harvestLevel = harvestLevel;
		this.enchantability = enchantability;
		this.efficiency = efficiency;
		this.attackDamage = damage;
		this.repairMaterial = material;
	}

	public int getUses() {
		return this.maxUses;
	}

	public float getSpeed() {
		return this.efficiency;
	}

	public float getAttackDamageBonus() {
		return this.attackDamage;
	}

	public int getLevel() {
		return this.harvestLevel;
	}

	public int getEnchantmentValue() {
		return this.enchantability;
	}

	public Ingredient getRepairIngredient() {
		return this.repairMaterial.get();
	}

	public TagKey< Block > getIncorrectBlocksForDrops() {
		return switch( this.harvestLevel ) {
			case 1 -> BlockTags.INCORRECT_FOR_STONE_TOOL;
			case 2 -> BlockTags.INCORRECT_FOR_IRON_TOOL;
			case 3 -> BlockTags.INCORRECT_FOR_DIAMOND_TOOL;
			case 4 -> BlockTags.INCORRECT_FOR_NETHERITE_TOOL;
			default -> BlockTags.INCORRECT_FOR_WOODEN_TOOL;
		};
	}
}
