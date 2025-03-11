package net.justin.alexandritemod.item;

import net.justin.alexandritemod.AlexandriteMod;
import net.justin.alexandritemod.block.ModBlocks;
import net.justin.alexandritemod.entity.ModEntities;
import net.justin.alexandritemod.item.custom.ChiselItem;
import net.justin.alexandritemod.item.custom.HammerItem;
import net.justin.alexandritemod.item.custom.ModArmorItem;
import net.justin.alexandritemod.sound.ModSounds;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AlexandriteMod.MOD_ID);

    //Items
    public static final RegistryObject<Item> ALEXANDRITE = ITEMS.register("alexandrite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_ALEXANDRITE = ITEMS.register("raw_alexandrite",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHISEL = ITEMS.register("chisel",
            ()-> new ChiselItem(new Item.Properties().durability(32)));

    public static final RegistryObject<Item> TRICERATOPS_SPAWN_EGG = ITEMS.register("triceratops_spawn_egg",
            ()-> new ForgeSpawnEggItem(ModEntities.TRICERATOPS, 0x53524b,0xdac741, new Item.Properties()));



    //Tools
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

    //Hammer
    public static final RegistryObject<Item> ALEXANDRITE_HAMMER = ITEMS.register("alexandrite_hammer",
            () -> new HammerItem(ModToolTiers.ALEXANDRITE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.ALEXANDRITE, 7, -3.5f))));
    public static final RegistryObject<Item> TOOL_ROD = ITEMS.register("tool_rod",
            ()-> new Item(new Item.Properties()));


    //Armor
    public static final RegistryObject<Item> ALEXANDRITE_HELMET = ITEMS.register("alexandrite_helmet",
            ()-> new ModArmorItem(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new  Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(40))));

    public static final RegistryObject<Item> ALEXANDRITE_CHESTPLATE = ITEMS.register("alexandrite_chestplate",
            ()-> new ArmorItem(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new  Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(40))));

    public static final RegistryObject<Item> ALEXANDRITE_LEGGINGS = ITEMS.register("alexandrite_leggings",
            ()-> new ArmorItem(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new  Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(40))));

    public static final RegistryObject<Item> ALEXANDRITE_BOOTS = ITEMS.register("alexandrite_boots",
            ()-> new ArmorItem(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new  Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(40))));

    public static final RegistryObject<Item> ALEXANDRITE_HORSE_ARMOR = ITEMS.register("alexandrite_horse_armor",
            ()-> new AnimalArmorItem(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, AnimalArmorItem.BodyType.EQUESTRIAN,
                    false, new Item.Properties().stacksTo(1)));

    //Bow
    public static final RegistryObject<Item> ALEXANDRITE_BOW = ITEMS.register("alexandrite_bow",
            ()-> new BowItem(new Item.Properties().durability(500)));

    //Music Discs
    public static final RegistryObject<Item> BAR_BRAWL_MUSIC_DISK = ITEMS.register("bar_brawl_music_disc",
            ()-> new Item((new Item.Properties().jukeboxPlayable(ModSounds.BAR_BRAWL_KEY).stacksTo(1))));

    public static final RegistryObject<Item> UP_MUSIC_DISK = ITEMS.register("up_music_disc",
            ()-> new Item((new Item.Properties().jukeboxPlayable(ModSounds.UP_KEY).stacksTo(1))));

    //Crops
    public static final RegistryObject<Item> KOHLRABI_SEEDS = ITEMS.register("kohlrabi_seeds",
            ()-> new ItemNameBlockItem(ModBlocks.KOHLRABI_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> STRAWBERRY_SEEDS = ITEMS.register("strawberry_seeds",
            ()-> new ItemNameBlockItem(ModBlocks.STRAWBERRY_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> KOHLRABI = ITEMS.register("kohlrabi",
            ()-> new Item(new Item.Properties().food(ModFoodProperties.KOHLRABI)));

    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
            ()-> new Item(new Item.Properties().food(ModFoodProperties.STRAWBERRY)));

    public static final RegistryObject<Item> BLUEBERRY = ITEMS.register("blueberry",
            ()-> new ItemNameBlockItem(ModBlocks.BLUEBERRY_BUSH.get(), new Item.Properties().food(ModFoodProperties.BLUEBERRY)));

    public static final RegistryObject<Item> HONEY_BERRIES = ITEMS.register("honey_berries",
            ()-> new ItemNameBlockItem(ModBlocks.HONEY_BERRY_BUSH.get(), new Item.Properties().food(ModFoodProperties.HONEY_BERRIES)));

    //Signs
    public static final RegistryObject<Item> WALNUT_SIGN = ITEMS.register("walnut_sign_item",
            ()-> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.WALNUT_SIGN.get(), ModBlocks.WALNUT_WALL_SIGN.get()));

    public static final RegistryObject<Item> WALNUT_HANGING_SIGN = ITEMS.register("walnut_hanging_sign_item",
            ()-> new HangingSignItem(ModBlocks.WALNUT_HANGING_SIGN.get(), ModBlocks.WALNUT_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
