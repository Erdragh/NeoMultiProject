package com.example.lib.datagen;

import com.example.lib.LibMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = LibMod.MODID)
public class LibModDatagen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeClient(), new LanguageProvider(generator.getPackOutput(), LibMod.MODID, "en_us") {
            @Override
            protected void addTranslations() {
                add(Items.POTATO, "Consumer Potato");
            }
        });
    }
}
