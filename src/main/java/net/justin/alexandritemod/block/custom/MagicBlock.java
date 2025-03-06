package net.justin.alexandritemod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class MagicBlock extends Block {
    public MagicBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {

        pLevel.playSound(pPlayer,pPos, SoundEvents.AMETHYST_CLUSTER_PLACE, SoundSource.BLOCKS, 1f,1f);
        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {


        if(pEntity instanceof ItemEntity itemEntity){
            if(itemEntity.getItem().getItem() == Items.WHITE_CONCRETE_POWDER) {
                itemEntity.setItem(new ItemStack( Items.WHITE_CONCRETE, itemEntity.getItem().getCount()));

            }
        }

        if(pEntity instanceof ItemEntity itemEntity){
            if(itemEntity.getItem().getItem() == Items.ORANGE_CONCRETE_POWDER) {
                itemEntity.setItem(new ItemStack( Items.ORANGE_CONCRETE, itemEntity.getItem().getCount()));

            }
        }

        if(pEntity instanceof ItemEntity itemEntity){
            if(itemEntity.getItem().getItem() == Items.MAGENTA_CONCRETE_POWDER) {
                itemEntity.setItem(new ItemStack( Items.MAGENTA_CONCRETE, itemEntity.getItem().getCount()));

            }
        }

        if(pEntity instanceof ItemEntity itemEntity){
            if(itemEntity.getItem().getItem() == Items.LIGHT_BLUE_CONCRETE_POWDER) {
                itemEntity.setItem(new ItemStack( Items.LIGHT_BLUE_CONCRETE, itemEntity.getItem().getCount()));

            }
        }

        if(pEntity instanceof ItemEntity itemEntity){
            if(itemEntity.getItem().getItem() == Items.YELLOW_CONCRETE_POWDER) {
                itemEntity.setItem(new ItemStack( Items.YELLOW_CONCRETE, itemEntity.getItem().getCount()));

            }
        }

        if(pEntity instanceof ItemEntity itemEntity){
            if(itemEntity.getItem().getItem() == Items.LIME_CONCRETE_POWDER) {
                itemEntity.setItem(new ItemStack( Items.LIME_CONCRETE, itemEntity.getItem().getCount()));

            }
        }

        if(pEntity instanceof ItemEntity itemEntity){
            if(itemEntity.getItem().getItem() == Items.PINK_CONCRETE_POWDER) {
                itemEntity.setItem(new ItemStack( Items.PINK_CONCRETE, itemEntity.getItem().getCount()));

            }
        }

        if(pEntity instanceof ItemEntity itemEntity){
            if(itemEntity.getItem().getItem() == Items.GRAY_CONCRETE_POWDER) {
                itemEntity.setItem(new ItemStack( Items.GRAY_CONCRETE, itemEntity.getItem().getCount()));

            }
        }

        if(pEntity instanceof ItemEntity itemEntity){
            if(itemEntity.getItem().getItem() == Items.LIGHT_GRAY_CONCRETE_POWDER) {
                itemEntity.setItem(new ItemStack( Items.LIGHT_GRAY_CONCRETE, itemEntity.getItem().getCount()));

            }
        }

        if(pEntity instanceof ItemEntity itemEntity){
            if(itemEntity.getItem().getItem() == Items.CYAN_CONCRETE_POWDER) {
                itemEntity.setItem(new ItemStack( Items.CYAN_CONCRETE, itemEntity.getItem().getCount()));

            }
        }

        if(pEntity instanceof ItemEntity itemEntity){
            if(itemEntity.getItem().getItem() == Items.PURPLE_CONCRETE_POWDER) {
                itemEntity.setItem(new ItemStack( Items.PURPLE_CONCRETE, itemEntity.getItem().getCount()));

            }
        }

        if(pEntity instanceof ItemEntity itemEntity){
            if(itemEntity.getItem().getItem() == Items.BLUE_CONCRETE_POWDER) {
                itemEntity.setItem(new ItemStack( Items.BLUE_CONCRETE, itemEntity.getItem().getCount()));

            }
        }

        if(pEntity instanceof ItemEntity itemEntity){
            if(itemEntity.getItem().getItem() == Items.BROWN_CONCRETE_POWDER) {
                itemEntity.setItem(new ItemStack( Items.BROWN_CONCRETE, itemEntity.getItem().getCount()));

            }
        }

        if(pEntity instanceof ItemEntity itemEntity){
            if(itemEntity.getItem().getItem() == Items.GREEN_CONCRETE_POWDER) {
                itemEntity.setItem(new ItemStack( Items.GREEN_CONCRETE, itemEntity.getItem().getCount()));

            }
        }

        if(pEntity instanceof ItemEntity itemEntity){
            if(itemEntity.getItem().getItem() == Items.RED_CONCRETE_POWDER) {
                itemEntity.setItem(new ItemStack( Items.RED_CONCRETE, itemEntity.getItem().getCount()));

            }
        }


        if(pEntity instanceof ItemEntity itemEntity){
            if(itemEntity.getItem().getItem() == Items.BLACK_CONCRETE_POWDER) {
                itemEntity.setItem(new ItemStack( Items.BLACK_CONCRETE, itemEntity.getItem().getCount()));

            }
        }


        super.stepOn(pLevel, pPos, pState, pEntity);
    }


    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.alexandritemod.magic_block.tooltip"));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
