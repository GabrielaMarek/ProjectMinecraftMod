package net.GGM.projectmod.item.custom;

import net.minecraft.network.chat.Component;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PortalStickItem extends Item {

    private static final int COOLDOWN_DURATION = 100;

    private final Map<Player, Long> lastUseTimes = new HashMap<>();

    public PortalStickItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide) {
            if (isOnCooldown(player)) {
                return InteractionResultHolder.fail(player.getItemInHand(hand));
            }

            HitResult result = getPlayerTarget(player, world);
            if (result.getType() == HitResult.Type.BLOCK) {
                BlockPos pos = ((BlockHitResult) result).getBlockPos().above();
                player.teleportTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);

                generateLightning(world, pos);

                setCooldown(player);

                return InteractionResultHolder.success(player.getItemInHand(hand));
            }
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    private HitResult getPlayerTarget(Player player, Level world) {
        double reachDistance = 150.0;
        return player.pick(reachDistance, 0.0F, false);
    }

    private void generateLightning(Level world, BlockPos pos) {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            double x = pos.getX() + random.nextDouble() * 10 - 5;
            double z = pos.getZ() + random.nextDouble() * 10 - 5;
            BlockPos strikePos = new BlockPos((int)x, pos.getY(), (int)z);
            LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, world);
            lightning.moveTo(strikePos.getX(), strikePos.getY(), strikePos.getZ());
            world.addFreshEntity(lightning);
        }
    }

    private boolean isOnCooldown(Player player) {
        if (lastUseTimes.containsKey(player)) {
            long lastUseTime = lastUseTimes.get(player);
            long currentTime = player.getCommandSenderWorld().getGameTime();
            long remainingCooldown = lastUseTime + COOLDOWN_DURATION - currentTime;
            if (remainingCooldown > 0) {
                int seconds = (int) (remainingCooldown / 20);
                player.sendSystemMessage(Component.literal("Portal Stick is on cooldown. Time remaining: " + seconds + " seconds"));
            }
            return currentTime < lastUseTime + COOLDOWN_DURATION;
        }
        return false;
    }


    private void setCooldown(Player player) {
        lastUseTimes.put(player, player.getCommandSenderWorld().getGameTime());
    }

}
