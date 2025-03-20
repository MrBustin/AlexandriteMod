package net.justin.alexandritemod.recipe;

import net.justin.alexandritemod.AlexandriteMod;
import net.justin.alexandritemod.screen.custom.GrowthChamberScreen;
import net.minecraft.world.item.crafting.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes{
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, AlexandriteMod.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, AlexandriteMod.MOD_ID);


    public static final RegistryObject<RecipeSerializer<GrowthChamberRecipe>> GROWTH_CHAMBER_SERIALIZER =
        SERIALIZERS.register("growth_chamber", GrowthChamberRecipe.Serializer::new);
    public static final RegistryObject<RecipeType<GrowthChamberRecipe>> GROWTH_CHAMBER_TYPE =
            TYPES.register("growth_chamber", ()-> new RecipeType<GrowthChamberRecipe>() {
                @Override
                public String toString() {
                    return "growth_chamber";
                }
            });




    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }

}
