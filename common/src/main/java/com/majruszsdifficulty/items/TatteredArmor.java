/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 * 
 * Could not load the following classes:
 *  com.majruszlibrary.events.OnEntityPowderSnowCheck
 *  net.minecraft.core.Holder
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ArmorItem
 *  net.minecraft.world.item.ArmorItem$Type
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.Rarity
 */
package com.majruszsdifficulty.items;

import com.majruszlibrary.events.OnEntityPowderSnowCheck;
import com.majruszsdifficulty.MajruszsDifficulty;
import com.majruszsdifficulty.items.CustomArmorMaterial;
import java.util.function.Supplier;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class TatteredArmor
extends ArmorItem {
    public static Supplier<TatteredArmor> boots() {
        return () -> new TatteredArmor(ArmorItem.Type.BOOTS);
    }

    public static Supplier<TatteredArmor> chestplate() {
        return () -> new TatteredArmor(ArmorItem.Type.CHESTPLATE);
    }

    public static Supplier<TatteredArmor> helmet() {
        return () -> new TatteredArmor(ArmorItem.Type.HELMET);
    }

    public static Supplier<TatteredArmor> leggings() {
        return () -> new TatteredArmor(ArmorItem.Type.LEGGINGS);
    }

    private static boolean hasBootsEquipped(OnEntityPowderSnowCheck data) {
        LivingEntity entity;
        Entity entity2 = data.entity;
        return entity2 instanceof LivingEntity && (entity = (LivingEntity)entity2).getItemBySlot(EquipmentSlot.FEET).is((Item)MajruszsDifficulty.TATTERED_BOOTS_ITEM.get());
    }

    private TatteredArmor(ArmorItem.Type type) {
        super(Holder.direct(CustomArmorMaterial.TATTERED), type, new Item.Properties().durability(type.getDurability(CustomArmorMaterial.TATTERED_DURABILITY)).rarity(Rarity.COMMON).stacksTo(1));
    }

    static {
        OnEntityPowderSnowCheck.listen(OnEntityPowderSnowCheck::makeWalkable).addCondition(TatteredArmor::hasBootsEquipped);
    }
}

