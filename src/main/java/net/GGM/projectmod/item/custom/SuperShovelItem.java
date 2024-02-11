package net.GGM.projectmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class SuperShovelItem extends Item {

    public SuperShovelItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level world = context.getLevel();
        if (player != null && world != null && !world.isClientSide()) {
            BlockPos pos = player.blockPosition(); // Player's position
            ServerLevel serverWorld = (ServerLevel) world;
            boolean destroyedBlocks = false;
            for (int x = pos.getX() - 2; x <= pos.getX() + 2; x++) {
                for (int y = pos.getY() - 2; y <= pos.getY() + 2; y++) {
                    for (int z = pos.getZ() - 2; z <= pos.getZ() + 2; z++) {
                        BlockPos blockPos = new BlockPos(x, y, z);
                        BlockState state = world.getBlockState(blockPos);
                        Block block = state.getBlock();
                        // Check if the block is dirt, sand, or gravel
                        if (block == Blocks.DIRT || block == Blocks.SAND || block == Blocks.GRAVEL || block == Blocks.GRASS_BLOCK || block == Blocks.CLAY) {
                            world.destroyBlock(blockPos, true);
                            destroyedBlocks = true;
                        }
                    }
                }
            }
            if (destroyedBlocks) {
                player.sendSystemMessage(Component.literal("Destroyed nearby dirt, sand, and gravel."));
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }
}
