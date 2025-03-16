package net.justin.alexandritemod.block;

import com.mojang.serialization.MapCodec;
import net.justin.alexandritemod.AlexandriteMod;
import net.justin.alexandritemod.block.custom.*;
import net.justin.alexandritemod.item.ModItems;
import net.justin.alexandritemod.sound.ModSounds;
import net.justin.alexandritemod.util.ModWoodTypes;
import net.justin.alexandritemod.worldgen.tree.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, AlexandriteMod.MOD_ID);




    // Blocks
    public static final RegistryObject<Block> ALEXANDRITE_BLOCK = registerBlock("alexandrite_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));

    public static final RegistryObject<Block> RAW_ALEXANDRITE_BLOCK = registerBlock("raw_alexandrite_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> ALEXANDRITE_LAMP = registerBlock("alexandrite_lamp",
            ()-> new AlexandriteLampBlock(BlockBehaviour.Properties.of().strength(3f)
                    .lightLevel(state -> state.getValue(AlexandriteLampBlock.CLICKED) ? 15 : 0)));

    public static final RegistryObject<Block> MAGIC_BLOCK = registerBlock("magic_block",
            () -> new MagicBlock(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().sound(ModSounds.MAGIC_BLOCK_SOUNDS)));

    public static final RegistryObject<Block> PEDESTAL = registerBlock("pedestal",
            ()-> new PedestalBlock(BlockBehaviour.Properties.of().noOcclusion()));

    public static final RegistryObject<Block> GROWTH_CHAMBER = registerBlock("growth_chamber",
            ()-> new GrowthChamberBlock(BlockBehaviour.Properties.of().strength(4f)));

    public static final RegistryObject<Block> CHAMBER_FRAME = registerBlock("chamber_frame",
            () -> new ChamberFrameBlock(BlockBehaviour.Properties.of()
                    .strength(50f,1200f).sound(SoundType.METAL).noOcclusion().instrument(NoteBlockInstrument.DIDGERIDOO)));


    // Ores
    public static final RegistryObject<Block> ALEXANDRITE_ORE = registerBlock("alexandrite_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> ALEXANDRITE_NETHER_ORE = registerBlock("alexandrite_nether_ore",
            () -> new DropExperienceBlock(UniformInt.of(1, 5), BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> ALEXANDRITE_DEEPSLATE_ORE = registerBlock("alexandrite_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 6), BlockBehaviour.Properties.of()
                    .strength(5f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));





    // Non-Block Blocks
    public static final RegistryObject<StairBlock> ALEXANDRITE_STAIRS = registerBlock("alexandrite_stairs",
            () -> new StairBlock(ModBlocks.ALEXANDRITE_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));
    public static final RegistryObject<SlabBlock> ALEXANDRITE_SLAB = registerBlock("alexandrite_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));

    public static final RegistryObject<PressurePlateBlock> ALEXANDRITE_PRESSURE_PLATE = registerBlock("alexandrite_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));
    public static final RegistryObject<ButtonBlock> ALEXANDRITE_BUTTON = registerBlock("alexandrite_button",
            () -> new ButtonBlock(BlockSetType.IRON,10, BlockBehaviour.Properties.of().strength(3f)
                    .requiresCorrectToolForDrops().noCollission()));

    public static final RegistryObject<FenceBlock> ALEXANDRITE_FENCE = registerBlock("alexandrite_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));
    public static final RegistryObject<FenceGateBlock> ALEXANDRITE_FENCE_GATE = registerBlock("alexandrite_fence_gate",
            () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));
    public static final RegistryObject<WallBlock> ALEXANDRITE_WALL = registerBlock("alexandrite_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));

    public static final RegistryObject<DoorBlock> ALEXANDRITE_DOOR = registerBlock("alexandrite_door",
            () -> new DoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<TrapDoorBlock> ALEXANDRITE_TRAPDOOR = registerBlock("alexandrite_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().noOcclusion()));




    public static final RegistryObject<StairBlock> WALNUT_STAIRS = registerBlock("walnut_stairs",
            () -> new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final RegistryObject<SlabBlock> WALNUT_SLAB = registerBlock("walnut_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));

     public static final RegistryObject<PressurePlateBlock> WALNUT_PRESSURE_PLATE = registerBlock("walnut_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
    public static final RegistryObject<ButtonBlock> WALNUT_BUTTON = registerBlock("walnut_button",
            () -> new ButtonBlock(BlockSetType.OAK,15, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));

    public static final RegistryObject<FenceBlock> WALNUT_FENCE = registerBlock("walnut_fence",
           () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final RegistryObject<FenceGateBlock> WALNUT_FENCE_GATE = registerBlock("walnut_fence_gate",
            () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));

    public static final RegistryObject<DoorBlock> WALNUT_DOOR = registerBlock("walnut_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<TrapDoorBlock> WALNUT_TRAPDOOR = registerBlock("walnut_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().noOcclusion()));







    // Crops
    public static final RegistryObject<Block> KOHLRABI_CROP = BLOCKS.register("kohlrabi_crop",
            () -> new KohlrabiCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));

    public static final RegistryObject<Block> STRAWBERRY_CROP = BLOCKS.register("strawberry_crop",
            () -> new StrawBerryCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));


    public static final RegistryObject<Block> HONEY_BERRY_BUSH = BLOCKS.register("honey_berry_bush",
            ()-> new HoneyBerryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));

    public static final RegistryObject<Block> BLUEBERRY_BUSH = BLOCKS.register("blueberry_bush",
            ()-> new BlueberryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));


    //Tree Blocks
    public static final RegistryObject<RotatedPillarBlock> WALNUT_LOG = registerBlock("walnut_log",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));

    public static final RegistryObject<RotatedPillarBlock> WALNUT_WOOD = registerBlock("walnut_wood",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_WALNUT_LOG = registerBlock("stripped_walnut_log",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_WALNUT_WOOD = registerBlock("stripped_walnut_wood",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));

    public static final RegistryObject<Block> WALNUT_PLANKS = registerBlock("walnut_planks",
            ()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> WALNUT_LEAVES = registerBlock("walnut_leaves",
            ()-> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final RegistryObject<Block> WALNUT_SAPLING = registerBlock("walnut_sapling",
            ()-> new SaplingBlock(ModTreeGrowers.WALNUT, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));



    public static final RegistryObject<Block> WALNUT_SIGN = registerBlock("walnut_sign",
        ()-> new ModStandingSignBlock(ModWoodTypes.WALNUT,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)));

    public static final RegistryObject<Block> WALNUT_WALL_SIGN = registerBlock("walnut_wall_sign",
            ()-> new ModWallSignBlock(ModWoodTypes.WALNUT,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)));



    public static final RegistryObject<Block> WALNUT_HANGING_SIGN = registerBlock("walnut_hanging_sign",
            ()-> new ModHangingSignBlock(ModWoodTypes.WALNUT,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)));

    public static final RegistryObject<Block> WALNUT_WALL_HANGING_SIGN = registerBlock("walnut_wall_hanging_sign",
            ()-> new ModWallHangingSignBlock(ModWoodTypes.WALNUT,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)));




    //Chairs
    public static final RegistryObject<Block> WALNUT_CHAIR = registerBlock("chair",
            ()-> new ChairBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> OAK_CHAIR = registerBlock("oak_chair",
            ()-> new ChairBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> SPRUCE_CHAIR = registerBlock("spruce_chair",
            ()-> new ChairBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> BIRCH_CHAIR = registerBlock("birch_chair",
            ()-> new ChairBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> JUNGLE_CHAIR = registerBlock("jungle_chair",
            ()-> new ChairBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> ACACIA_CHAIR = registerBlock("acacia_chair",
            ()-> new ChairBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> DARK_OAK_CHAIR = registerBlock("dark_oak_chair",
            ()-> new ChairBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> MANGROVE_CHAIR = registerBlock("mangrove_chair",
            ()-> new ChairBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CHERRY_CHAIR = registerBlock("cherry_chair",
            ()-> new ChairBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CRIMSON_CHAIR = registerBlock("crimson_chair",
            ()-> new ChairBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> WARPED_CHAIR = registerBlock("warped_chair",
            ()-> new ChairBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2f).sound(SoundType.WOOD)));

    //Tables
    public static final RegistryObject<Block> WALNUT_TABLE = registerBlock("table",
            ()-> new TableBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> OAK_TABLE = registerBlock("oak_table",
            ()-> new TableBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> SPRUCE_TABLE = registerBlock("spruce_table",
            ()-> new TableBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> BIRCH_TABLE = registerBlock("birch_table",
            ()-> new TableBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> JUNGLE_TABLE = registerBlock("jungle_table",
            ()-> new TableBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> ACACIA_TABLE = registerBlock("acacia_table",
            ()-> new TableBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> DARK_OAK_TABLE = registerBlock("dark_oak_table",
            ()-> new TableBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> MANGROVE_TABLE = registerBlock("mangrove_table",
            ()-> new TableBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CHERRY_TABLE = registerBlock("cherry_table",
            ()-> new TableBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CRIMSON_TABLE = registerBlock("crimson_table",
            ()-> new TableBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> WARPED_TABLE = registerBlock("warped_table",
            ()-> new TableBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2f).sound(SoundType.WOOD)));






    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name,()-> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);

    }



}