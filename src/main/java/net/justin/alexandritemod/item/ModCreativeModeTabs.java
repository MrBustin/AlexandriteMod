package net.justin.alexandritemod.item;

import net.justin.alexandritemod.AlexandriteMod;
import net.justin.alexandritemod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AlexandriteMod.MOD_ID);


    public static final RegistryObject<CreativeModeTab> ALEXANDRITE_ITEMS_TAB = CREATIVE_MODE_TABS.register("alexandrite_items_tab",
            ()-> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.ALEXANDRITE.get()))
                    .title(Component.translatable("creativetab.alexandritemod.alexandrite_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.ALEXANDRITE.get());
                        output.accept(ModItems.RAW_ALEXANDRITE.get());
                        output.accept(ModBlocks.ALEXANDRITE_BLOCK.get());
                        output.accept(ModBlocks.RAW_ALEXANDRITE_BLOCK.get());
                        output.accept(ModBlocks.ALEXANDRITE_ORE.get());
                        output.accept(ModBlocks.ALEXANDRITE_DEEPSLATE_ORE.get());
                        output.accept(ModBlocks.ALEXANDRITE_NETHER_ORE.get());
                        output.accept(ModBlocks.ALEXANDRITE_LAMP.get());
                        output.accept(ModBlocks.DIGITAL_DISPLAY_BLOCK.get());
                        output.accept(ModBlocks.MAGIC_BLOCK.get());
                        output.accept(ModBlocks.WALNUT_PLANKS.get());
                        output.accept(ModBlocks.WALNUT_WOOD.get());
                        output.accept(ModBlocks.WALNUT_LOG.get());
                        output.accept(ModBlocks.STRIPPED_WALNUT_WOOD.get());
                        output.accept(ModBlocks.STRIPPED_WALNUT_LOG.get());
                        output.accept(ModBlocks.WALNUT_LEAVES.get());
                        output.accept(ModBlocks.ALEXANDRITE_STAIRS.get());
                        output.accept(ModBlocks.ALEXANDRITE_SLAB.get());
                        output.accept(ModBlocks.ALEXANDRITE_FENCE.get());
                        output.accept(ModBlocks.ALEXANDRITE_FENCE_GATE.get());
                        output.accept(ModBlocks.ALEXANDRITE_WALL.get());
                        output.accept(ModBlocks.ALEXANDRITE_DOOR.get());
                        output.accept(ModBlocks.ALEXANDRITE_TRAPDOOR.get());
                        output.accept(ModBlocks.ALEXANDRITE_BUTTON.get());
                        output.accept(ModBlocks.WALNUT_STAIRS.get());
                        output.accept(ModBlocks.WALNUT_SLAB.get());
                        output.accept(ModBlocks.WALNUT_FENCE.get());
                        output.accept(ModBlocks.WALNUT_FENCE_GATE.get());
                        output.accept(ModBlocks.WALNUT_DOOR.get());
                        output.accept(ModBlocks.WALNUT_TRAPDOOR.get());
                        output.accept(ModBlocks.WALNUT_BUTTON.get());
                        output.accept(ModItems.WALNUT_SIGN.get());
                        output.accept(ModItems.WALNUT_HANGING_SIGN.get());
                        output.accept(ModBlocks.WALNUT_CHAIR.get());
                        output.accept(ModBlocks.OAK_CHAIR.get());
                        output.accept(ModBlocks.SPRUCE_CHAIR.get());
                        output.accept(ModBlocks.BIRCH_CHAIR.get());
                        output.accept(ModBlocks.JUNGLE_CHAIR.get());
                        output.accept(ModBlocks.ACACIA_CHAIR.get());
                        output.accept(ModBlocks.DARK_OAK_CHAIR.get());
                        output.accept(ModBlocks.MANGROVE_CHAIR.get());
                        output.accept(ModBlocks.CHERRY_CHAIR.get());
                        output.accept(ModBlocks.CRIMSON_CHAIR.get());
                        output.accept(ModBlocks.WARPED_CHAIR.get());
                        output.accept(ModBlocks.WALNUT_TABLE.get());
                        output.accept(ModBlocks.OAK_TABLE.get());
                        output.accept(ModBlocks.SPRUCE_TABLE.get());
                        output.accept(ModBlocks.BIRCH_TABLE.get());
                        output.accept(ModBlocks.JUNGLE_TABLE.get());
                        output.accept(ModBlocks.ACACIA_TABLE.get());
                        output.accept(ModBlocks.DARK_OAK_TABLE.get());
                        output.accept(ModBlocks.MANGROVE_TABLE.get());
                        output.accept(ModBlocks.CHERRY_TABLE.get());
                        output.accept(ModBlocks.CRIMSON_TABLE.get());
                        output.accept(ModBlocks.WARPED_TABLE.get());
                        output.accept(ModBlocks.PEDESTAL.get());
                        output.accept(ModBlocks.GROWTH_CHAMBER.get());
                        output.accept(ModBlocks.TRANSMOG_TABLE_BLOCK.get());
                        output.accept(ModBlocks.CHAMBER_FRAME.get());
                        output.accept(ModBlocks.ALEXANDRITE_FURNACE.get());



                        output.accept(ModItems.TOOL_ROD.get());
                        output.accept(ModItems.CHISEL.get());
                        output.accept(ModItems.TOMAHAWK.get());
                        output.accept(ModItems.TRICERATOPS_SPAWN_EGG.get());
                        output.accept(ModItems.BEAVER_SPAWN_EGG.get());
                        output.accept(ModItems.KOHLRABI.get());
                        output.accept(ModItems.STRAWBERRY.get());
                        output.accept(ModItems.HONEY_BERRIES.get());
                        output.accept(ModItems.BLUEBERRY.get());
                        output.accept(ModItems.KOHLRABI_SEEDS.get());
                        output.accept(ModItems.STRAWBERRY_SEEDS.get());
                        output.accept(ModBlocks.WALNUT_SAPLING.get());
                        output.accept(ModItems.BAR_BRAWL_MUSIC_DISK.get());
                        output.accept(ModItems.UP_MUSIC_DISK.get());


                        output.accept(ModItems.ALEXANDRITE_AXE.get());
                        output.accept(ModItems.ALEXANDRITE_PICKAXE.get());
                        output.accept(ModItems.ALEXANDRITE_HOE.get());
                        output.accept(ModItems.ALEXANDRITE_SWORD.get());
                        output.accept(ModItems.ALEXANDRITE_SHOVEL.get());
                        output.accept(ModItems.ALEXANDRITE_HAMMER.get());
                        output.accept(ModItems.ALEXANDRITE_BOW.get());

                        output.accept(ModItems.ALEXANDRITE_HELMET.get());
                        output.accept(ModItems.ALEXANDRITE_CHESTPLATE.get());
                        output.accept(ModItems.ALEXANDRITE_LEGGINGS.get());
                        output.accept(ModItems.ALEXANDRITE_BOOTS.get());
                        output.accept(ModItems.ALEXANDRITE_HORSE_ARMOR.get());



                    }).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

