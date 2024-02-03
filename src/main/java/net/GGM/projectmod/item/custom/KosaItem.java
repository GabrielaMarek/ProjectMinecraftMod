package net.GGM.projectmod.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class KosaItem extends Item {
    public KosaItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (world.isClientSide)
        {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
        double spawnDistance = 2.0;
        Vec3 direction = player.getLookAngle();
        Vec3 spawnPosition = player.position().add(direction.scale(spawnDistance));
        SnowGolem snowman = new SnowGolem(EntityType.SNOW_GOLEM, world);
        snowman.setPos(spawnPosition.x, spawnPosition.y, spawnPosition.z);
        world.addFreshEntity(snowman);
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
