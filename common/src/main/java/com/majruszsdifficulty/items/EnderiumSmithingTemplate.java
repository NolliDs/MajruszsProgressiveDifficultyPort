package com.majruszsdifficulty.items;

import com.majruszlibrary.events.OnLootGenerated;
import com.majruszlibrary.item.LootHelper;
import com.majruszlibrary.text.TextHelper;
import com.majruszsdifficulty.MajruszsDifficulty;
import net.minecraft.ChatFormatting;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

import java.util.List;

public class EnderiumSmithingTemplate extends SmithingTemplateItem {
	private static final ResourceLocation ID = MajruszsDifficulty.HELPER.getLocation( "gameplay/enderium_upgrade_smithing_template" );

	static {
		OnLootGenerated.listen( EnderiumSmithingTemplate::addToChest )
			.addCondition( data->data.lootId.equals( BuiltInLootTables.END_CITY_TREASURE.location() ) );
	}

	public EnderiumSmithingTemplate() {
		super(
			TextHelper.translatable( "majruszsdifficulty.smithing.enderium.applies_to" ).withStyle( ChatFormatting.BLUE ),
			TextHelper.translatable( "majruszsdifficulty.smithing.enderium.ingredients" ).withStyle( ChatFormatting.BLUE ),
			TextHelper.translatable( "majruszsdifficulty.smithing.enderium.upgrade" ).withStyle( ChatFormatting.GRAY ),
			TextHelper.translatable( "majruszsdifficulty.smithing.enderium.base_slot" ).withStyle( ChatFormatting.GRAY ),
			TextHelper.translatable( "majruszsdifficulty.smithing.enderium.additions_slot" ).withStyle( ChatFormatting.GRAY ),
			List.of(
				ResourceLocation.parse( "item/empty_armor_slot_helmet" ),
				ResourceLocation.parse( "item/empty_slot_sword" ),
				ResourceLocation.parse( "item/empty_armor_slot_chestplate" ),
				ResourceLocation.parse( "item/empty_slot_pickaxe" ),
				ResourceLocation.parse( "item/empty_armor_slot_leggings" ),
				ResourceLocation.parse( "item/empty_slot_axe" ),
				ResourceLocation.parse( "item/empty_armor_slot_boots" ),
				ResourceLocation.parse( "item/empty_slot_hoe" ),
				ResourceLocation.parse( "item/empty_slot_shovel" )
			),
			List.of(
				ResourceLocation.parse( "item/empty_slot_ingot" )
			)
		);
	}

	private static void addToChest( OnLootGenerated data ) {
		LootHelper.getLootTable( ID )
			.getRandomItems( LootHelper.toGiftParams( data.entity ) ) // tochestparams
			.forEach( data.generatedLoot::add );
	}
}
