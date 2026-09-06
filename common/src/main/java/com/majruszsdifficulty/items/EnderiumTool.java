/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.AxeItem
 *  net.minecraft.world.item.DiggerItem
 *  net.minecraft.world.item.HoeItem
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.PickaxeItem
 *  net.minecraft.world.item.Rarity
 *  net.minecraft.world.item.ShovelItem
 *  net.minecraft.world.item.SwordItem
 *  net.minecraft.world.item.Tier
 */
package com.majruszsdifficulty.items;

import com.majruszsdifficulty.items.CustomItemTier;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class EnderiumTool {

    public static class Sword
    extends SwordItem {
        public Sword() {
            super((Tier)CustomItemTier.ENDERIUM, new Item.Properties().durability(CustomItemTier.ENDERIUM.getUses()).attributes(SwordItem.createAttributes((Tier)CustomItemTier.ENDERIUM, (int)4, (float)-2.6f)).rarity(Rarity.UNCOMMON).fireResistant());
        }
    }

    public static class Shovel
    extends ShovelItem {
        public Shovel() {
            super((Tier)CustomItemTier.ENDERIUM, new Item.Properties().durability(CustomItemTier.ENDERIUM.getUses()).attributes(DiggerItem.createAttributes((Tier)CustomItemTier.ENDERIUM, (float)1.5f, (float)-3.0f)).rarity(Rarity.UNCOMMON).fireResistant());
        }
    }

    public static class Pickaxe
    extends PickaxeItem {
        public Pickaxe() {
            super((Tier)CustomItemTier.ENDERIUM, new Item.Properties().durability(CustomItemTier.ENDERIUM.getUses()).attributes(DiggerItem.createAttributes((Tier)CustomItemTier.ENDERIUM, (float)1.0f, (float)-2.8f)).rarity(Rarity.UNCOMMON).fireResistant());
        }
    }

    public static class Hoe
    extends HoeItem {
        public Hoe() {
            super((Tier)CustomItemTier.ENDERIUM, new Item.Properties().durability(CustomItemTier.ENDERIUM.getUses()).attributes(DiggerItem.createAttributes((Tier)CustomItemTier.ENDERIUM, (float)-5.0f, (float)0.0f)).rarity(Rarity.UNCOMMON).fireResistant());
        }
    }

    public static class Axe
    extends AxeItem {
        public Axe() {
            super((Tier)CustomItemTier.ENDERIUM, new Item.Properties().durability(CustomItemTier.ENDERIUM.getUses()).attributes(DiggerItem.createAttributes((Tier)CustomItemTier.ENDERIUM, (float)6.0f, (float)-3.1f)).rarity(Rarity.UNCOMMON).fireResistant());
        }
    }
}

