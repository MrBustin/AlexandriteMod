package net.justin.alexandritemod.common;

import com.mojang.serialization.Codec;
import net.justin.alexandritemod.AlexandriteMod;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS =
            DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE.key(), AlexandriteMod.MOD_ID);

    public static final RegistryObject<DataComponentType<Float>> CUSTOM_MODEL_INDEX =
            DATA_COMPONENTS.register("custom_model", () ->
                    DataComponentType.<Float>builder()
                            .persistent(Codec.FLOAT)
                            .build());

    public static void register(IEventBus eventBus) {
        DATA_COMPONENTS.register(eventBus);
    }
}
