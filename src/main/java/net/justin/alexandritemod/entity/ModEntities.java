package net.justin.alexandritemod.entity;

import net.justin.alexandritemod.AlexandriteMod;
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




    public static void register(IEventBus eventBus){
        ENTITY_TYPE.register(eventBus);
    }
}
