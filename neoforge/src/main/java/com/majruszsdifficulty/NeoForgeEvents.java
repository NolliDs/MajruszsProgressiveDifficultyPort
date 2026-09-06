package com.majruszsdifficulty;

import com.majruszsdifficulty.entity.CursedArmor;
import com.majruszsdifficulty.treasurebag.TreasureBagHelper;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class NeoForgeEvents {
	@SubscribeEvent
	public static void onPlayerLogin( PlayerEvent.PlayerLoggedInEvent event ) {
		if( event.getEntity() instanceof net.minecraft.server.level.ServerPlayer player ) {
			TreasureBagHelper.createDefaultProgress( player );
		}
	}

	// Registers the natural-spawn rules for the mod's custom monsters. In 1.20.1 this went through
	// Majrusz Library's Custom.SpawnPlacements helper, but that path is not wired up in the 1.21.1
	// library build, so NeoForge logged every one of these entities as missing a spawn placement and
	// spawned them without any location restrictions. Registering them here (mod event bus) restores
	// the intended behaviour. Mirrors MajruszsDifficulty's spawn placement setup.
	public static void onRegisterSpawnPlacements( RegisterSpawnPlacementsEvent event ) {
		event.register( MajruszsDifficulty.CERBERUS_ENTITY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
			Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND );
		event.register( MajruszsDifficulty.CREEPERLING_ENTITY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
			Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND );
		event.register( MajruszsDifficulty.CURSED_ARMOR_ENTITY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
			CursedArmor::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND );
		event.register( MajruszsDifficulty.GIANT_ENTITY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
			Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND );
		event.register( MajruszsDifficulty.ILLUSIONER_ENTITY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
			Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND );
		event.register( MajruszsDifficulty.TANK_ENTITY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
			Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND );
	}
}
