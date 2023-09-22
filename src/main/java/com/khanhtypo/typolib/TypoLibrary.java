package com.khanhtypo.typolib;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("typolib")
public class TypoLibrary {
    public static final String MODID = "typolib";
    public static final IEventBus FORGE_EVENT_BUS = MinecraftForge.EVENT_BUS;
    public static final IEventBus MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();

    public TypoLibrary() {
        FORGE_EVENT_BUS.register(this);
    }
}
