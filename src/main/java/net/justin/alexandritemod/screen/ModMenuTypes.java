package net.justin.alexandritemod.screen;

import net.justin.alexandritemod.AlexandriteMod;
import net.justin.alexandritemod.screen.custom.GrowthChamberMenu;
import net.justin.alexandritemod.screen.custom.TransmogTableMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, AlexandriteMod.MOD_ID);


    public static final RegistryObject<MenuType<GrowthChamberMenu>> GROWTH_CHAMBER_MENU =
            MENUS.register("growth_chamber_menu", () -> IForgeMenuType.create(GrowthChamberMenu::new));

    public static final RegistryObject<MenuType<TransmogTableMenu>> TRANSMOG_TABLE_MENU =
            MENUS.register("transmog_table_menu", () -> IForgeMenuType.create(TransmogTableMenu::new));


    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
