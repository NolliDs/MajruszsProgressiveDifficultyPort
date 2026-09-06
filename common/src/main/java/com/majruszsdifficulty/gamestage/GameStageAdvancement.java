package com.majruszsdifficulty.gamestage;

import net.minecraft.resources.ResourceLocation;

public class GameStageAdvancement {
	public final ResourceLocation id;

	public GameStageAdvancement() {
		this.id = ResourceLocation.parse( "majruszsdifficulty:game_stage" );
	}

	public ResourceLocation getId() {
		return this.id;
	}

	public void trigger() {
		// TODO: Re-implement for 1.21.1
	}
}
