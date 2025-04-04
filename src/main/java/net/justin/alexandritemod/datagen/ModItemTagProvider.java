package net.justin.alexandritemod.datagen;
import net.justin.alexandritemod.AlexandriteMod;
import net.justin.alexandritemod.block.ModBlocks;
import net.justin.alexandritemod.item.ModItems;
import net.justin.alexandritemod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture,
                              CompletableFuture<TagLookup<Block>> lookupCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, lookupCompletableFuture, AlexandriteMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.ALEXANDRITE.get())
                .add(ModItems.RAW_ALEXANDRITE.get())
                .add(Items.COAL)
                .add(Items.STICK)
                .add(Items.COMPASS);

        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.ALEXANDRITE_HELMET.get())
                .add(ModItems.ALEXANDRITE_CHESTPLATE.get())
                .add(ModItems.ALEXANDRITE_LEGGINGS.get())
                .add(ModItems.ALEXANDRITE_BOOTS.get());

        tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.WALNUT_LOG.get().asItem())
                .add(ModBlocks.WALNUT_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_WALNUT_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_WALNUT_WOOD.get().asItem());

        tag(ItemTags.PLANKS)
                .add(ModBlocks.WALNUT_PLANKS.get().asItem());

        tag(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(ModItems.ALEXANDRITE.get());

        tag(ModTags.Items.CROPS)
                .add(ModItems.KOHLRABI.get())
                .add(ModItems.STRAWBERRY.get())
                .add(ModItems.BLUEBERRY.get())
                .add(ModItems.HONEY_BERRIES.get())
                .add(Items.WHEAT)
                .add(Items.CARROT)
                .add(Items.BEETROOT)
                .add(Items.POTATO)
                .add(Items.SWEET_BERRIES)
                .add(Items.CACTUS)
                .add(Items.SUGAR_CANE);

    }
}