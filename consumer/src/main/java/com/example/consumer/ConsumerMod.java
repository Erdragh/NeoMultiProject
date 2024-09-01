package com.example.consumer;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

@Mod(ConsumerMod.MODID)
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ConsumerMod {
    public static final String MODID = "consumer";
    public static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Consumer mod {} starting at {}", MODID, event.description());
    }
}
