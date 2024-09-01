package com.example.lib;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

@Mod(LibMod.MODID)
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class LibMod {
    public static final String MODID = "lib";
    public static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Lib mod {} starting at {}", MODID, event.description());
    }
}
