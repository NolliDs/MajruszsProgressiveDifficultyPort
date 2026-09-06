/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 * 
 * Could not load the following classes:
 *  com.majruszlibrary.animations.ModelDef
 *  com.majruszlibrary.data.WorldData
 *  com.majruszlibrary.emitter.ParticleEmitter
 *  com.majruszlibrary.emitter.ParticleEmitter$Properties
 *  com.majruszlibrary.events.OnGameInitialized
 *  com.majruszlibrary.item.ItemHelper
 *  com.majruszlibrary.modhelper.ModHelper
 *  com.majruszlibrary.network.NetworkObject
 *  com.majruszlibrary.registry.Custom$Attributes
 *  com.majruszlibrary.registry.Custom$ItemProperties
 *  com.majruszlibrary.registry.Custom$ModelLayers
 *  com.majruszlibrary.registry.Custom$Particles
 *  com.majruszlibrary.registry.Custom$PotionRecipe
 *  com.majruszlibrary.registry.Custom$Renderers
 *  com.majruszlibrary.registry.Custom$SpawnPlacements
 *  com.majruszlibrary.registry.RegistryGroup
 *  com.majruszlibrary.registry.RegistryObject
 *  com.majruszlibrary.time.TimeHelper
 *  net.minecraft.client.model.geom.builders.CubeDeformation
 *  net.minecraft.client.model.geom.builders.LayerDefinition
 *  net.minecraft.client.model.geom.builders.MeshDefinition
 *  net.minecraft.core.Registry
 *  net.minecraft.core.particles.ParticleType
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.damagesource.DamageType
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.SpawnPlacementTypes
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.item.CreativeModeTab
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.SpawnEggItem
 *  net.minecraft.world.item.alchemy.Potion
 *  net.minecraft.world.item.crafting.RecipeSerializer
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraft.world.level.levelgen.placement.PlacedFeature
 *  net.minecraft.world.level.storage.loot.functions.LootItemFunctionType
 *  net.neoforged.api.distmarker.Dist
 *  net.neoforged.api.distmarker.OnlyIn
 */
package com.majruszsdifficulty;

import com.majruszlibrary.animations.ModelDef;
import com.majruszlibrary.emitter.ParticleEmitter;
import com.majruszlibrary.events.OnGameInitialized;
import com.majruszlibrary.item.ItemHelper;
import com.majruszlibrary.modhelper.ModHelper;
import com.majruszlibrary.network.NetworkObject;
import com.majruszlibrary.registry.Custom;
import com.majruszlibrary.registry.RegistryGroup;
import com.majruszlibrary.registry.RegistryObject;
import com.majruszlibrary.time.TimeHelper;
import com.majruszsdifficulty.blocks.EnderiumBlock;
import com.majruszsdifficulty.blocks.EnderiumShardOre;
import com.majruszsdifficulty.blocks.FragileEndStone;
import com.majruszsdifficulty.blocks.InfernalSponge;
import com.majruszsdifficulty.blocks.InfestedEndStone;
import com.majruszsdifficulty.blocks.SoakedInfernalSponge;
import com.majruszsdifficulty.data.Config;
import com.majruszsdifficulty.data.WorldData;
import com.majruszsdifficulty.effects.Bleeding;
import com.majruszsdifficulty.effects.BleedingImmunity;
import com.majruszsdifficulty.effects.GlassRegeneration;
import com.majruszsdifficulty.effects.bleeding.BleedingParticles;
import com.majruszsdifficulty.entity.Cerberus;
import com.majruszsdifficulty.entity.CerberusModel;
import com.majruszsdifficulty.entity.CerberusRenderer;
import com.majruszsdifficulty.entity.Creeperling;
import com.majruszsdifficulty.entity.CreeperlingModel;
import com.majruszsdifficulty.entity.CreeperlingRenderer;
import com.majruszsdifficulty.entity.CursedArmor;
import com.majruszsdifficulty.entity.CursedArmorModel;
import com.majruszsdifficulty.entity.CursedArmorRenderer;
import com.majruszsdifficulty.entity.Giant;
import com.majruszsdifficulty.entity.GiantModel;
import com.majruszsdifficulty.entity.GiantRenderer;
import com.majruszsdifficulty.entity.Illusioner;
import com.majruszsdifficulty.entity.IllusionerRenderer;
import com.majruszsdifficulty.entity.Tank;
import com.majruszsdifficulty.entity.TankModel;
import com.majruszsdifficulty.entity.TankRenderer;
import com.majruszsdifficulty.gamestage.GameStageAdvancement;
import com.majruszsdifficulty.items.Bandage;
import com.majruszsdifficulty.items.CerberusFang;
import com.majruszsdifficulty.items.Cloth;
import com.majruszsdifficulty.items.CreativeModeTabs;
import com.majruszsdifficulty.items.EnderPouch;
import com.majruszsdifficulty.items.EnderiumArmor;
import com.majruszsdifficulty.items.EnderiumIngot;
import com.majruszsdifficulty.items.EnderiumShard;
import com.majruszsdifficulty.items.EnderiumShardLocator;
import com.majruszsdifficulty.items.EnderiumSmithingTemplate;
import com.majruszsdifficulty.items.EnderiumTool;
import com.majruszsdifficulty.items.EvokerFangScroll;
import com.majruszsdifficulty.items.FakeItem;
import com.majruszsdifficulty.items.RecallPotion;
import com.majruszsdifficulty.items.SonicBoomScroll;
import com.majruszsdifficulty.items.SoulJar;
import com.majruszsdifficulty.items.TatteredArmor;
import com.majruszsdifficulty.items.TreasureBag;
import com.majruszsdifficulty.items.UndeadBattleStandard;
import com.majruszsdifficulty.items.WitherSword;
import com.majruszsdifficulty.loot.CurseRandomly;
import com.majruszsdifficulty.particles.BloodParticle;
import com.majruszsdifficulty.treasurebag.TreasureBagHelper;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import com.majruszlibrary.annotation.Dist;
import com.majruszlibrary.annotation.OnlyIn;

