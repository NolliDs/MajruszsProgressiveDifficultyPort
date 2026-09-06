package com.majruszsdifficulty.loot;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.stream.Stream;

public interface ILootPlatform {
	Stream< LootPool > getLootPools( LootTable lootTable );

	default LootTable getLootTable( ResourceLocation id, ServerLevel level ) {
		return LootTable.EMPTY;
	}
}
