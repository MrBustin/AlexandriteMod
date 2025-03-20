package net.justin.alexandritemod.block.entity.custom;

import net.justin.alexandritemod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AlexandriteFurnaceBlockEntity extends AbstractFurnaceBlockEntity {
    public AlexandriteFurnaceBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ALEXANDRITE_FURNACE_BE.get(), pPos, pBlockState, RecipeType.SMELTING);
    }


    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.alexandritemod.alexandrite_furnace");
    }

    @Override
    protected int getBurnDuration(ItemStack pFuel) {
        return super.getBurnDuration(pFuel)*2;
    }




    @Override
    protected AbstractContainerMenu createMenu(int pId, Inventory pPlayer) {
        return new FurnaceMenu(pId, pPlayer, this, this.dataAccess);
    }


}
