package com.majruszsdifficulty.items;

import com.majruszlibrary.annotation.Dist;
import com.majruszlibrary.annotation.OnlyIn;
import com.majruszlibrary.data.Reader;
import com.majruszlibrary.data.Serializables;
import com.majruszlibrary.entity.AttributeHandler;
import com.majruszlibrary.events.*;
import com.majruszlibrary.events.base.Events;
import com.majruszlibrary.math.Random;
import com.majruszlibrary.platform.Side;
import com.majruszlibrary.text.TextHelper;
import com.majruszsdifficulty.events.OnSoulJarMultiplierGet;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ShieldItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class SoulJar extends Item {
	private static final float DAMAGE_BONUS = 3.0f;
	private static final float MOVE_BONUS = 0.15f;
	private static final int ARMOR_BONUS = 3;
	private static final float MINE_BONUS = 0.15f;
	private static final int LUCK_BONUS = 1;
	private static final float SWIM_BONUS = 0.30f;
	private static final AttributeHandler ARMOR_ATTRIBUTE = new AttributeHandler( "soul_jar_armor_bonus", ()->Attributes.ARMOR.value(), AttributeModifier.Operation.ADD_VALUE );
	private static final AttributeHandler LUCK_ATTRIBUTE = new AttributeHandler( "soul_jar_luck_bonus", ()->Attributes.LUCK.value(), AttributeModifier.Operation.ADD_VALUE );
	private static final AttributeHandler MOVE_ATTRIBUTE = new AttributeHandler( "soul_jar_movement_bonus", ()->Attributes.MOVEMENT_SPEED.value(), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL );

	static {
		OnPlayerInteracted.listen( SoulJar::randomize )
			.addCondition( data->SoulJar.canHaveSouls( data.itemStack ) );

		OnLootGenerated.listen( SoulJar::randomize );

		OnEntityPreDamaged.listen( SoulJar::increaseDamage )
			.addCondition( data->data.target instanceof Mob mob && mob.getType().is( EntityTypeTags.UNDEAD ) )
			.addCondition( data->data.attacker != null )
			.addCondition( data->BonusInfo.has( data.attacker, BonusType.DAMAGE ) );

		OnBreakSpeedGet.listen( SoulJar::increaseMineSpeed )
			.addCondition( data->BonusInfo.has( data.player, BonusType.MINE ) );

		OnEntitySwimSpeedMultiplierGet.listen( SoulJar::increaseSwimSpeed )
			.addCondition( data->BonusInfo.has( data.entity, BonusType.SWIM ) );

		OnItemEquipped.listen( SoulJar::updateAttributes );

		OnSoulJarMultiplierGet.listen( SoulJar::decreaseShieldBonus )
			.addCondition( data->data.itemStack.getItem() instanceof ShieldItem );

		OnItemRenderColorsGet.listen( SoulJar::changeSoulColor )
			.addCondition( data->data.itemStack.getItem() instanceof SoulJar );

		Serializables.get( BonusInfo.class )
			.define( "SoulJarBonusMask", Reader.integer(), BonusInfo::getMask, BonusInfo::setMask );
	}

	public SoulJar() {
		super( new Properties().stacksTo( 1 ).rarity( Rarity.UNCOMMON ) );
	}

	private static void changeSoulColor( OnItemRenderColorsGet data ) {
		BonusInfo bonusInfo = BonusInfo.read( data.itemStack );
		for( int idx = 0; idx < 3; ++idx ) {
			data.add( idx + 1, bonusInfo.getBonus( idx ).map( BonusType::getColor ).orElse( ( 0xeeeeee - idx * 0x111111 ) | 0xFF000000 ) );
		}
	}

	public static int getSoulColor( ItemStack stack, int tintIndex ) {
		if( tintIndex == 0 ) return -1;
		BonusInfo bonusInfo = BonusInfo.read( stack );
		return bonusInfo.getBonus( tintIndex - 1 )
			.map( BonusType::getColor )
			.orElse( 0xeeeeee - ( tintIndex - 1 ) * 0x111111 );
	}

	private static void randomize( OnPlayerInteracted data ) {
		SoulJar.tryToRandomize( data.itemStack );
		SoulJar.applyBonuses( data.player, data.itemStack );
	}

	private static void increaseDamage( OnEntityPreDamaged data ) {
		data.damage += DAMAGE_BONUS * SoulJar.getMultiplier( data.attacker, data.attacker.getOffhandItem() );
		data.spawnMagicParticles = true;
	}

	private static void increaseMineSpeed( OnBreakSpeedGet data ) {
		data.speed += data.original * MINE_BONUS * SoulJar.getMultiplier( data.player, data.player.getOffhandItem() );
	}

	private static void increaseSwimSpeed( OnEntitySwimSpeedMultiplierGet data ) {
		data.multiplier += data.original * SWIM_BONUS * SoulJar.getMultiplier( data.entity, data.entity.getOffhandItem() );
	}

	private static void applyBonuses( LivingEntity entity, ItemStack itemStack ) {
		float multiplier = SoulJar.canHaveSouls( itemStack ) ? SoulJar.getMultiplier( entity, itemStack ) : 0.0f;
		BonusInfo bonusInfo = BonusInfo.read( itemStack );

		ARMOR_ATTRIBUTE.setValue( ( bonusInfo.has( BonusType.ARMOR ) ? ARMOR_BONUS : 0.0f ) * multiplier ).apply( entity );
		LUCK_ATTRIBUTE.setValue( ( bonusInfo.has( BonusType.LUCK ) ? LUCK_BONUS : 0.0f ) * multiplier ).apply( entity );
		MOVE_ATTRIBUTE.setValue( ( bonusInfo.has( BonusType.MOVE ) ? MOVE_BONUS : 0.0f ) * multiplier ).apply( entity );
	}

	private static void updateAttributes( OnItemEquipped data ) {
		SoulJar.applyBonuses( data.entity, data.entity.getOffhandItem() );
	}

	private static void randomize( OnLootGenerated data ) {
		data.generatedLoot.forEach( SoulJar::tryToRandomize );
	}

	private static void decreaseShieldBonus( OnSoulJarMultiplierGet data ) {
		data.multiplier *= 2.0f / 3.0f;
	}

	public static void tryToRandomize( ItemStack itemStack ) {
		if( itemStack.getItem() instanceof SoulJar ) {
			CustomData customData = itemStack.getOrDefault( DataComponents.CUSTOM_DATA, CustomData.EMPTY );
			net.minecraft.nbt.CompoundTag tag = customData.copyTag();
			Serializables.modify( new BonusInfo(), tag, bonusInfo->{
				if( bonusInfo.getBonusTypes().isEmpty() ) {
					bonusInfo.randomize();
				}
			} );
			itemStack.set( DataComponents.CUSTOM_DATA, CustomData.of( tag ) );
		}
	}

	private static float getMultiplier( LivingEntity entity, ItemStack itemStack ) {
		return Events.dispatch( new OnSoulJarMultiplierGet( entity, itemStack ) ).getMultiplier();
	}

	private static boolean canHaveSouls( ItemStack itemStack ) {
		return itemStack.getItem() instanceof SoulJar
			|| itemStack.getItem() instanceof ShieldItem;
	}

	public enum BonusType {
		DAMAGE( "smite", "entity.minecraft.wolf", ChatFormatting.RED, 0xFFcc5555, multiplier->TextHelper.signed( DAMAGE_BONUS * multiplier ) ),
		MOVE( "move", "entity.minecraft.horse", ChatFormatting.WHITE, 0xFFdddddd, multiplier->TextHelper.signedPercent( MOVE_BONUS * multiplier ) ),
		ARMOR( "armor", "entity.majruszsdifficulty.tank", ChatFormatting.BLUE, 0xFF5555cc, multiplier->TextHelper.signed( ( int )( ARMOR_BONUS * multiplier ) ) ),
		MINE( "mine", "entity.minecraft.sniffer", ChatFormatting.YELLOW, 0xFFcccc55, multiplier->TextHelper.signedPercent( MINE_BONUS * multiplier ) ),
		LUCK( "luck", "entity.minecraft.rabbit", ChatFormatting.GREEN, 0xFF55cc55, multiplier->TextHelper.signed( LUCK_BONUS * multiplier ) ),
		SWIM( "swim", "entity.minecraft.dolphin", ChatFormatting.AQUA, 0xFF55cccc, multiplier->TextHelper.signedPercent( SWIM_BONUS * multiplier ) );

		final String bonusId;
		final String mobId;
		final ChatFormatting soulFormatting;
		final int color;
		final Function< Float, String > valueProvider;

		BonusType( String bonusId, String mobId, ChatFormatting soulFormatting, int color, Function< Float, String > valueProvider ) {
			this.bonusId = "item.majruszsdifficulty.soul_jar.%s".formatted( bonusId );
			this.mobId = mobId;
			this.soulFormatting = soulFormatting;
			this.color = color;
			this.valueProvider = valueProvider;
		}

		public int getBit() {
			return 1 << this.ordinal();
		}

		public int getColor() {
			return this.color;
		}

		public MutableComponent getBonusComponent( float multiplier ) {
			return TextHelper.translatable( this.bonusId, this.valueProvider.apply( multiplier ) )
				.withStyle( ChatFormatting.BLUE );
		}

		public MutableComponent getSoulComponent() {
			return TextHelper.translatable( this.mobId )
				.withStyle( this.soulFormatting );
		}
	}

	public static class BonusInfo {
		private static final int BONUS_COUNT = 3;
		public final List< BonusType > bonuses = new ArrayList<>();

		public static BonusInfo read( ItemStack itemStack ) {
			BonusInfo bonusInfo = new BonusInfo();

			return Serializables.read( bonusInfo, itemStack.getOrDefault( DataComponents.CUSTOM_DATA, CustomData.EMPTY ).copyTag() );
		}

		public static boolean has( LivingEntity entity, BonusType bonusType ) {
			return BonusInfo.read( entity.getOffhandItem() ).has( bonusType );
		}

		public void randomize() {
			this.setMask( BonusInfo.toMask( Random.next( Arrays.stream( BonusType.values() ).toList(), BONUS_COUNT ) ) );
		}

		public void setMask( int mask ) {
			this.bonuses.clear();

			Arrays.stream( BonusType.values() )
				.filter( bonusType->( bonusType.getBit() & mask ) != 0b0 )
				.forEach( this.bonuses::add );
		}

		public int getMask() {
			return BonusInfo.toMask( this.bonuses );
		}

		public boolean has( BonusType bonusType ) {
			return this.bonuses.contains( bonusType );
		}

		public boolean hasBonuses() {
			return !this.bonuses.isEmpty();
		}

		public Optional< BonusType > getBonus( int idx ) {
			return idx < this.bonuses.size() ? Optional.of( this.bonuses.get( idx ) ) : Optional.empty();
		}

		public List< BonusType > getBonusTypes() {
			return this.bonuses;
		}

		public List< Component > getComponents() {
			List< Component > components = new ArrayList<>();
			if( this.bonuses.isEmpty() ) {
				Component bonusCount = TextHelper.literal( "%d", BONUS_COUNT ).withStyle( ChatFormatting.GREEN );
				components.add( TextHelper.translatable( "item.majruszsdifficulty.soul_jar.item_tooltip1", bonusCount ).withStyle( ChatFormatting.GRAY ) );
				components.add( TextHelper.translatable( "item.majruszsdifficulty.soul_jar.item_tooltip2" ).withStyle( ChatFormatting.GRAY ) );
			} else {
				components.add( TextHelper.empty() );
				components.add( TextHelper.translatable( "item.majruszsdifficulty.soul_jar.item_tooltip3" ).withStyle( ChatFormatting.GRAY ) );
				MutableComponent souls = TextHelper.literal( "" );
				for( BonusType bonusType : this.getBonusTypes() ) {
					souls.append( bonusType.getSoulComponent().append( " " ) );
				}
				components.add( souls );
			}

			return components;
		}

		private static int toMask( List< BonusType > bonuses ) {
			int mask = 0b0;
			for( BonusType bonusType : bonuses ) {
				mask |= bonusType.getBit();
			}

			return mask;
		}
	}

	@OnlyIn( Dist.CLIENT )
	public static class Client {
		static {
			OnItemAttributeTooltip.listen( Client::addTooltip )
				.addCondition( data->SoulJar.canHaveSouls( data.itemStack ) )
				.addCondition( data->Side.getLocalPlayer() != null /* compatibility check */ );

			OnItemTooltip.listen( Client::addTooltip )
				.addCondition( data->SoulJar.canHaveSouls( data.itemStack ) );
		}

		private static void changeSoulColor( OnItemRenderColorsGet data ) {
			BonusInfo bonusInfo = BonusInfo.read( data.itemStack );
			for( int idx = 0; idx < 3; ++idx ) {
				data.add( idx + 1, bonusInfo.getBonus( idx ).map( BonusType::getColor ).orElse( 0xeeeeee - idx * 0x111111 ) );
			}
		}

		private static void addTooltip( OnItemAttributeTooltip data ) {
			float multiplier = SoulJar.getMultiplier( Side.getLocalPlayer(), data.itemStack );
			BonusInfo bonusInfo = BonusInfo.read( data.itemStack );
			for( BonusType bonusType : bonusInfo.getBonusTypes() ) {
				data.add( EquipmentSlot.OFFHAND, bonusType.getBonusComponent( multiplier ) );
			}
		}

		private static void addTooltip( OnItemTooltip data ) {
			BonusInfo bonusInfo = BonusInfo.read( data.itemStack );
			if( bonusInfo.hasBonuses() || data.itemStack.getItem() instanceof SoulJar ) {
				data.components.addAll( bonusInfo.getComponents() );
			}
		}
	}
}
