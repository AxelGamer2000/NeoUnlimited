package com.axelgamerprogrammer.creativeTab;

import com.axelgamerprogrammer.NeoUnlimited;
import com.axelgamerprogrammer.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "neounlimited" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NeoUnlimited.MODID);

    // Creates a creative tab with the id "neounlimited:example_tab" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.neounlimited")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ModItems.BEDROCK_DESTROYER.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ModItems.BEDROCK_DESTROYER.get());
                output.accept(ModItems.ENDER_PEARL_LAUNCHER.get());
            }).build());
}
