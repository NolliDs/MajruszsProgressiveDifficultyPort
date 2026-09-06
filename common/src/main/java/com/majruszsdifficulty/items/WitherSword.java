/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 * 
 * Could not load the following classes:
 *  com.majruszlibrary.data.Reader
 *  com.majruszlibrary.data.Serializables
 *  com.majruszlibrary.entity.EffectDef
 *  com.majruszlibrary.events.OnEntityDamaged
 *  com.majruszlibrary.events.OnItemAttributeTooltip
 *  com.majruszlibrary.text.TextHelper
 *  com.majruszlibrary.time.TimeHelper
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.Rarity
 *  net.minecraft.world.item.SwordItem
 *  net.minecraft.world.item.Tier
 */
package com.majruszsdifficulty.items;

import com.majruszlibrary.data.Reader;
import com.majruszlibrary.data.Serializables;
import com.majruszlibrary.entity.EffectDef;
import com.majruszlibrary.events.OnEntityDamaged;
import com.majruszlibrary.events.OnItemAttributeTooltip;
import com.majruszlibrary.text.TextHelper;
import com.majruszlibrary.time.TimeHelper;
import com.majruszsdifficulty.data.Config;
import com.majruszsdifficulty.items.CustomItemTier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class WitherSword
extends SwordItem {
    private static EffectDef EFFECT = new EffectDef(() -> (MobEffect)MobEffects.WITHER.value(), 1, 6.0f);

    public WitherSword() {
        super((Tier)CustomItemTier.WITHER, new Item.Properties().durability(CustomItemTier.WITHER.getUses()).attributes(WitherSword.createAttributes((Tier)CustomItemTier.WITHER, (int)3, (float)-2.4f)).rarity(Rarity.UNCOMMON));
    }

    private static void apply(OnEntityDamaged data) {
        data.target.addEffect(new MobEffectInstance(MobEffects.WITHER, TimeHelper.toTicks((double)WitherSword.EFFECT.duration), WitherSword.EFFECT.amplifier));
    }

    private static void addTooltip(OnItemAttributeTooltip data) {
        data.add(EquipmentSlot.MAINHAND, (Component)TextHelper.translatable((String)"item.majruszsdifficulty.wither_sword.effect", (Object[])new Object[]{TextHelper.percent((float)1.0f), TextHelper.toRoman((int)(WitherSword.EFFECT.amplifier + 1))}).withStyle(ChatFormatting.DARK_GREEN));
    }

    static {
        OnEntityDamaged.listen(WitherSword::apply).addCondition(data -> data.attacker != null).addCondition(data -> data.attacker.getMainHandItem().getItem() instanceof WitherSword);
        OnItemAttributeTooltip.listen(WitherSword::addTooltip).addCondition(data -> data.itemStack.getItem() instanceof WitherSword);
        Serializables.getStatic(Config.Items.class).define("wither_sword", WitherSword.class);
        Serializables.getStatic(WitherSword.class).define("effect", Reader.custom(EffectDef::new), () -> EFFECT, v -> {
            EFFECT = v;
        });
    }
}

