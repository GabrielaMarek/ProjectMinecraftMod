package net.GGM.projectmod.item;

import net.GGM.projectmod.ProjectMod;
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
                        output.accept(ModItems.KOSA.get());
                        output.accept(ModItems.GLACIER_INGOT.get());
                        output.accept(ModItems.WSIZSANWICH.get());
                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
