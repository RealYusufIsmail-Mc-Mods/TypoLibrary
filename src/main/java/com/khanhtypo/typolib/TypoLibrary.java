package com.khanhtypo.typolib;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TypoLibrary.MODID)
public class TypoLibrary {
    public static final Logger LOGGER = LogManager.getLogger("TypoLibrary");
    public static final String MODID = "typolib";
    public static final IEventBus FORGE_EVENT_BUS = MinecraftForge.EVENT_BUS;

    public TypoLibrary() {
        FORGE_EVENT_BUS.register(this);
        var testLogger = createLogger("typoLibTest");
        testLogger.info("TYPOLIB Loaded");
    }

    public static Logger createLogger(String modName) {
        return LogManager.getLogger("TypoLibrary/" + modName);
    }
}
