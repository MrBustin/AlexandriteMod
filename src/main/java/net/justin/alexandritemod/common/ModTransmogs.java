package net.justin.alexandritemod.common;

import net.justin.alexandritemod.AlexandriteMod;
import net.justin.alexandritemod.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class ModTransmogs {
    public static void registerAll() {
        registerSwords();
        registerPickaxes();
        registerArmor();
    }

    private static void registerSwords() {
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "dark_blade"));
    }

    private static void registerPickaxes() {
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_PICKAXE.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "crystal_pickaxe"));
    }

    private static void registerArmor() {
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_HELMET.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "dragon_helmet"));
    }
}