public class MajruszsDifficulty {
    public static final String MOD_ID = "majruszsdifficulty";
    public static final ModHelper HELPER = ModHelper.create((String)"majruszsdifficulty");
    public static final com.majruszlibrary.data.WorldData WORLD_DATA;
    public static final RegistryGroup<Block> BLOCKS;
    public static final RegistryGroup<CreativeModeTab> CREATIVE_MODE_TABS;
    public static final RegistryGroup<EntityType<?>> ENTITY_TYPES;
    public static final RegistryGroup<Item> ITEMS;
    public static final RegistryGroup<LootItemFunctionType<?>> LOOT_FUNCTIONS;
    @SuppressWarnings( "rawtypes" )
    public static final RegistryObject<LootItemFunctionType> CURSE_RANDOMLY_LOOT_FUNCTION;
    public static final RegistryGroup<MobEffect> MOB_EFFECTS;
    public static final RegistryGroup<ParticleType<?>> PARTICLES;
    public static final RegistryGroup<Potion> POTIONS;
    public static final RegistryGroup<RecipeSerializer<?>> RECIPES;
    public static final RegistryGroup<SoundEvent> SOUND_EVENTS;
    public static final NetworkObject<BleedingParticles.Message> BLEEDING_GUI;
    public static final NetworkObject<TreasureBag.RightClickAction> TREASURE_BAG_RIGHT_CLICK_NETWORK;
    public static final NetworkObject<TreasureBagHelper.Progress> TREASURE_BAG_PROGRESS_NETWORK;
    public static final RegistryObject<EntityType<Cerberus>> CERBERUS_ENTITY;
    public static final RegistryObject<EntityType<Creeperling>> CREEPERLING_ENTITY;
    public static final RegistryObject<EntityType<CursedArmor>> CURSED_ARMOR_ENTITY;
    public static final RegistryObject<EntityType<Giant>> GIANT_ENTITY;
    public static final RegistryObject<EntityType<Illusioner>> ILLUSIONER_ENTITY;
    public static final RegistryObject<EntityType<Tank>> TANK_ENTITY;
    public static final RegistryObject<Bleeding> BLEEDING_EFFECT;
    public static final RegistryObject<BleedingImmunity> BLEEDING_IMMUNITY_EFFECT;
    public static final RegistryObject<GlassRegeneration> GLASS_REGENERATION_EFFECT;
    public static final RegistryObject<EnderiumBlock> ENDERIUM_BLOCK;
    public static final RegistryObject<EnderiumShardOre> ENDERIUM_SHARD_ORE_BLOCK;
    public static final RegistryObject<FragileEndStone> FRAGILE_END_STONE_BLOCK;
    public static final RegistryObject<InfernalSponge> INFERNAL_SPONGE_BLOCK;
    public static final RegistryObject<InfestedEndStone> INFESTED_END_STONE_BLOCK;
    public static final RegistryObject<SoakedInfernalSponge> SOAKED_INFERNAL_SPONGE_BLOCK;
    public static final RegistryObject<Bandage> BANDAGE_ITEM;
    public static final RegistryObject<CerberusFang> CERBERUS_FANG_ITEM;
    public static final RegistryObject<Cloth> CLOTH_ITEM;
    public static final RegistryObject<EnderiumTool.Axe> ENDERIUM_AXE_ITEM;
    public static final RegistryObject<EnderiumArmor> ENDERIUM_BOOTS_ITEM;
    public static final RegistryObject<EnderiumArmor> ENDERIUM_CHESTPLATE_ITEM;
    public static final RegistryObject<EnderiumArmor> ENDERIUM_HELMET_ITEM;
    public static final RegistryObject<EnderiumTool.Hoe> ENDERIUM_HOE_ITEM;
    public static final RegistryObject<EnderiumIngot> ENDERIUM_INGOT_ITEM;
    public static final RegistryObject<EnderiumArmor> ENDERIUM_LEGGINGS_ITEM;
    public static final RegistryObject<EnderiumTool.Pickaxe> ENDERIUM_PICKAXE_ITEM;
    public static final RegistryObject<EnderiumShard> ENDERIUM_SHARD_ITEM;
    public static final RegistryObject<EnderiumShardLocator> ENDERIUM_SHARD_LOCATOR_ITEM;
    public static final RegistryObject<EnderiumTool.Shovel> ENDERIUM_SHOVEL_ITEM;
    public static final RegistryObject<EnderiumTool.Sword> ENDERIUM_SWORD_ITEM;
    public static final RegistryObject<EnderiumSmithingTemplate> ENDERIUM_SMITHING_TEMPLATE_ITEM;
    public static final RegistryObject<EnderPouch> ENDER_POUCH_ITEM;
    public static final RegistryObject<EvokerFangScroll> EVOKER_FANG_SCROLL_ITEM;
    public static final RegistryObject<Bandage> GOLDEN_BANDAGE_ITEM;
    public static final RegistryObject<RecallPotion> RECALL_POTION_ITEM;
    public static final RegistryObject<SonicBoomScroll> SONIC_BOOM_SCROLL_ITEM;
    public static final RegistryObject<SoulJar> SOUL_JAR_ITEM;
    public static final RegistryObject<TatteredArmor> TATTERED_BOOTS_ITEM;
    public static final RegistryObject<TatteredArmor> TATTERED_CHESTPLATE_ITEM;
    public static final RegistryObject<TatteredArmor> TATTERED_HELMET_ITEM;
    public static final RegistryObject<TatteredArmor> TATTERED_LEGGINGS_ITEM;
    public static final RegistryObject<UndeadBattleStandard> UNDEAD_BATTLE_STANDARD_ITEM;
    public static final RegistryObject<WitherSword> WITHER_SWORD_ITEM;
    public static final RegistryObject<EnderiumBlock.Item> ENDERIUM_BLOCK_ITEM;
    public static final RegistryObject<EnderiumShardOre.Item> ENDERIUM_SHARD_ORE_ITEM;
    public static final RegistryObject<FragileEndStone.Item> FRAGILE_END_STONE_ITEM;
    public static final RegistryObject<InfernalSponge.Item> INFERNAL_SPONGE_ITEM;
    public static final RegistryObject<InfestedEndStone.Item> INFESTED_END_STONE_ITEM;
    public static final RegistryObject<SoakedInfernalSponge.Item> SOAKED_INFERNAL_SPONGE_ITEM;
    public static final RegistryObject<SpawnEggItem> CERBERUS_SPAWN_EGG_ITEM;
    public static final RegistryObject<SpawnEggItem> CREEPERLING_SPAWN_EGG_ITEM;
    public static final RegistryObject<SpawnEggItem> CURSED_ARMOR_SPAWN_EGG_ITEM;
    public static final RegistryObject<SpawnEggItem> GIANT_SPAWN_EGG_ITEM;
    public static final RegistryObject<SpawnEggItem> ILLUSIONER_SPAWN_EGG_ITEM;
    public static final RegistryObject<SpawnEggItem> TANK_SPAWN_EGG_ITEM;
    public static final RegistryObject<TreasureBag> ANGLER_TREASURE_BAG_ITEM;
    public static final RegistryObject<TreasureBag> ELDER_GUARDIAN_TREASURE_BAG_ITEM;
    public static final RegistryObject<TreasureBag> ENDER_DRAGON_TREASURE_BAG_ITEM;
    public static final RegistryObject<TreasureBag> PILLAGER_TREASURE_BAG_ITEM;
    public static final RegistryObject<TreasureBag> UNDEAD_ARMY_TREASURE_BAG_ITEM;
    public static final RegistryObject<TreasureBag> WARDEN_TREASURE_BAG_ITEM;
    public static final RegistryObject<TreasureBag> WITHER_TREASURE_BAG_ITEM;
    public static final RegistryObject<Potion> WITHER_POTION;
    public static final RegistryObject<Potion> WITHER_LONG_POTION;
    public static final RegistryObject<Potion> WITHER_STRONG_POTION;
    public static final RegistryObject<SimpleParticleType> BLOOD_PARTICLE;
    public static final RegistryObject<CreativeModeTab> PRIMARY_MODE_TAB;
    public static final ResourceKey<PlacedFeature> ENDERIUM_ORE_PLACED_FEATURE;
    public static final ResourceKey<PlacedFeature> ENDERIUM_ORE_LARGE_PLACED_FEATURE;
    public static final ResourceKey<PlacedFeature> FRAGILE_END_STONE_PLACED_FEATURE;
    public static final ResourceKey<PlacedFeature> FRAGILE_END_STONE_LARGE_PLACED_FEATURE;
    public static final ResourceKey<PlacedFeature> INFESTED_END_STONE_PLACED_FEATURE;
    public static final ResourceKey<DamageType> BLEEDING_DAMAGE_SOURCE;
    public static final GameStageAdvancement GAME_STAGE_ADVANCEMENT;
    public static final RegistryObject<SoundEvent> UNDEAD_ARMY_APPROACHING_SOUND;
    public static final RegistryObject<SoundEvent> UNDEAD_ARMY_WAVE_STARTED_SOUND;

