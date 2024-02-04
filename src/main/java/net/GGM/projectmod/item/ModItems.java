package net.GGM.projectmod.item;

import net.GGM.projectmod.ProjectMod;
import net.GGM.projectmod.item.custom.FireSwordItem;

import net.GGM.projectmod.item.custom.KosaItem;


import net.minecraft.world.item.HoeItem;

import net.GGM.projectmod.item.custom.ModFoods;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ProjectMod.MOD_ID);

    public static final RegistryObject<Item> MIECZ = ITEMS.register("miecz",
            () -> new FireSwordItem(Tiers.DIAMOND, 7, 1.6F, new Item.Properties().durability(1561)));

    public static final RegistryObject<Item> RAW_BRICK = ITEMS.register("raw_brick",
            () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> KOSA = ITEMS.register("kosa",

            () -> new KosaItem(new Item.Properties().durability(80)));

    public static final RegistryObject<Item> GLACIER_INGOT = ITEMS.register("glacier_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GLACIER_SHARD = ITEMS.register("glacier_shard",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WSIZSANWICH = ITEMS.register("wsizsandwich",
            () -> new Item(new Item.Properties().food(ModFoods.WSIZSANDWICH)));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
