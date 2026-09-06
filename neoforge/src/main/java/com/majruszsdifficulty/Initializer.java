/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 * 
 * Could not load the following classes:
 *  com.majruszlibrary.modhelper.DataNeoForge
 *  net.neoforged.bus.api.IEventBus
 *  net.neoforged.fml.common.Mod
 */
package com.majruszsdifficulty;

import com.majruszlibrary.modhelper.DataNeoForge;
import com.majruszsdifficulty.MajruszsDifficulty;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(value="majruszsdifficulty")
public class Initializer {
    public Initializer(IEventBus bus) {
        DataNeoForge.MOD_EVENT_BUS = bus;
        MajruszsDifficulty.HELPER.register();
        net.neoforged.neoforge.common.NeoForge.EVENT_BUS.register( NeoForgeEvents.class );
        bus.addListener( NeoForgeEvents::onRegisterSpawnPlacements );
    }
}

