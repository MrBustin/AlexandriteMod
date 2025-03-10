package net.justin.alexandritemod.worldgen;

import net.justin.alexandritemod.AlexandriteMod;
import net.justin.alexandritemod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?,?>> OVERWORLD_ALEXANDRITE_ORE_KEY = registerKey("alexandrite_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>> NETHER_ALEXANDRITE_ORE_KEY = registerKey("nether_alexandrite_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>> WALNUT_KEY = registerKey("walnut");
    public static final ResourceKey<ConfiguredFeature<?,?>> HONEY_BERRY_BUSH_KEY = registerKey("honey_berry_bush");
    public static final ResourceKey<ConfiguredFeature<?,?>> BLUEBERRY_BUSH_KEY = registerKey("blueberry_bush");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);

        List<OreConfiguration.TargetBlockState> overworldAlexandriteOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.ALEXANDRITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.ALEXANDRITE_DEEPSLATE_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_ALEXANDRITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldAlexandriteOres, 4));
        register(context, NETHER_ALEXANDRITE_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables,
                ModBlocks.ALEXANDRITE_NETHER_ORE.get().defaultBlockState(), 4));


        register(context, WALNUT_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.WALNUT_LOG.get()),
                new ForkingTrunkPlacer(4, 4, 3),

                BlockStateProvider.simple(ModBlocks.WALNUT_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), 3),

                new TwoLayersFeatureSize(1, 0, 2)).build());


        register(context, HONEY_BERRY_BUSH_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(
                                BlockStateProvider.simple(ModBlocks.HONEY_BERRY_BUSH.get().defaultBlockState().setValue(SweetBerryBushBlock.AGE, Integer.valueOf(3)))
                        ),
                        List.of(Blocks.GRASS_BLOCK)
                ));

        register(context, BLUEBERRY_BUSH_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(
                                BlockStateProvider.simple(ModBlocks.BLUEBERRY_BUSH.get().defaultBlockState().setValue(SweetBerryBushBlock.AGE, Integer.valueOf(3)))
                        ),
                        List.of(Blocks.GRASS_BLOCK)
                ));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
