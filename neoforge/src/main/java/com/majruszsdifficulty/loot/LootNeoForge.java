package com.majruszsdifficulty.loot;

import com.majruszsdifficulty.mixin.neoforge.IMixinLootTable;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class LootNeoForge implements ILootPlatform {
	private static final Logger LOGGER = LoggerFactory.getLogger( "majruszsdifficulty" );

	@Override
	public Stream< LootPool > getLootPools( LootTable lootTable ) {
		return ( ( IMixinLootTable )lootTable ).getPools().stream();
	}

	@Override
	public LootTable getLootTable( ResourceLocation id, ServerLevel level ) {
		var key = ResourceKey.create( Registries.LOOT_TABLE, id );

		// Method 1: reloadableRegistries
		LootTable table = level.getServer().reloadableRegistries().getLootTable( key );
		LOGGER.info( "LootNeoForge[1] reloadableRegistries '{}' -> {}", id, table != LootTable.EMPTY ? "FOUND" : "EMPTY" );
		if( table != LootTable.EMPTY ) return table;

		// Method 2: registryAccess
		table = level.registryAccess().registryOrThrow( Registries.LOOT_TABLE ).get( key );
		LOGGER.info( "LootNeoForge[2] registryAccess '{}' -> {}", id, table != null ? ( table != LootTable.EMPTY ? "FOUND" : "EMPTY" ) : "null" );
		if( table != null && table != LootTable.EMPTY ) return table;

		LOGGER.warn( "LootNeoForge: ALL methods failed for '{}'", id );
		return LootTable.EMPTY;
	}
}
