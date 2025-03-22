package net.justin.alexandritemod.util;

import net.justin.alexandritemod.AlexandriteMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {


    public static class Blocks{
        public static final TagKey<Block> NEEDS_ALEXANDRITE_TOOL = createTag("needs_alexandrite_tool");
        public static final TagKey<Block> INCORRECT_FOR_ALEXANDRITE_TOOL = createTag("incorrect_for_alexandrite_tool");


        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, name));
        }

    }

    public static class Items{

        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
        public static final TagKey<Item> CROPS = createTag("crops");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, name));
        }
    }
}
