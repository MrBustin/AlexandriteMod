package net.justin.alexandritemod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties KOHLRABI = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25f)
            .effect(new MobEffectInstance(MobEffects.DIG_SPEED, 2400,2), 1.0f).alwaysEdible().build();

    public static final FoodProperties STRAWBERRY = new FoodProperties.Builder().nutrition(3).saturationModifier(0.6F).fast().build();

    public static final FoodProperties BLUEBERRY = new FoodProperties.Builder().nutrition(2).saturationModifier(0.15F).fast().build();

    public static final FoodProperties HONEY_BERRIES = new FoodProperties.Builder().nutrition(2).saturationModifier(0.15F).fast().build();

}
