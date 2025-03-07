package net.justin.alexandritemod.item;

import net.justin.alexandritemod.AlexandriteMod;
import net.justin.alexandritemod.item.custom.ChiselItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AlexandriteMod.MOD_ID);

    public static final RegistryObject<Item> ALEXANDRITE = ITEMS.register("alexandrite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_ALEXANDRITE = ITEMS.register("raw_alexandrite",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHISEL = ITEMS.register("chisel",
            ()-> new ChiselItem(new Item.Properties().durability(32)));

    public static final RegistryObject<Item> KOHLRABI = ITEMS.register("kohlrabi",
            ()-> new Item(new Item.Properties().food(ModFoodProperties.KOHLRABI)));

    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
            ()-> new Item(new Item.Properties().food(ModFoodProperties.STRAWBERRY)));

    public static final RegistryObject<Item> ALEXANDRITE_SWORD = ITEMS.register("alexandrite_sword",
            ()-> new SwordItem(ModToolTiers.ALEXANDRITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.ALEXANDRITE, 3, -2.4f))));
    public static final RegistryObject<Item> ALEXANDRITE_PICKAXE = ITEMS.register("alexandrite_pickaxe",
            () -> new PickaxeItem(ModToolTiers.ALEXANDRITE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.ALEXANDRITE, 1, -2.8f))));
    public static final RegistryObject<Item> ALEXANDRITE_SHOVEL = ITEMS.register("alexandrite_shovel",
            () -> new ShovelItem(ModToolTiers.ALEXANDRITE, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.ALEXANDRITE, 1.5f, -3.0f))));
    public static final RegistryObject<Item> ALEXANDRITE_AXE = ITEMS.register("alexandrite_axe",
            () -> new AxeItem(ModToolTiers.ALEXANDRITE, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.ALEXANDRITE, 6, -3.2f))));
    public static final RegistryObject<Item> ALEXANDRITE_HOE = ITEMS.register("alexandrite_hoe",
            () -> new HoeItem(ModToolTiers.ALEXANDRITE, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.ALEXANDRITE, 0, -3.0f))));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
