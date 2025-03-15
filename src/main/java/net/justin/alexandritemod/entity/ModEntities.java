package net.justin.alexandritemod.entity;

import net.justin.alexandritemod.AlexandriteMod;
import net.justin.alexandritemod.entity.custom.BeaverEntity;
import net.justin.alexandritemod.entity.custom.ChairEntity;
import net.justin.alexandritemod.entity.custom.TomahawkProjectileEntity;
import net.justin.alexandritemod.entity.custom.TriceratopsEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AlexandriteMod.MOD_ID);

    public static final RegistryObject<EntityType<TriceratopsEntity>> TRICERATOPS =
            ENTITY_TYPE.register("triceratops", () -> EntityType.Builder.of(TriceratopsEntity::new, MobCategory.CREATURE)
                    .sized(1.5f,1.5f).build("triceatops"));

    public static final RegistryObject<EntityType<BeaverEntity>> BEAVER =
            ENTITY_TYPE.register("beaver", () -> EntityType.Builder.of(BeaverEntity::new, MobCategory.CREATURE)
                    .sized(1f,1f).build("beaver"));

    public static final RegistryObject<EntityType<ChairEntity>> CHAIR =
            ENTITY_TYPE.register("chair_entity", () -> EntityType.Builder.of(ChairEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("chair_entity"));

    public static final RegistryObject<EntityType<TomahawkProjectileEntity>> TOMAHAWK =
            ENTITY_TYPE.register("tomahawk", () -> EntityType.Builder.<TomahawkProjectileEntity>of(TomahawkProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 1.15f).build("tomahawk"));




    public static void register(IEventBus eventBus){
        ENTITY_TYPE.register(eventBus);
    }
}
