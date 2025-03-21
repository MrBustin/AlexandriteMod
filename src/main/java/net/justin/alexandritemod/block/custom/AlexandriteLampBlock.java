package net.justin.alexandritemod.block.custom;


import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;


public class AlexandriteLampBlock extends Block {
    public static final BooleanProperty CLICKED = BooleanProperty.create("clicked");



    public AlexandriteLampBlock(Properties properties){
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(CLICKED,false));
    }

    @Override

    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult){



        if(!pLevel.isClientSide()){
            boolean currentState = pState.getValue(CLICKED);
            pLevel.setBlockAndUpdate(pPos,pState.setValue(CLICKED,!currentState));
            pLevel.playSound(null, pPos, currentState ? SoundEvents.COPPER_BULB_TURN_ON : SoundEvents.COPPER_BULB_TURN_OFF, SoundSource.BLOCKS);

        }


        return InteractionResult.SUCCESS;
    }
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder){
        pBuilder.add(CLICKED);
    }

}
