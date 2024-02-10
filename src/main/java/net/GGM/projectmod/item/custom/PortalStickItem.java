package net.GGM.projectmod.item.custom;

import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;

import java.util.Random;

public class PortalStickItem extends Item {
    public PortalStickItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand){

        if (!world.isClientSide) {
            HitResult result = getPlayerTarget(player, world);
            if (result.getType() == HitResult.Type.BLOCK) {
                BlockPos pos = ((BlockHitResult) result).getBlockPos().above();
                player.teleportTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);

                // Generate lightning strikes nearby the player's position
                generateLightning(world, pos);

                return InteractionResultHolder.success(player.getItemInHand(hand));
            }
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    private HitResult getPlayerTarget(Player player, Level world) {
        double reachDistance = 150.0; // Adjust the reach distance to 50 blocks
        return player.pick(reachDistance, 0.0F, false);
    }

    private void generateLightning(Level world, BlockPos pos) {
        Random random = new Random();
        for (int i = 0; i < 3; i++) { // Generate 3 lightning strikes nearby
            double x = pos.getX() + random.nextDouble() * 10 - 5;
            double z = pos.getZ() + random.nextDouble() * 10 - 5;
            BlockPos strikePos = new BlockPos((int)x, pos.getY(), (int)z); // Cast doubles to integers
            LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, world);
            lightning.moveTo(strikePos.getX(), strikePos.getY(), strikePos.getZ());
            world.addFreshEntity(lightning);
        }
    }
}

