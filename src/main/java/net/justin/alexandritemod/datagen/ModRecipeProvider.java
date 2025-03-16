package net.justin.alexandritemod.datagen;

import net.justin.alexandritemod.AlexandriteMod;
import net.justin.alexandritemod.block.ModBlocks;
import net.justin.alexandritemod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);

        if (!ModBlocks.WALNUT_PLANKS.isPresent()) {
            AlexandriteMod.LOGGER.error("Walnut Planks registry object is missing!");
        }
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        List<ItemLike> ALEXANDRITE_SMELTABLES = List.of(ModItems.RAW_ALEXANDRITE.get(),
                ModBlocks.ALEXANDRITE_ORE.get(), ModBlocks.ALEXANDRITE_DEEPSLATE_ORE.get(), ModBlocks.ALEXANDRITE_NETHER_ORE.get(), ModBlocks.RAW_ALEXANDRITE_BLOCK.get()

        );


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModBlocks.CHAMBER_FRAME.get())
                        .pattern("AAA")
                        .pattern("TST")
                        .pattern("AAA")
                        .define('A', ModBlocks.ALEXANDRITE_BLOCK.get())
                        .define('T', ModItems.TOOL_ROD.get())
                        .define('S', Items.NETHER_STAR);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModBlocks.GROWTH_CHAMBER.get())
                        .pattern("BBB")
                        .pattern("WDW")
                        .pattern("BBB")
                        .define('B', Blocks.BONE_BLOCK)
                        .define('W', Items.WATER_BUCKET)
                        .define('D', Items.DRAGON_EGG)
        .unlockedBy(getHasName(ModBlocks.CHAMBER_FRAME.get()), has(ModBlocks.CHAMBER_FRAME.get())).save(pRecipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.TOOL_ROD.get())
                .pattern("I")
                .pattern("I")
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.ALEXANDRITE_SWORD.get())
                .pattern("A")
                .pattern("A")
                .pattern("S")
                .define('A', ModItems.ALEXANDRITE.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.ALEXANDRITE_SHOVEL.get())
                .pattern("A")
                .pattern("S")
                .pattern("S")
                .define('A', ModItems.ALEXANDRITE.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.ALEXANDRITE_AXE.get())
                .pattern("AA ")
                .pattern("AS ")
                .pattern(" S ")
                .define('A', ModItems.ALEXANDRITE.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.ALEXANDRITE_PICKAXE.get())
                .pattern("AAA")
                .pattern(" S ")
                .pattern(" S ")
                .define('A', ModItems.ALEXANDRITE.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.ALEXANDRITE_HOE.get())
                .pattern("AA ")
                .pattern(" S ")
                .pattern(" S ")
                .define('A', ModItems.ALEXANDRITE.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.ALEXANDRITE_HAMMER.get())
                .pattern("BAB")
                .pattern("BSB")
                .pattern(" S ")
                .define('A', ModItems.ALEXANDRITE.get())
                .define('B', ModBlocks.ALEXANDRITE_BLOCK.get())
                .define('S', ModItems.TOOL_ROD.get())
                .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get())).save(pRecipeOutput);









        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ALEXANDRITE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.ALEXANDRITE.get())
                .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MAGIC_BLOCK.get())
                .pattern("AAA")
                .pattern("ASA")
                .pattern("AAA")
                .define('A', ModBlocks.ALEXANDRITE_BLOCK.get())
                .define('S', Items.NETHER_STAR)
                .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_ALEXANDRITE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.RAW_ALEXANDRITE.get())
                .unlockedBy(getHasName(ModItems.RAW_ALEXANDRITE.get()), has(ModItems.RAW_ALEXANDRITE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.CHISEL.get())
                .pattern("  A")
                .pattern(" S ")
                .pattern("S  ")
                .define('A', ModItems.ALEXANDRITE.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.ALEXANDRITE_BOW.get())
                .pattern(" AS")
                .pattern("A S")
                .pattern(" AS")
                .define('A', ModItems.ALEXANDRITE.get())
                .define('S', Items.STRING)
                .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get())).save(pRecipeOutput);

        //Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.ALEXANDRITE_HELMET.get())
                .pattern("AAA")
                .pattern("A A")
                .define('A', ModItems.ALEXANDRITE.get())
                .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.ALEXANDRITE_CHESTPLATE.get())
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.ALEXANDRITE.get())
                .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.ALEXANDRITE_LEGGINGS.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.ALEXANDRITE.get())
                .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.ALEXANDRITE_BOOTS.get())
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.ALEXANDRITE.get())
                .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get())).save(pRecipeOutput);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ALEXANDRITE.get(), 9)
                .requires(ModBlocks.ALEXANDRITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ALEXANDRITE_BLOCK.get()), has(ModBlocks.ALEXANDRITE_BLOCK.get())).save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_ALEXANDRITE.get(), 9)
                .requires(ModBlocks.RAW_ALEXANDRITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.RAW_ALEXANDRITE_BLOCK.get()), has(ModBlocks.RAW_ALEXANDRITE_BLOCK.get())).save(pRecipeOutput);

       // ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ALEXANDRITE.get(), 32)
       //         .requires(ModBlocks.MAGIC_BLOCK.get())
       //         .unlockedBy(getHasName(ModBlocks.ALEXANDRITE_BLOCK.get()), has(ModBlocks.ALEXANDRITE_BLOCK.get()))
       //         .save(pRecipeOutput, AlexandriteMod.MOD_ID + ":alexandrite_from_magic_block");

        oreSmelting(pRecipeOutput, ALEXANDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.ALEXANDRITE.get(), 0.25f, 200, "alexandrite");
        oreBlasting(pRecipeOutput, ALEXANDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.ALEXANDRITE.get(), 0.25f, 100, "alexandrite");


        stairBuilder(ModBlocks.ALEXANDRITE_STAIRS.get(), Ingredient.of(ModItems.ALEXANDRITE.get())).group("alexandrite")
                .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get())).save(pRecipeOutput);
        slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ALEXANDRITE_SLAB.get(), ModItems.ALEXANDRITE.get());

        buttonBuilder(ModBlocks.ALEXANDRITE_BUTTON.get(), Ingredient.of(ModItems.ALEXANDRITE.get())).group("alexandrite")
                .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get())).save(pRecipeOutput);
        pressurePlate(pRecipeOutput, ModBlocks.ALEXANDRITE_PRESSURE_PLATE.get(), ModItems.ALEXANDRITE.get());

        fenceBuilder(ModBlocks.ALEXANDRITE_FENCE.get(), Ingredient.of(ModItems.ALEXANDRITE.get())).group("alexandrite")
                .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get())).save(pRecipeOutput);
        fenceGateBuilder(ModBlocks.ALEXANDRITE_FENCE_GATE.get(), Ingredient.of(ModItems.ALEXANDRITE.get())).group("alexandrite")
                .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get())).save(pRecipeOutput);
        wall(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ALEXANDRITE_WALL.get(), ModItems.ALEXANDRITE.get());

        doorBuilder(ModBlocks.ALEXANDRITE_DOOR.get(), Ingredient.of(ModItems.ALEXANDRITE.get())).group("alexandrite")
                .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get())).save(pRecipeOutput);
        trapdoorBuilder(ModBlocks.ALEXANDRITE_TRAPDOOR.get(), Ingredient.of(ModItems.ALEXANDRITE.get())).group("alexandrite")
                .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get())).save(pRecipeOutput);



        stairBuilder(ModBlocks.WALNUT_STAIRS.get(), Ingredient.of(ModBlocks.WALNUT_PLANKS.get())).group("walnut")
                .unlockedBy(getHasName(ModBlocks.WALNUT_PLANKS.get()), has(ModBlocks.WALNUT_PLANKS.get())).save(pRecipeOutput);

        slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WALNUT_SLAB.get(), ModBlocks.WALNUT_PLANKS.get());

         buttonBuilder(ModBlocks.WALNUT_BUTTON.get(), Ingredient.of(ModBlocks.WALNUT_PLANKS.get())).group("walnut")
                .unlockedBy(getHasName(ModBlocks.WALNUT_PLANKS.get()), has(ModBlocks.WALNUT_PLANKS.get())).save(pRecipeOutput);

        pressurePlate(pRecipeOutput, ModBlocks.WALNUT_PRESSURE_PLATE.get(), ModBlocks.WALNUT_PLANKS.get());

        fenceBuilder(ModBlocks.WALNUT_FENCE.get(), Ingredient.of(ModBlocks.WALNUT_PLANKS.get())).group("walnut")
                .unlockedBy(getHasName(ModBlocks.WALNUT_PLANKS.get()), has(ModBlocks.WALNUT_PLANKS.get())).save(pRecipeOutput);

        fenceGateBuilder(ModBlocks.WALNUT_FENCE_GATE.get(), Ingredient.of(ModBlocks.WALNUT_PLANKS.get())).group("walnut")
                .unlockedBy(getHasName(ModBlocks.WALNUT_PLANKS.get()), has(ModBlocks.WALNUT_PLANKS.get())).save(pRecipeOutput);

        doorBuilder(ModBlocks.WALNUT_DOOR.get(), Ingredient.of(ModBlocks.WALNUT_PLANKS.get())).group("walnut")
                .unlockedBy(getHasName(ModBlocks.WALNUT_PLANKS.get()), has(ModBlocks.WALNUT_PLANKS.get())).save(pRecipeOutput);

        trapdoorBuilder(ModBlocks.WALNUT_TRAPDOOR.get(), Ingredient.of(ModBlocks.WALNUT_PLANKS.get())).group("walnut")
                .unlockedBy(getHasName(ModBlocks.WALNUT_PLANKS.get()), has(ModBlocks.WALNUT_PLANKS.get())).save(pRecipeOutput);

        //Chairs
        chairBuilder(ModBlocks.WALNUT_CHAIR.get(), Ingredient.of(ModBlocks.WALNUT_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.WALNUT_PLANKS.get()), has(ModBlocks.WALNUT_PLANKS.get())).save(pRecipeOutput);

        chairBuilder(ModBlocks.OAK_CHAIR.get(), Ingredient.of(Blocks.OAK_PLANKS))
                .unlockedBy(getHasName(Blocks.OAK_PLANKS), has(Blocks.OAK_PLANKS)).save(pRecipeOutput);

        chairBuilder(ModBlocks.SPRUCE_CHAIR.get(), Ingredient.of(Blocks.SPRUCE_PLANKS))
                .unlockedBy(getHasName(Blocks.SPRUCE_PLANKS), has(Blocks.SPRUCE_PLANKS)).save(pRecipeOutput);

        chairBuilder(ModBlocks.BIRCH_CHAIR.get(), Ingredient.of(Blocks.BIRCH_PLANKS))
                .unlockedBy(getHasName(Blocks.BIRCH_PLANKS), has(Blocks.BIRCH_PLANKS)).save(pRecipeOutput);

        chairBuilder(ModBlocks.JUNGLE_CHAIR.get(), Ingredient.of(Blocks.JUNGLE_PLANKS))
                .unlockedBy(getHasName(Blocks.JUNGLE_PLANKS), has(Blocks.JUNGLE_PLANKS)).save(pRecipeOutput);

        chairBuilder(ModBlocks.ACACIA_CHAIR.get(), Ingredient.of(Blocks.ACACIA_PLANKS))
                .unlockedBy(getHasName(Blocks.ACACIA_PLANKS), has(Blocks.ACACIA_PLANKS)).save(pRecipeOutput);

        chairBuilder(ModBlocks.DARK_OAK_CHAIR.get(), Ingredient.of(Blocks.DARK_OAK_PLANKS))
                .unlockedBy(getHasName(Blocks.DARK_OAK_PLANKS), has(Blocks.DARK_OAK_PLANKS)).save(pRecipeOutput);

        chairBuilder(ModBlocks.MANGROVE_CHAIR.get(), Ingredient.of(Blocks.MANGROVE_PLANKS))
                .unlockedBy(getHasName(Blocks.MANGROVE_PLANKS), has(Blocks.MANGROVE_PLANKS)).save(pRecipeOutput);

        chairBuilder(ModBlocks.CHERRY_CHAIR.get(), Ingredient.of(Blocks.CHERRY_PLANKS))
                .unlockedBy(getHasName(Blocks.CHERRY_PLANKS), has(Blocks.CHERRY_PLANKS)).save(pRecipeOutput);

        chairBuilder(ModBlocks.CRIMSON_CHAIR.get(), Ingredient.of(Blocks.CRIMSON_PLANKS))
                .unlockedBy(getHasName(Blocks.CRIMSON_PLANKS), has(Blocks.CRIMSON_PLANKS)).save(pRecipeOutput);

        chairBuilder(ModBlocks.WARPED_CHAIR.get(), Ingredient.of(Blocks.WARPED_PLANKS))
                .unlockedBy(getHasName(Blocks.WARPED_PLANKS), has(Blocks.WARPED_PLANKS)).save(pRecipeOutput);



        //Tables
        tableBuilder(ModBlocks.WALNUT_TABLE.get(), Ingredient.of(ModBlocks.WALNUT_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.WALNUT_PLANKS.get()), has(ModBlocks.WALNUT_PLANKS.get())).save(pRecipeOutput);

        tableBuilder(ModBlocks.OAK_TABLE.get(), Ingredient.of(Blocks.OAK_PLANKS))
                .unlockedBy(getHasName(Blocks.OAK_PLANKS), has(Blocks.OAK_PLANKS)).save(pRecipeOutput);

        tableBuilder(ModBlocks.SPRUCE_TABLE.get(), Ingredient.of(Blocks.SPRUCE_PLANKS))
                .unlockedBy(getHasName(Blocks.SPRUCE_PLANKS), has(Blocks.SPRUCE_PLANKS)).save(pRecipeOutput);

        tableBuilder(ModBlocks.BIRCH_TABLE.get(), Ingredient.of(Blocks.BIRCH_PLANKS))
                .unlockedBy(getHasName(Blocks.BIRCH_PLANKS), has(Blocks.BIRCH_PLANKS)).save(pRecipeOutput);

        tableBuilder(ModBlocks.JUNGLE_TABLE.get(), Ingredient.of(Blocks.JUNGLE_PLANKS))
                .unlockedBy(getHasName(Blocks.JUNGLE_PLANKS), has(Blocks.JUNGLE_PLANKS)).save(pRecipeOutput);

        tableBuilder(ModBlocks.ACACIA_TABLE.get(), Ingredient.of(Blocks.ACACIA_PLANKS))
                .unlockedBy(getHasName(Blocks.ACACIA_PLANKS), has(Blocks.ACACIA_PLANKS)).save(pRecipeOutput);

        tableBuilder(ModBlocks.DARK_OAK_TABLE.get(), Ingredient.of(Blocks.DARK_OAK_PLANKS))
                .unlockedBy(getHasName(Blocks.DARK_OAK_PLANKS), has(Blocks.DARK_OAK_PLANKS)).save(pRecipeOutput);

        tableBuilder(ModBlocks.MANGROVE_TABLE.get(), Ingredient.of(Blocks.MANGROVE_PLANKS))
                .unlockedBy(getHasName(Blocks.MANGROVE_PLANKS), has(Blocks.MANGROVE_PLANKS)).save(pRecipeOutput);

        tableBuilder(ModBlocks.CHERRY_TABLE.get(), Ingredient.of(Blocks.CHERRY_PLANKS))
                .unlockedBy(getHasName(Blocks.CHERRY_PLANKS), has(Blocks.CHERRY_PLANKS)).save(pRecipeOutput);

        tableBuilder(ModBlocks.CRIMSON_TABLE.get(), Ingredient.of(Blocks.CRIMSON_PLANKS))
                .unlockedBy(getHasName(Blocks.CRIMSON_PLANKS), has(Blocks.CRIMSON_PLANKS)).save(pRecipeOutput);

        tableBuilder(ModBlocks.WARPED_TABLE.get(), Ingredient.of(Blocks.WARPED_PLANKS))
                .unlockedBy(getHasName(Blocks.WARPED_PLANKS), has(Blocks.WARPED_PLANKS)).save(pRecipeOutput);


    }

    protected static RecipeBuilder tableBuilder(ItemLike pTable, Ingredient pMaterial) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, pTable, 1)
                .define('#', pMaterial)
                .define('S', Items.STICK)
                .pattern("###")
                .pattern("# #")
                .pattern("S S");
    }

    protected static RecipeBuilder chairBuilder(ItemLike pChair, Ingredient pMaterial) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, pChair, 1)
                .define('#', pMaterial)
                .pattern("#  ")
                .pattern("###")
                .pattern("# #");
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, AlexandriteMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}