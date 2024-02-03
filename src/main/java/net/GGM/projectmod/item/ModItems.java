package net.GGM.projectmod.item;

import net.GGM.projectmod.ProjectMod;
import net.GGM.projectmod.item.custom.FireSwordItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ProjectMod.MOD_ID);

    public static final RegistryObject<Item> MIECZ = ITEMS.register("miecz",
            () -> new FireSwordItem(new Item.Properties().durability(100)));

    public static final RegistryObject<Item> RAW_BRICK = ITEMS.register("raw_brick",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
