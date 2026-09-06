/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 * 
 * Could not load the following classes:
 *  com.majruszlibrary.text.TextHelper
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.item.CreativeModeTab
 *  net.minecraft.world.item.CreativeModeTab$ItemDisplayParameters
 *  net.minecraft.world.item.CreativeModeTab$Output
 *  net.minecraft.world.item.CreativeModeTab$Row
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 */
package com.majruszsdifficulty.items;

import com.majruszlibrary.text.TextHelper;
import com.majruszsdifficulty.MajruszsDifficulty;
import java.util.function.Supplier;
import java.util.stream.Stream;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class CreativeModeTabs {
    private static final Component PRIMARY = TextHelper.translatable((String)"itemGroup.majruszsdifficulty.primary", (Object[])new Object[0]);

    public static Supplier<CreativeModeTab> primary() {
        return () -> CreativeModeTab.builder((CreativeModeTab.Row)CreativeModeTab.Row.TOP, (int)0).title(PRIMARY).displayItems(CreativeModeTabs::definePrimaryItems).icon(() -> new ItemStack((ItemLike)MajruszsDifficulty.UNDEAD_BATTLE_STANDARD_ITEM.get())).build();
    }

    private static void definePrimaryItems(CreativeModeTab.ItemDisplayParameters params, CreativeModeTab.Output output) {
        Stream.of(MajruszsDifficulty.INFERNAL_SPONGE_ITEM, MajruszsDifficulty.SOAKED_INFERNAL_SPONGE_ITEM, MajruszsDifficulty.ENDERIUM_BLOCK_ITEM, MajruszsDifficulty.ENDERIUM_SHARD_ORE_ITEM, MajruszsDifficulty.FRAGILE_END_STONE_ITEM, MajruszsDifficulty.INFESTED_END_STONE_ITEM).map(item -> new ItemStack((ItemLike)item.get())).forEach(arg_0 -> ((CreativeModeTab.Output)output).accept(arg_0));
        Stream.of(MajruszsDifficulty.BANDAGE_ITEM, MajruszsDifficulty.GOLDEN_BANDAGE_ITEM, MajruszsDifficulty.CLOTH_ITEM, MajruszsDifficulty.UNDEAD_BATTLE_STANDARD_ITEM, MajruszsDifficulty.SOUL_JAR_ITEM, MajruszsDifficulty.RECALL_POTION_ITEM, MajruszsDifficulty.EVOKER_FANG_SCROLL_ITEM, MajruszsDifficulty.SONIC_BOOM_SCROLL_ITEM, MajruszsDifficulty.CERBERUS_FANG_ITEM, MajruszsDifficulty.ENDER_POUCH_ITEM, MajruszsDifficulty.ENDERIUM_SHARD_LOCATOR_ITEM, MajruszsDifficulty.ENDERIUM_SHARD_ITEM, MajruszsDifficulty.ENDERIUM_INGOT_ITEM, MajruszsDifficulty.ENDERIUM_SMITHING_TEMPLATE_ITEM).map(item -> new ItemStack((ItemLike)item.get())).forEach(arg_0 -> ((CreativeModeTab.Output)output).accept(arg_0));
        Stream.of(MajruszsDifficulty.WITHER_SWORD_ITEM, MajruszsDifficulty.ENDERIUM_SWORD_ITEM, MajruszsDifficulty.ENDERIUM_SHOVEL_ITEM, MajruszsDifficulty.ENDERIUM_PICKAXE_ITEM, MajruszsDifficulty.ENDERIUM_AXE_ITEM, MajruszsDifficulty.ENDERIUM_HOE_ITEM, MajruszsDifficulty.TATTERED_HELMET_ITEM, MajruszsDifficulty.TATTERED_CHESTPLATE_ITEM, MajruszsDifficulty.TATTERED_LEGGINGS_ITEM, MajruszsDifficulty.TATTERED_BOOTS_ITEM, MajruszsDifficulty.ENDERIUM_HELMET_ITEM, MajruszsDifficulty.ENDERIUM_CHESTPLATE_ITEM, MajruszsDifficulty.ENDERIUM_LEGGINGS_ITEM, MajruszsDifficulty.ENDERIUM_BOOTS_ITEM).map(item -> new ItemStack((ItemLike)item.get())).forEach(arg_0 -> ((CreativeModeTab.Output)output).accept(arg_0));
        Stream.of(MajruszsDifficulty.ANGLER_TREASURE_BAG_ITEM, MajruszsDifficulty.ELDER_GUARDIAN_TREASURE_BAG_ITEM, MajruszsDifficulty.ENDER_DRAGON_TREASURE_BAG_ITEM, MajruszsDifficulty.PILLAGER_TREASURE_BAG_ITEM, MajruszsDifficulty.UNDEAD_ARMY_TREASURE_BAG_ITEM, MajruszsDifficulty.WARDEN_TREASURE_BAG_ITEM, MajruszsDifficulty.WITHER_TREASURE_BAG_ITEM).map(item -> new ItemStack((ItemLike)item.get())).forEach(arg_0 -> ((CreativeModeTab.Output)output).accept(arg_0));
        Stream.of(MajruszsDifficulty.CERBERUS_SPAWN_EGG_ITEM, MajruszsDifficulty.CREEPERLING_SPAWN_EGG_ITEM, MajruszsDifficulty.CURSED_ARMOR_SPAWN_EGG_ITEM, MajruszsDifficulty.GIANT_SPAWN_EGG_ITEM, MajruszsDifficulty.ILLUSIONER_SPAWN_EGG_ITEM, MajruszsDifficulty.TANK_SPAWN_EGG_ITEM).map(item -> new ItemStack((ItemLike)item.get())).forEach(arg_0 -> ((CreativeModeTab.Output)output).accept(arg_0));
    }
}

