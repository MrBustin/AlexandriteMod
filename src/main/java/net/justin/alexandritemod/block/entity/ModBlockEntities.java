package net.justin.alexandritemod.block.entity;


import io.netty.util.Attribute;
import net.justin.alexandritemod.AlexandriteMod;
import net.justin.alexandritemod.block.ModBlocks;
import net.justin.alexandritemod.block.custom.AlexandriteFurnaceBlock;
import net.justin.alexandritemod.block.entity.custom.AlexandriteFurnaceBlockEntity;
import net.justin.alexandritemod.block.entity.custom.GrowthChamberBlockEntity;
import net.justin.alexandritemod.block.entity.custom.PedestalBlockEntity;
import net.justin.alexandritemod.block.entity.custom.TransmogTableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, AlexandriteMod.MOD_ID);


    public static final RegistryObject<BlockEntityType<PedestalBlockEntity>> PEDESTAL_BE =
            BLOCK_ENTITIES.register("pedestal_be", ()-> BlockEntityType.Builder.of(
                    PedestalBlockEntity::new, ModBlocks.PEDESTAL.get()).build(null));

    public static final RegistryObject<BlockEntityType<TransmogTableBlockEntity>> TRANSMOG_BE =
            BLOCK_ENTITIES.register("transmog_be", ()-> BlockEntityType.Builder.of(
                    TransmogTableBlockEntity::new, ModBlocks.TRANSMOG_TABLE_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<GrowthChamberBlockEntity>> GROWTH_CHAMBER_BE =
            BLOCK_ENTITIES.register("growth_chamber_be", ()-> BlockEntityType.Builder.of(
                    GrowthChamberBlockEntity::new, ModBlocks.GROWTH_CHAMBER.get()).build(null));

    public static final RegistryObject<BlockEntityType<AlexandriteFurnaceBlockEntity>> ALEXANDRITE_FURNACE_BE =
            BLOCK_ENTITIES.register("alexandrite_furnace_be", ()-> BlockEntityType.Builder.of(
                    AlexandriteFurnaceBlockEntity::new, ModBlocks.ALEXANDRITE_FURNACE.get()).build(null));

    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
            BLOCK_ENTITIES.register("mod_sign", ()-> BlockEntityType.Builder.of(
                    ModSignBlockEntity::new, ModBlocks.WALNUT_SIGN.get(), ModBlocks.WALNUT_WALL_SIGN.get()).build(null));

    public static final RegistryObject<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN =
            BLOCK_ENTITIES.register("mod_hanging_sign", ()-> BlockEntityType.Builder.of(
                    ModHangingSignBlockEntity::new, ModBlocks.WALNUT_HANGING_SIGN.get(), ModBlocks.WALNUT_WALL_HANGING_SIGN.get()).build(null));


    public static void register(IEventBus eventbus){
        BLOCK_ENTITIES.register(eventbus);
    }
}