    private static RegistryObject<SoundEvent> register(String name) {
        return SOUND_EVENTS.create(name, () -> SoundEvent.createVariableRangeEvent((ResourceLocation)HELPER.getLocation(name)));
    }

    private static void addDefaultEmitters(OnGameInitialized data) {
        ParticleEmitter.setDefault((ParticleType)((ParticleType)BLOOD_PARTICLE.get()), (ParticleEmitter.Properties)new ParticleEmitter.Properties(ParticleEmitter.offset((float)0.5f), ParticleEmitter.speed((float)0.025f, (float)0.075f)));
    }

    private MajruszsDifficulty() {
    }

    static {
        HELPER.config(Config.class).autoSync().create();
        WORLD_DATA = HELPER.worldData(WorldData.class).client(WorldData.Client.class).create();
        BLOCKS = HELPER.create((Registry)BuiltInRegistries.BLOCK);
        CREATIVE_MODE_TABS = HELPER.create(BuiltInRegistries.CREATIVE_MODE_TAB);
        ENTITY_TYPES = HELPER.create((Registry)BuiltInRegistries.ENTITY_TYPE);
        ITEMS = HELPER.create((Registry)BuiltInRegistries.ITEM);
        LOOT_FUNCTIONS = HELPER.create(BuiltInRegistries.LOOT_FUNCTION_TYPE);
        CURSE_RANDOMLY_LOOT_FUNCTION = LOOT_FUNCTIONS.create("curse_randomly", CurseRandomly::create);
        MOB_EFFECTS = HELPER.create(BuiltInRegistries.MOB_EFFECT);
        PARTICLES = HELPER.create(BuiltInRegistries.PARTICLE_TYPE);
        POTIONS = HELPER.create(BuiltInRegistries.POTION);
        RECIPES = HELPER.create(BuiltInRegistries.RECIPE_SERIALIZER);
        SOUND_EVENTS = HELPER.create(BuiltInRegistries.SOUND_EVENT);
        BLEEDING_GUI = HELPER.create("bleeding_gui", BleedingParticles.Message.class);
        TREASURE_BAG_RIGHT_CLICK_NETWORK = HELPER.create("treasure_bag_right_click", TreasureBag.RightClickAction.class);
        TREASURE_BAG_PROGRESS_NETWORK = HELPER.create("treasure_bag_progress", TreasureBagHelper.Progress.class);
        CERBERUS_ENTITY = ENTITY_TYPES.create("cerberus", Cerberus::createEntityType);
        CREEPERLING_ENTITY = ENTITY_TYPES.create("creeperling", Creeperling::createEntityType);
        CURSED_ARMOR_ENTITY = ENTITY_TYPES.create("cursed_armor", CursedArmor::createEntityType);
        GIANT_ENTITY = ENTITY_TYPES.create("giant", Giant::createEntityType);
        ILLUSIONER_ENTITY = ENTITY_TYPES.create("illusioner", Illusioner::createEntityType);
        TANK_ENTITY = ENTITY_TYPES.create("tank", Tank::createEntityType);
        BLEEDING_EFFECT = MOB_EFFECTS.create("bleeding", Bleeding::new);
        BLEEDING_IMMUNITY_EFFECT = MOB_EFFECTS.create("bleeding_immunity", BleedingImmunity::new);
        GLASS_REGENERATION_EFFECT = MOB_EFFECTS.create("glass_regeneration", GlassRegeneration::new);
        ENDERIUM_BLOCK = BLOCKS.create("enderium_block", EnderiumBlock::new);
        ENDERIUM_SHARD_ORE_BLOCK = BLOCKS.create("enderium_shard_ore", EnderiumShardOre::new);
        FRAGILE_END_STONE_BLOCK = BLOCKS.create("fragile_end_stone", FragileEndStone::new);
        INFERNAL_SPONGE_BLOCK = BLOCKS.create("infernal_sponge", InfernalSponge::new);
        INFESTED_END_STONE_BLOCK = BLOCKS.create("infested_end_stone", InfestedEndStone::new);
        SOAKED_INFERNAL_SPONGE_BLOCK = BLOCKS.create("soaked_infernal_sponge", SoakedInfernalSponge::new);
        BANDAGE_ITEM = ITEMS.create("bandage", Bandage.normal());
        CERBERUS_FANG_ITEM = ITEMS.create("cerberus_fang", CerberusFang::new);
        CLOTH_ITEM = ITEMS.create("cloth", Cloth::new);
        ENDERIUM_AXE_ITEM = ITEMS.create("enderium_axe", EnderiumTool.Axe::new);
        ENDERIUM_BOOTS_ITEM = ITEMS.create("enderium_boots", EnderiumArmor.boots());
        ENDERIUM_CHESTPLATE_ITEM = ITEMS.create("enderium_chestplate", EnderiumArmor.chestplate());
        ENDERIUM_HELMET_ITEM = ITEMS.create("enderium_helmet", EnderiumArmor.helmet());
        ENDERIUM_HOE_ITEM = ITEMS.create("enderium_hoe", EnderiumTool.Hoe::new);
        ENDERIUM_INGOT_ITEM = ITEMS.create("enderium_ingot", EnderiumIngot::new);
        ENDERIUM_LEGGINGS_ITEM = ITEMS.create("enderium_leggings", EnderiumArmor.leggings());
        ENDERIUM_PICKAXE_ITEM = ITEMS.create("enderium_pickaxe", EnderiumTool.Pickaxe::new);
        ENDERIUM_SHARD_ITEM = ITEMS.create("enderium_shard", EnderiumShard::new);
        ENDERIUM_SHARD_LOCATOR_ITEM = ITEMS.create("enderium_shard_locator", EnderiumShardLocator::new);
        ENDERIUM_SHOVEL_ITEM = ITEMS.create("enderium_shovel", EnderiumTool.Shovel::new);
        ENDERIUM_SWORD_ITEM = ITEMS.create("enderium_sword", EnderiumTool.Sword::new);
        ENDERIUM_SMITHING_TEMPLATE_ITEM = ITEMS.create("enderium_upgrade_smithing_template", EnderiumSmithingTemplate::new);
        ENDER_POUCH_ITEM = ITEMS.create("ender_pouch", EnderPouch::new);
        EVOKER_FANG_SCROLL_ITEM = ITEMS.create("evoker_fang_scroll", EvokerFangScroll::new);
        GOLDEN_BANDAGE_ITEM = ITEMS.create("golden_bandage", Bandage.golden());
        RECALL_POTION_ITEM = ITEMS.create("recall_potion", RecallPotion::new);
        SONIC_BOOM_SCROLL_ITEM = ITEMS.create("sonic_boom_scroll", SonicBoomScroll::new);
        SOUL_JAR_ITEM = ITEMS.create("soul_jar", SoulJar::new);
        TATTERED_BOOTS_ITEM = ITEMS.create("tattered_boots", TatteredArmor.boots());
        TATTERED_CHESTPLATE_ITEM = ITEMS.create("tattered_chestplate", TatteredArmor.chestplate());
        TATTERED_HELMET_ITEM = ITEMS.create("tattered_helmet", TatteredArmor.helmet());
        TATTERED_LEGGINGS_ITEM = ITEMS.create("tattered_leggings", TatteredArmor.leggings());
        UNDEAD_BATTLE_STANDARD_ITEM = ITEMS.create("undead_battle_standard", UndeadBattleStandard::new);
        WITHER_SWORD_ITEM = ITEMS.create("wither_sword", WitherSword::new);
        ENDERIUM_BLOCK_ITEM = ITEMS.create("enderium_block", EnderiumBlock.Item::new);
        ENDERIUM_SHARD_ORE_ITEM = ITEMS.create("enderium_shard_ore", EnderiumShardOre.Item::new);
        FRAGILE_END_STONE_ITEM = ITEMS.create("fragile_end_stone", FragileEndStone.Item::new);
        INFERNAL_SPONGE_ITEM = ITEMS.create("infernal_sponge", InfernalSponge.Item::new);
        INFESTED_END_STONE_ITEM = ITEMS.create("infested_end_stone", InfestedEndStone.Item::new);
        SOAKED_INFERNAL_SPONGE_ITEM = ITEMS.create("soaked_infernal_sponge", SoakedInfernalSponge.Item::new);
        CERBERUS_SPAWN_EGG_ITEM = ITEMS.create("cerberus_spawn_egg", ItemHelper.createEgg(CERBERUS_ENTITY, (int)0x212121, (int)0xE0E0E0));
        CREEPERLING_SPAWN_EGG_ITEM = ITEMS.create("creeperling_spawn_egg", ItemHelper.createEgg(CREEPERLING_ENTITY, (int)894731, (int)0));
        CURSED_ARMOR_SPAWN_EGG_ITEM = ITEMS.create("cursed_armor_spawn_egg", ItemHelper.createEgg(CURSED_ARMOR_ENTITY, (int)0x808080, (int)0xE1E1E1));
        GIANT_SPAWN_EGG_ITEM = ITEMS.create("giant_spawn_egg", ItemHelper.createEgg(GIANT_ENTITY, (int)44975, (int)7969893));
        ILLUSIONER_SPAWN_EGG_ITEM = ITEMS.create("illusioner_spawn_egg", ItemHelper.createEgg(ILLUSIONER_ENTITY, (int)4073788, (int)0x959B9B));
        TANK_SPAWN_EGG_ITEM = ITEMS.create("tank_spawn_egg", ItemHelper.createEgg(TANK_ENTITY, (int)0xC1C1C1, (int)0x949494));
        ANGLER_TREASURE_BAG_ITEM = ITEMS.create("angler_treasure_bag", TreasureBag.angler());
        ELDER_GUARDIAN_TREASURE_BAG_ITEM = ITEMS.create("elder_guardian_treasure_bag", TreasureBag.elderGuardian());
        ENDER_DRAGON_TREASURE_BAG_ITEM = ITEMS.create("ender_dragon_treasure_bag", TreasureBag.enderDragon());
        PILLAGER_TREASURE_BAG_ITEM = ITEMS.create("pillager_treasure_bag", TreasureBag.pillager());
        UNDEAD_ARMY_TREASURE_BAG_ITEM = ITEMS.create("undead_army_treasure_bag", TreasureBag.undeadArmy());
        WARDEN_TREASURE_BAG_ITEM = ITEMS.create("warden_treasure_bag", TreasureBag.warden());
        WITHER_TREASURE_BAG_ITEM = ITEMS.create("wither_treasure_bag", TreasureBag.wither());
        ITEMS.create("advancement_bleeding", FakeItem::new);
        ITEMS.create("advancement_normal", FakeItem::new);
        ITEMS.create("advancement_expert", FakeItem::new);
        ITEMS.create("advancement_master", FakeItem::new);
        WITHER_POTION = POTIONS.create("wither", () -> new Potion(new MobEffectInstance[]{new MobEffectInstance(MobEffects.WITHER, TimeHelper.toTicks((double)40.0))}));
        WITHER_LONG_POTION = POTIONS.create("long_wither", () -> new Potion(new MobEffectInstance[]{new MobEffectInstance(MobEffects.WITHER, TimeHelper.toTicks((double)80.0))}));
        WITHER_STRONG_POTION = POTIONS.create("strong_wither", () -> new Potion(new MobEffectInstance[]{new MobEffectInstance(MobEffects.WITHER, TimeHelper.toTicks((double)20.0), 1)}));
        BLOOD_PARTICLE = PARTICLES.create("blood", () -> new SimpleParticleType(true){});
        PRIMARY_MODE_TAB = CREATIVE_MODE_TABS.create("primary", CreativeModeTabs.primary());
        ENDERIUM_ORE_PLACED_FEATURE = ResourceKey.create((ResourceKey)Registries.PLACED_FEATURE, (ResourceLocation)HELPER.getLocation("enderium_ore"));
        ENDERIUM_ORE_LARGE_PLACED_FEATURE = ResourceKey.create((ResourceKey)Registries.PLACED_FEATURE, (ResourceLocation)HELPER.getLocation("enderium_ore_large"));
        FRAGILE_END_STONE_PLACED_FEATURE = ResourceKey.create((ResourceKey)Registries.PLACED_FEATURE, (ResourceLocation)HELPER.getLocation("fragile_end_stone"));
        FRAGILE_END_STONE_LARGE_PLACED_FEATURE = ResourceKey.create((ResourceKey)Registries.PLACED_FEATURE, (ResourceLocation)HELPER.getLocation("fragile_end_stone_large"));
        INFESTED_END_STONE_PLACED_FEATURE = ResourceKey.create((ResourceKey)Registries.PLACED_FEATURE, (ResourceLocation)HELPER.getLocation("infested_end_stone"));
        BLEEDING_DAMAGE_SOURCE = ResourceKey.create((ResourceKey)Registries.DAMAGE_TYPE, (ResourceLocation)HELPER.getLocation("bleeding"));
        GAME_STAGE_ADVANCEMENT = new GameStageAdvancement();
        UNDEAD_ARMY_APPROACHING_SOUND = MajruszsDifficulty.register("undead_army.approaching");
        UNDEAD_ARMY_WAVE_STARTED_SOUND = MajruszsDifficulty.register("undead_army.wave_started");
        OnGameInitialized.listen(MajruszsDifficulty::addDefaultEmitters);
        HELPER.create(Custom.Attributes.class, attributes -> {
            attributes.register((EntityType)CERBERUS_ENTITY.get(), Cerberus.createAttributes());
            attributes.register((EntityType)CURSED_ARMOR_ENTITY.get(), CursedArmor.createAttributes());
            attributes.register((EntityType)CREEPERLING_ENTITY.get(), Creeperling.createChildAttributes());
            attributes.register((EntityType)GIANT_ENTITY.get(), Giant.createAttributes());
            attributes.register((EntityType)ILLUSIONER_ENTITY.get(), Illusioner.createAttributes().build());
            attributes.register((EntityType)TANK_ENTITY.get(), Tank.createAttributes());
        });
        HELPER.create(Custom.SpawnPlacements.class, spawnPlacements -> {
            spawnPlacements.register((EntityType)CERBERUS_ENTITY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
            spawnPlacements.register((EntityType)CURSED_ARMOR_ENTITY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CursedArmor::checkMonsterSpawnRules);
            spawnPlacements.register((EntityType)CREEPERLING_ENTITY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
            spawnPlacements.register((EntityType)GIANT_ENTITY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
            spawnPlacements.register((EntityType)ILLUSIONER_ENTITY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
            spawnPlacements.register((EntityType)TANK_ENTITY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        });
    }

    @OnlyIn(value=Dist.CLIENT)
    public static class Client {
        static {
            HELPER.create(Custom.ItemProperties.class, itemProperties -> itemProperties.register((Item)ENDERIUM_SHARD_LOCATOR_ITEM.get(), ResourceLocation.parse((String)"shard_distance"), EnderiumShardLocator.Client::getShardDistance));
            HELPER.create(Custom.ModelLayers.class, modelLayers -> {
                modelLayers.register(CerberusRenderer.LAYER, () -> ((ModelDef)CerberusModel.MODEL.get()).toLayerDefinition());
                modelLayers.register(CursedArmorRenderer.LAYER, () -> ((ModelDef)CursedArmorModel.MODEL.get()).toLayerDefinition());
                modelLayers.register(CursedArmorRenderer.INNER_ARMOR_LAYER, () -> LayerDefinition.create((MeshDefinition)CursedArmorModel.createMesh((CubeDeformation)new CubeDeformation(0.5f), (float)0.0f), (int)64, (int)32));
                modelLayers.register(CursedArmorRenderer.OUTER_ARMOR_LAYER, () -> LayerDefinition.create((MeshDefinition)CursedArmorModel.createMesh((CubeDeformation)new CubeDeformation(1.0f), (float)0.0f), (int)64, (int)32));
                modelLayers.register(CreeperlingRenderer.LAYER, () -> ((ModelDef)CreeperlingModel.MODEL.get()).toLayerDefinition());
                modelLayers.register(GiantRenderer.LAYER, () -> LayerDefinition.create((MeshDefinition)GiantModel.createMesh((CubeDeformation)CubeDeformation.NONE, (float)0.0f), (int)64, (int)64));
                modelLayers.register(TankRenderer.LAYER, () -> ((ModelDef)TankModel.MODEL.get()).toLayerDefinition());
            });
            HELPER.create(Custom.Particles.class, particles -> particles.register((ParticleType)BLOOD_PARTICLE.get(), BloodParticle.Factory::new));
            HELPER.create(Custom.PotionRecipe.class, recipes -> {
                recipes.register(WITHER_POTION, () -> Items.REDSTONE, WITHER_LONG_POTION);
                recipes.register(WITHER_POTION, () -> Items.GLOWSTONE_DUST, WITHER_STRONG_POTION);
            });
            HELPER.create(Custom.Renderers.class, renderers -> {
                renderers.register((EntityType)CERBERUS_ENTITY.get(), CerberusRenderer::new);
                renderers.register((EntityType)CURSED_ARMOR_ENTITY.get(), CursedArmorRenderer::new);
                renderers.register((EntityType)CREEPERLING_ENTITY.get(), CreeperlingRenderer::new);
                renderers.register((EntityType)GIANT_ENTITY.get(), GiantRenderer::new);
                renderers.register((EntityType)ILLUSIONER_ENTITY.get(), IllusionerRenderer::new);
                renderers.register((EntityType)TANK_ENTITY.get(), TankRenderer::new);
            });
        }
    }
}

