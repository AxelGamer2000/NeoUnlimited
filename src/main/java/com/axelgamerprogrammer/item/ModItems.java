package com.axelgamerprogrammer.item;

import com.axelgamerprogrammer.NeoUnlimited;
import com.axelgamerprogrammer.block.ModBlocks;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    // Create a Deferred Register to hold Items which will all be registered under the "neounlimited" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NeoUnlimited.MODID);

    // Creates a new BlockItem with the id "neounlimited:example_block", combining the namespace and path
    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", ModBlocks.EXAMPLE_BLOCK);

    // Creates a new food item with the id "neounlimited:example_id", nutrition 1 and saturation 2
    public static final DeferredItem<Item> WOODEN_GRASS_SCYTHE = ITEMS.registerSimpleItem("wooden_grass_scythe", new Item.Properties());

    public static final DeferredItem<Item> BEDROCK_DESTROYER = ITEMS.registerItem("bedrock_destroyer", BedrockDestroyerItem::new, new Item.Properties().stacksTo(8));

}
