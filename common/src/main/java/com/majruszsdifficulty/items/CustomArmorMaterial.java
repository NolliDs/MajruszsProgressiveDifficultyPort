/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.item.ArmorItem$Type
 *  net.minecraft.world.item.ArmorMaterial
 *  net.minecraft.world.item.ArmorMaterial$Layer
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.ItemLike
 */
package com.majruszsdifficulty.items;

import com.majruszsdifficulty.MajruszsDifficulty;
import java.util.EnumMap;
import java.util.List;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class CustomArmorMaterial {
    private static final EnumMap<ArmorItem.Type, Integer> TATTERED_DEFENSE = new EnumMap(ArmorItem.Type.class);
    private static final EnumMap<ArmorItem.Type, Integer> ENDERIUM_DEFENSE = new EnumMap(ArmorItem.Type.class);
    public static final int TATTERED_DURABILITY = 5;
    public static final int ENDERIUM_DURABILITY = 39;
    public static final ArmorMaterial TATTERED;
    public static final ArmorMaterial ENDERIUM;

    static {
        TATTERED_DEFENSE.put(ArmorItem.Type.BOOTS, 1);
        TATTERED_DEFENSE.put(ArmorItem.Type.LEGGINGS, 2);
        TATTERED_DEFENSE.put(ArmorItem.Type.CHESTPLATE, 3);
        TATTERED_DEFENSE.put(ArmorItem.Type.HELMET, 1);
        ENDERIUM_DEFENSE.put(ArmorItem.Type.BOOTS, 4);
        ENDERIUM_DEFENSE.put(ArmorItem.Type.LEGGINGS, 6);
        ENDERIUM_DEFENSE.put(ArmorItem.Type.CHESTPLATE, 8);
        ENDERIUM_DEFENSE.put(ArmorItem.Type.HELMET, 4);
        TATTERED = new ArmorMaterial(TATTERED_DEFENSE, 15, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of((ItemLike[])new ItemLike[]{(ItemLike)MajruszsDifficulty.CLOTH_ITEM.get()}), List.of(new ArmorMaterial.Layer(ResourceLocation.parse((String)"minecraft:tattered"))), 0.0f, 0.0f);
        ENDERIUM = new ArmorMaterial(ENDERIUM_DEFENSE, 15, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Ingredient.of((ItemLike[])new ItemLike[]{(ItemLike)MajruszsDifficulty.ENDERIUM_INGOT_ITEM.get()}), List.of(new ArmorMaterial.Layer(ResourceLocation.parse((String)"minecraft:enderium"))), 4.0f, 0.1f);
    }
}

