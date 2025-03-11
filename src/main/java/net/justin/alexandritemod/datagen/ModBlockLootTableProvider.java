package net.justin.alexandritemod.datagen;

import net.justin.alexandritemod.block.ModBlocks;
import net.justin.alexandritemod.block.custom.KohlrabiCropBlock;
import net.justin.alexandritemod.block.custom.StrawBerryCropBlock;
import net.justin.alexandritemod.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.ALEXANDRITE_BLOCK.get());
        dropSelf(ModBlocks.RAW_ALEXANDRITE_BLOCK.get());
        dropSelf(ModBlocks.MAGIC_BLOCK.get());

        this.add(ModBlocks.ALEXANDRITE_ORE.get(),
                block -> createOreDrop(ModBlocks.ALEXANDRITE_ORE.get(), ModItems.RAW_ALEXANDRITE.get()));
        this.add(ModBlocks.ALEXANDRITE_DEEPSLATE_ORE.get(),
                block -> createOreDrop(ModBlocks.ALEXANDRITE_DEEPSLATE_ORE.get(), ModItems.RAW_ALEXANDRITE.get()));
        this.add(ModBlocks.ALEXANDRITE_NETHER_ORE.get(),
                block -> createOreDrop(ModBlocks.ALEXANDRITE_NETHER_ORE.get(), ModItems.RAW_ALEXANDRITE.get()));

        dropSelf(ModBlocks.ALEXANDRITE_STAIRS.get());
        this.add(ModBlocks.ALEXANDRITE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.ALEXANDRITE_SLAB.get()));

        dropSelf(ModBlocks.ALEXANDRITE_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.ALEXANDRITE_BUTTON.get());
        dropSelf(ModBlocks.ALEXANDRITE_FENCE.get());
        dropSelf(ModBlocks.ALEXANDRITE_FENCE_GATE.get());
        dropSelf(ModBlocks.ALEXANDRITE_WALL.get());
        dropSelf(ModBlocks.ALEXANDRITE_TRAPDOOR.get());
        dropSelf(ModBlocks.ALEXANDRITE_LAMP.get());
        dropSelf(ModBlocks.PEDESTAL.get());

        this.add(ModBlocks.ALEXANDRITE_DOOR.get(),
                block -> createDoorTable(ModBlocks.ALEXANDRITE_DOOR.get()));


        LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.KOHLRABI_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(KohlrabiCropBlock.AGE, 6));

        this.add(ModBlocks.KOHLRABI_CROP.get(), this.createCropDrops(ModBlocks.KOHLRABI_CROP.get(),
                ModItems.KOHLRABI.get(), ModItems.KOHLRABI_SEEDS.get(), lootItemConditionBuilder));

        LootItemCondition.Builder lootItemConditionBuilder1 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.STRAWBERRY_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawBerryCropBlock.AGE, 5));

        this.add(ModBlocks.STRAWBERRY_CROP.get(), this.createCropDrops(ModBlocks.STRAWBERRY_CROP.get(),
                ModItems.STRAWBERRY.get(), ModItems.STRAWBERRY_SEEDS.get(), lootItemConditionBuilder1));


        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        this.add(ModBlocks.HONEY_BERRY_BUSH.get(), block -> this.applyExplosionDecay(
                block,LootTable.lootTable().withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.HONEY_BERRY_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3))
                                ).add(LootItem.lootTableItem(ModItems.HONEY_BERRIES.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.HONEY_BERRY_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2))
                                ).add(LootItem.lootTableItem(ModItems.HONEY_BERRIES.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )));


        this.add(ModBlocks.BLUEBERRY_BUSH.get(), block -> this.applyExplosionDecay(
                block,LootTable.lootTable().withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BLUEBERRY_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3))
                                ).add(LootItem.lootTableItem(ModItems.BLUEBERRY.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BLUEBERRY_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2))
                                ).add(LootItem.lootTableItem(ModItems.BLUEBERRY.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )));

        this.dropSelf(ModBlocks.WALNUT_SAPLING.get());
        this.dropSelf(ModBlocks.WALNUT_PLANKS.get());
        this.dropSelf(ModBlocks.WALNUT_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_WALNUT_WOOD.get());
        this.dropSelf(ModBlocks.WALNUT_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_WALNUT_LOG.get());

        this.add(ModBlocks.WALNUT_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.WALNUT_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));


        dropSelf(ModBlocks.WALNUT_STAIRS.get());
        dropSelf(ModBlocks.WALNUT_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.WALNUT_BUTTON.get());
        dropSelf(ModBlocks.WALNUT_FENCE.get());
        dropSelf(ModBlocks.WALNUT_FENCE_GATE.get());
        dropSelf(ModBlocks.WALNUT_TRAPDOOR.get());
        this.add(ModBlocks.WALNUT_DOOR.get(),
                block -> createDoorTable(ModBlocks.WALNUT_DOOR.get()));
        this.add(ModBlocks.WALNUT_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.WALNUT_SLAB.get()));

        dropSelf(ModBlocks.WALNUT_CHAIR.get());
        dropSelf(ModBlocks.OAK_CHAIR.get());
        dropSelf(ModBlocks.SPRUCE_CHAIR.get());
        dropSelf(ModBlocks.BIRCH_CHAIR.get());
        dropSelf(ModBlocks.JUNGLE_CHAIR.get());
        dropSelf(ModBlocks.ACACIA_CHAIR.get());
        dropSelf(ModBlocks.DARK_OAK_CHAIR.get());
        dropSelf(ModBlocks.MANGROVE_CHAIR.get());
        dropSelf(ModBlocks.CHERRY_CHAIR.get());
        dropSelf(ModBlocks.CRIMSON_CHAIR.get());
        dropSelf(ModBlocks.WARPED_CHAIR.get());


        dropSelf(ModBlocks.WALNUT_TABLE.get());
        dropSelf(ModBlocks.OAK_TABLE.get());
        dropSelf(ModBlocks.SPRUCE_TABLE.get());
        dropSelf(ModBlocks.BIRCH_TABLE.get());
        dropSelf(ModBlocks.JUNGLE_TABLE.get());
        dropSelf(ModBlocks.ACACIA_TABLE.get());
        dropSelf(ModBlocks.DARK_OAK_TABLE.get());
        dropSelf(ModBlocks.MANGROVE_TABLE.get());
        dropSelf(ModBlocks.CHERRY_TABLE.get());
        dropSelf(ModBlocks.CRIMSON_TABLE.get());
        dropSelf(ModBlocks.WARPED_TABLE.get());


        this.add(ModBlocks.WALNUT_SIGN.get(), block ->
                createSingleItemTable(ModItems.WALNUT_SIGN.get()));
        this.add(ModBlocks.WALNUT_WALL_SIGN.get(), block ->
                createSingleItemTable(ModItems.WALNUT_SIGN.get()));
        this.add(ModBlocks.WALNUT_HANGING_SIGN.get(), block ->
                createSingleItemTable(ModItems.WALNUT_HANGING_SIGN.get()));
        this.add(ModBlocks.WALNUT_WALL_HANGING_SIGN.get(), block ->
                createSingleItemTable(ModItems.WALNUT_HANGING_SIGN.get()));

    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock, this.applyExplosionDecay(
                        pBlock, LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}