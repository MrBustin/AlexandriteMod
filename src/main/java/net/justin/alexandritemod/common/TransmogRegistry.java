package net.justin.alexandritemod.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.*;

public class TransmogRegistry {
    private static final Map<Item, List<ResourceLocation>> TRANSMOGS = new HashMap<>();

    public static void registerTransmog(Item item, ResourceLocation transmog) {
        TRANSMOGS.computeIfAbsent(item, k -> new ArrayList<>()).add(transmog);
    }

    public static List<ResourceLocation> getTransmogsForItem(Item item) {
        return TRANSMOGS.getOrDefault(item, Collections.emptyList());
    }
}
