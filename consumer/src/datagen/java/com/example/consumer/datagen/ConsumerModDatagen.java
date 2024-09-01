package com.example.consumer.datagen;

import com.example.consumer.ConsumerMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = ConsumerMod.MODID)
public class ConsumerModDatagen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeClient(), new LanguageProvider(generator.getPackOutput(), ConsumerMod.MODID, "en_us") {
            @Override
            protected void addTranslations() {
                add(Items.APPLE, "Consumer Apple");
            }
        });
    }
}
