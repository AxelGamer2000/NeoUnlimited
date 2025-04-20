package com.axelgamerprogrammer.item;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Blocks;

public class BedrockDestroyerItem extends Item {
    public BedrockDestroyerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();

        if(context.getLevel().getBlockState(context.getClickedPos()).getBlock() == Blocks.BEDROCK) {
            context.getLevel().destroyBlock(context.getClickedPos(), false);
            context.getItemInHand().consume(1, context.getPlayer());
        }
        return super.useOn(context);
    }
}
