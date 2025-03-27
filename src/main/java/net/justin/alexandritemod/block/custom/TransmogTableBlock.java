package net.justin.alexandritemod.block.custom;

import com.mojang.serialization.MapCodec;
import net.justin.alexandritemod.block.entity.custom.GrowthChamberBlockEntity;
import net.justin.alexandritemod.block.entity.custom.PedestalBlockEntity;
import net.justin.alexandritemod.block.entity.custom.TransmogTableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class TransmogTableBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 13.0, 15.0);
    public static final MapCodec<TransmogTableBlock> CODEC = simpleCodec(TransmogTableBlock::new);

    public TransmogTableBlock(Properties pProperties) {
        super(pProperties);
    }


    protected RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }


    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TransmogTableBlockEntity(pPos, pState);
    }

    @Override
    protected void onRemove(BlockState pState, Level pLevel, BlockPos pPos,
                            BlockState pNewState, boolean pMovedByPiston) {
        if (pState.getBlock() != pNewState.getBlock()) {
            if (pLevel.getBlockEntity(pPos) instanceof TransmogTableBlockEntity transmogTableBlockEntity) {
                transmogTableBlockEntity.drops();
                pLevel.updateNeighbourForOutputSignal(pPos, this);
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel,
                                              BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {


        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if (entity instanceof TransmogTableBlockEntity transmogTableBlockEntity) {
                ((ServerPlayer) pPlayer).openMenu(new SimpleMenuProvider(transmogTableBlockEntity, Component.literal("Transmog Table")), pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return ItemInteractionResult.sidedSuccess(pLevel.isClientSide());
    }
}



