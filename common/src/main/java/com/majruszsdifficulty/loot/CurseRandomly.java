package com.majruszsdifficulty.loot;

import com.majruszlibrary.registry.Registries;
import com.majruszsdifficulty.MajruszsDifficulty;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CurseRandomly extends LootItemConditionalFunction {
	public static final MapCodec<CurseRandomly> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			LootItemCondition.DIRECT_CODEC.listOf().optionalFieldOf( "conditions", List.of() ).forGetter( f -> f.predicates )
		).apply( instance, CurseRandomly::new )
	);

	@SuppressWarnings( { "rawtypes", "unchecked" } )
	public static LootItemFunctionType<CurseRandomly> create() {
		return ( LootItemFunctionType )new LootItemFunctionType<>( CurseRandomly.CODEC );
	}

	CurseRandomly( List< LootItemCondition > conditions ) {
		super( conditions );
	}

	@Override
	@SuppressWarnings( "unchecked" )
	public LootItemFunctionType< CurseRandomly > getType() {
		return ( LootItemFunctionType< CurseRandomly > )( Object )MajruszsDifficulty.CURSE_RANDOMLY_LOOT_FUNCTION.get();
	}

	@Override
	protected ItemStack run( ItemStack itemStack, LootContext context ) {
		List< Enchantment > curses = this.generateCurses( itemStack );
		if( !curses.isEmpty() ) {
			itemStack = CurseRandomly.enchantItem( itemStack, context.getRandom(), curses );
		}
		List< Enchantment > enchantments = this.generateEnchantments( itemStack );
		if( !enchantments.isEmpty() ) {
			itemStack = CurseRandomly.enchantItem( itemStack, context.getRandom(), enchantments );
		}

		return itemStack;
	}

	private static boolean isCurse( Enchantment enchantment ) {
		net.minecraft.core.Holder< Enchantment > holder = Registries.ENCHANTMENTS.getHolder( enchantment );
		return holder.is( net.minecraft.tags.EnchantmentTags.CURSE );
	}

	private List< Enchantment > generateCurses( ItemStack itemStack ) {
		return StreamSupport.stream( Registries.ENCHANTMENTS.spliterator(), false )
			.filter( enchantment->CurseRandomly.isCurse( enchantment ) )
			.filter( enchantment->enchantment.canEnchant( itemStack ) )
			.toList();
	}

	private List< Enchantment > generateEnchantments( ItemStack itemStack ) {
		return StreamSupport.stream( Registries.ENCHANTMENTS.spliterator(), false )
			.filter( enchantment->!CurseRandomly.isCurse( enchantment ) )
			.filter( enchantment->enchantment.canEnchant( itemStack ) )
			.toList();
	}

	private static ItemStack enchantItem( ItemStack itemStack, RandomSource randomSource, List< Enchantment > enchantments ) {
		Enchantment enchantment = enchantments.get( randomSource.nextInt( enchantments.size() ) );
		Holder< Enchantment > holder = Registries.ENCHANTMENTS.getHolder( enchantment );
		int level = Mth.nextInt( randomSource, enchantment.getMinLevel(), enchantment.getMaxLevel() );
		if( itemStack.is( Items.BOOK ) ) {
			itemStack = new ItemStack( Items.ENCHANTED_BOOK );
			ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable( ItemEnchantments.EMPTY );
			mutable.set( holder, level );
			itemStack.set( DataComponents.STORED_ENCHANTMENTS, mutable.toImmutable() );
		} else {
			itemStack = EnchantmentHelper.enchantItem( randomSource, itemStack, level, Stream.of( holder ) );
		}

		return itemStack;
	}
}
