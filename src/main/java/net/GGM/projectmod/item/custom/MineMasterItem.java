package net.GGM.projectmod.item.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class MineMasterItem extends Item {
    public MineMasterItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for(int i =0; i <= positionClicked.getY() +64; i++){
                BlockState state = pContext.getLevel().getBlockState(positionClicked.below(i));

                if(isValuableBlock(state)) {
                    pContext.getLevel().destroyBlock(positionClicked.below(i), true);
                    ItemStack itemStack = getItemStackForBlock(state.getBlock());
                    player.getInventory().add(itemStack);

                    outputValuableCoordinates(positionClicked.below(i), player, state.getBlock());

                    int xpAmount = getXPForBlock(state.getBlock());
                    player.giveExperiencePoints(xpAmount);

                    foundBlock = true;
                    break;
                }
            }

            if(!foundBlock){
                player.sendSystemMessage(Component.literal("No ores here, keep looking!"));
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return super.useOn(pContext);
    }

    private void outputValuableCoordinates(BlockPos blockpos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at " + "(" + blockpos.getX() + ", " + blockpos.getY() + ", " + blockpos.getZ() + ")"));
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(Blocks.DIAMOND_ORE) || state.is(Blocks.IRON_ORE) || state.is(Blocks.COAL_ORE) || state.is(Blocks.EMERALD_ORE) || state.is(Blocks.COPPER_ORE) || state.is(Blocks.GOLD_ORE)
                || state.is(Blocks.LAPIS_ORE) || state.is(Blocks.REDSTONE_ORE);
    }

    private ItemStack getItemStackForBlock(Block block) {
        if (block == Blocks.DIAMOND_ORE) {
            return new ItemStack(Items.DIAMOND);
        } else if (block == Blocks.IRON_ORE) {
            return new ItemStack(Items.IRON_INGOT);
        } else if (block == Blocks.COAL_ORE) {
            return new ItemStack(Items.COAL);
        } else if (block == Blocks.EMERALD_ORE) {
            return new ItemStack(Items.EMERALD);
        } else if (block == Blocks.COPPER_ORE) {
            return new ItemStack(Items.COPPER_INGOT);
        } else if (block == Blocks.GOLD_ORE) {
            return new ItemStack(Items.GOLD_INGOT);
        } else if (block == Blocks.LAPIS_ORE) {
            return new ItemStack(Items.LAPIS_LAZULI);
        } else if (block == Blocks.REDSTONE_ORE) {
            return new ItemStack(Items.REDSTONE);
        }
        return ItemStack.EMPTY;
    }

    private int getXPForBlock(Block block) {
        Random random = new Random();
        if (block == Blocks.DIAMOND_ORE) {
            return random.nextInt(5) + 3;
        } else
            return random.nextInt(5) + 2;
    }
}