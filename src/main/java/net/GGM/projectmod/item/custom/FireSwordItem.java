package net.GGM.projectmod.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FireSwordItem extends Item {

    public FireSwordItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!world.isClientSide) {
            Vec3 direction = player.getLookAngle();
            Fireball fireball = new SmallFireball(world, player, direction.x, direction.y, direction.z);

            fireball.setPos(player.getX(), player.getY() + player.getEyeHeight(), player.getZ());
            fireball.setDeltaMovement(direction.scale(1.5));

            world.addFreshEntity(fireball);
        }

        if (!player.isCreative()) {
            stack.hurtAndBreak(1, player, (entity) -> entity.broadcastBreakEvent(hand));
        }

        return InteractionResultHolder.success(stack);
    }


}
