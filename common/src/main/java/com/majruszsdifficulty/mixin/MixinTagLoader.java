package com.majruszsdifficulty.mixin;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mixin( TagLoader.class )
public class MixinTagLoader {
	@Shadow
	private String directory;

	@Inject( at = @At( "RETURN" ), method = "load" )
	private void injectModTags( net.minecraft.server.packs.resources.ResourceManager rm,
		CallbackInfoReturnable< Map< ResourceLocation, List< TagLoader.EntryWithSource > > > callback
	) {
		// Only inject for item tags
		if( !"tags/item".equals( this.directory ) ) return;

		Map< ResourceLocation, List< TagLoader.EntryWithSource > > map = callback.getReturnValue();
		String src = "majruszsdifficulty";

		add( map, "enchantable/sword", "majruszsdifficulty:wither_sword", src );
		add( map, "enchantable/sword", "majruszsdifficulty:enderium_sword", src );
		add( map, "enchantable/sharp_weapon", "majruszsdifficulty:wither_sword", src );
		add( map, "enchantable/sharp_weapon", "majruszsdifficulty:enderium_sword", src );
		add( map, "enchantable/sharp_weapon", "majruszsdifficulty:enderium_axe", src );
		add( map, "enchantable/weapon", "majruszsdifficulty:wither_sword", src );
		add( map, "enchantable/weapon", "majruszsdifficulty:enderium_sword", src );
		add( map, "enchantable/weapon", "majruszsdifficulty:enderium_axe", src );
		add( map, "enchantable/fire_aspect", "majruszsdifficulty:wither_sword", src );
		add( map, "enchantable/fire_aspect", "majruszsdifficulty:enderium_sword", src );
		add( map, "enchantable/mining", "majruszsdifficulty:enderium_pickaxe", src );
		add( map, "enchantable/mining", "majruszsdifficulty:enderium_shovel", src );
		add( map, "enchantable/mining", "majruszsdifficulty:enderium_axe", src );
		add( map, "enchantable/mining", "majruszsdifficulty:enderium_hoe", src );
		add( map, "enchantable/mining_loot", "majruszsdifficulty:enderium_pickaxe", src );
		add( map, "enchantable/mining_loot", "majruszsdifficulty:enderium_shovel", src );

		for( String type : new String[]{"enderium","tattered"} ) {
			for( String slot : new String[]{"helmet","chestplate","leggings","boots"} ) {
				String id = "majruszsdifficulty:" + type + "_" + slot;
				add( map, "enchantable/armor", id, src );
				add( map, "enchantable/" + slot.replace("boots","foot_armor").replace("helmet","head_armor").replace("chestplate","chest_armor").replace("leggings","leg_armor"), id, src );
			}
		}

		String[] allItems = {"wither_sword","enderium_sword","enderium_axe","enderium_pickaxe","enderium_shovel","enderium_hoe",
			"enderium_helmet","enderium_chestplate","enderium_leggings","enderium_boots",
			"tattered_helmet","tattered_chestplate","tattered_leggings","tattered_boots"};
		for( String item : allItems ) {
			add( map, "enchantable/durability", "majruszsdifficulty:" + item, src );
			add( map, "enchantable/vanishing", "majruszsdifficulty:" + item, src );
		}

		String[] equipItems = {"enderium_helmet","enderium_chestplate","enderium_leggings","enderium_boots",
			"tattered_helmet","tattered_chestplate","tattered_leggings","tattered_boots"};
		for( String item : equipItems ) {
			add( map, "enchantable/equippable", "majruszsdifficulty:" + item, src );
		}
	}

	private void add( Map< ResourceLocation, List< TagLoader.EntryWithSource > > map, String tagPath, String itemId, String source ) {
		ResourceLocation tagId = ResourceLocation.withDefaultNamespace( tagPath );
		List< TagLoader.EntryWithSource > entries = map.computeIfAbsent( tagId, k -> new ArrayList<>() );
		entries.add( new TagLoader.EntryWithSource( TagEntry.element( ResourceLocation.parse( itemId ) ), source ) );
	}
}
