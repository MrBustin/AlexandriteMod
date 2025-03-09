package net.justin.alexandritemod.worldgen.tree;

import net.justin.alexandritemod.AlexandriteMod;
import net.justin.alexandritemod.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower WALNUT = new TreeGrower(AlexandriteMod.MOD_ID + "walnut",
            Optional.empty(), Optional.of(ModConfiguredFeatures.WALNUT_KEY), Optional.empty());
}
