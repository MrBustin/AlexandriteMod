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
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "alliumblade"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "bamboo"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "baseball_bat"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "chainsword"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "crystalblade"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "crystaldoubleblade"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "cutlass"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "dark_blade"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "deaths_door"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "double_blade"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "douwswords_swousky"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "elite_skeleton_dagger"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "gladius"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "glorem_glipsum"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "godsword"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "honey_wand"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "idonas_sword"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "inflatedjustice"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "kindled_blade"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "moonshine"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "nightfall"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "plate_piercer"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "red_katana"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "refracted_blade"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "ring_blade"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "skallified_sword"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "soul_sword"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "soul_sword_blue"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "soul_sword_green"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "soulflame"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "swaxe"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "tarnished_blade"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "tribal_blade"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "velaras_greatsword"));
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_SWORD.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "wendarrs_greatsword"));

    }

    private static void registerPickaxes() {
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_PICKAXE.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "crystal_pickaxe"));
    }

    private static void registerArmor() {
        TransmogRegistry.registerTransmog(ModItems.ALEXANDRITE_HELMET.get(), ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "dragon_helmet"));
    }
}
