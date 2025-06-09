package net.justin.alexandritemod.common;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.*;

public class TransmogRegistry {
    private static final Map<Item, List<ResourceLocation>> TRANSMOGS = new HashMap<>();
    private static final Map<ResourceLocation, Float> TRANSMOG_INDEX = new HashMap<>();

    public static void registerTransmog(Item item, ResourceLocation transmog) {
        TRANSMOGS.computeIfAbsent(item, k -> new ArrayList<>()).add(transmog);
        TRANSMOG_INDEX.putIfAbsent(transmog, (float) TRANSMOG_INDEX.size());
    }

    public static List<ResourceLocation> getTransmogsForItem(Item item) {
        return TRANSMOGS.getOrDefault(item, Collections.emptyList());
    }

    public static float getTransmogIndex(ResourceLocation transmogId) {
        return TRANSMOG_INDEX.getOrDefault(transmogId, -1.0f);
    }

    public static void applyTransmog(ItemStack stack, ResourceLocation transmogId) {
        if (stack.isEmpty() || transmogId == null) return;
        float index = getTransmogIndex(transmogId);
        if (index >= 0) {
            stack.set(ModDataComponents.CUSTOM_MODEL_INDEX.get(), index);
        }
    }

    public static ResourceLocation getTransmogByIndex(Item item, int index) {
        List<ResourceLocation> list = getTransmogsForItem(item);
        return index >= 0 && index < list.size() ? list.get(index) : null;
    }
}

