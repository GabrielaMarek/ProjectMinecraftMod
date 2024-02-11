package net.GGM.projectmod.item;

import net.GGM.projectmod.ProjectMod;
import net.GGM.projectmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ProjectMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> PROJECT_TAB = CREATIVE_MODE_TABS.register("project_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.WSIZSANWICH.get()))
                    .title(Component.translatable("creativetab.project_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.MIECZ.get());
                        output.accept(ModItems.RAW_BRICK.get());
                        output.accept(ModBlocks.FIRE_ORE.get());
                        output.accept(ModItems.KOSA.get());
                        output.accept(ModItems.GLACIER_INGOT.get());
                        output.accept(ModBlocks.GLACIER_ORE.get());
                        output.accept(ModBlocks.GLACIER_BLOCK.get());
                        output.accept(ModItems.GLACIER_SHARD.get());
                        output.accept(ModItems.WSIZSANWICH.get());
                        output.accept(ModItems.GLACIER_HELMET.get());
                        output.accept(ModItems.GLACIER_CHESTPLATE.get());
                        output.accept(ModItems.GLACIER_LEGGINS.get());
                        output.accept(ModItems.GLACIER_BOOTS.get());
                        output.accept(ModItems.MINE_MASTER.get());
                        output.accept(ModItems.PORTAL_STICK.get());
                        output.accept(ModItems.SUPER_SHOVEL.get());
                        output.accept(ModItems.FIRE_COAL.get());

                    })
                    .build());


    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
