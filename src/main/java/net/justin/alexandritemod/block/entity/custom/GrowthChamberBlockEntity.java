package net.justin.alexandritemod.block.entity.custom;

import net.justin.alexandritemod.block.entity.ModBlockEntities;
import net.justin.alexandritemod.item.ModItems;
import net.justin.alexandritemod.recipe.GrowthChamberRecipe;
import net.justin.alexandritemod.recipe.GrowthChamberRecipeInput;
import net.justin.alexandritemod.recipe.ModRecipes;
import net.justin.alexandritemod.screen.custom.GrowthChamberMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class GrowthChamberBlockEntity extends BlockEntity implements MenuProvider{

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    private static final int BONEMEAL_SLOT = 2;

    public final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            if (slot == BONEMEAL_SLOT) {
                return stack.getItem() == Items.BONE_MEAL; // Only bonemeal allowed
            }
            if (slot == OUTPUT_SLOT) {
                return false; // Prevent manual insertion into output slot
            }
            return true; // Allow any item in the input slot
        }
    };




    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;

    public GrowthChamberBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.GROWTH_CHAMBER_BE.get(), pPos, pBlockState);
        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> GrowthChamberBlockEntity.this.progress;
                    case 1 -> GrowthChamberBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0: GrowthChamberBlockEntity.this.progress = value;
                    case 1: GrowthChamberBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        inputHandler.invalidate();
        outputHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("inventory", itemHandler.serializeNBT(pRegistries));
        pTag.putInt("growth_chamber.progress", progress);
        pTag.putInt("growth_chamber.max_progress", maxProgress);

        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);

        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
        progress = pTag.getInt("growth_chamber.progress");
        maxProgress = pTag.getInt("growth_chamber.max_progress");
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.alexandritemod.growth_chamber");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new GrowthChamberMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if (hasRecipe()) {
            if (hasCraftingFinished()) {
                craftItem();
                resetProgress();
            } else {
                increaseCraftingProgress();
                setChanged(level, blockPos, blockState);
            }
        } else {
            resetProgress();
        }

        if (hasCropRecipe()){
            if (hasCropGrowingFinished()) {
                craftItem();
                resetProgress();
            } else {
                increaseCraftingProgress();
                setChanged(level, blockPos, blockState);
            }
        }else{
            resetProgress();
        }
    }



    private void resetProgress() {
        // Only proceed if the recipe is a crop recipe and there's bonemeal in the slot
        if (hasCropRecipe()) {
            if (itemHandler.getStackInSlot(BONEMEAL_SLOT).getItem() == Items.BONE_MEAL) {
                if (hasCropGrowingFinished()) {
                    // Consume the bonemeal only when the crop has finished growing
                    itemHandler.extractItem(BONEMEAL_SLOT, 1, false);
                }
            }
        }

        // Reset progress
        this.progress = 0;
        this.maxProgress = 72;
    }

    private void craftItem() {
        Optional<RecipeHolder<GrowthChamberRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output();

        itemHandler.extractItem(INPUT_SLOT, 1, false);
        itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(output.getItem(),
                itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + output.getCount()));
    }


    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private boolean hasCropGrowingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {

        boolean hasBoneBlockBelow = level.getBlockState(this.worldPosition.below()).is(Blocks.BONE_BLOCK);
        if (hasCropRecipe() && itemHandler.getStackInSlot(BONEMEAL_SLOT).getItem() == Items.BONE_MEAL) {
            progress += hasBoneBlockBelow ? 4 : 1; // Double progress if Bone Block is present
        } else {
            progress+= hasBoneBlockBelow ? 4 : 1; // Double progress if Bone Block is present
        }
    }

    private boolean hasCropRecipe(){
        Optional<RecipeHolder<GrowthChamberRecipe>> recipe = getCurrentRecipe();

        if (recipe.isEmpty()) {
            return false;
        }

        GrowthChamberRecipe currentRecipe = recipe.get().value();
        ItemStack inputStack = itemHandler.getStackInSlot(INPUT_SLOT);

        // Check if the recipe is a crop type (adjust condition as needed)
        boolean isCrop = isCropRecipe(currentRecipe);

        // Ensure bonemeal is present if needed
        boolean hasBonemeal = itemHandler.getStackInSlot(BONEMEAL_SLOT).getItem() == Items.BONE_MEAL;

        if (isCrop && !hasBonemeal) {
            return false; // Crop recipes need bonemeal
        }

        ItemStack output = currentRecipe.output();
        return canInsertItemIntoOutputSlot(output) && canInsertAmountIntoOutputSlot(output.getCount());
    }

    private boolean isCropRecipe(GrowthChamberRecipe recipe) {
        // Example check: if the output is a crop, consider it a crop recipe
        Item outputItem = recipe.output().getItem();

        return outputItem == Items.WHEAT || outputItem == Items.CARROT || outputItem == Items.POTATO || outputItem == Items.BEETROOT;
    }



    private boolean hasRecipe() {
        Optional<RecipeHolder<GrowthChamberRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()){
            return false;
        }

        ItemStack output = recipe.get().value().output();
        return canInsertItemIntoOutputSlot(output) && canInsertAmountIntoOutputSlot(output.getCount());
    }

    private Optional<RecipeHolder<GrowthChamberRecipe>> getCurrentRecipe() {
        return this.level.getRecipeManager()
                .getRecipeFor(ModRecipes.GROWTH_CHAMBER_TYPE.get(), new GrowthChamberRecipeInput(itemHandler.getStackInSlot(INPUT_SLOT)),level);
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ? 64 : itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = itemHandler.getStackInSlot(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    // Handler that only allows insertion into INPUT_SLOT
    private final LazyOptional<IItemHandler> inputHandler = LazyOptional.of(() -> new IItemHandler() {
        @Override
        public int getSlots() {
            return 1;
        }

        @Override
        public @NotNull ItemStack getStackInSlot(int slot) {
            return itemHandler.getStackInSlot(INPUT_SLOT);
        }

        @Override
        public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
            return itemHandler.insertItem(INPUT_SLOT, stack, simulate);
        }

        @Override
        public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
            return ItemStack.EMPTY; // Prevent extraction from input slot
        }

        @Override
        public int getSlotLimit(int slot) {
            return itemHandler.getSlotLimit(INPUT_SLOT);
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return itemHandler.isItemValid(INPUT_SLOT, stack);
        }
    });

    // Handler that only allows bonemeal insertion into BONEMEAL_SLOT
    private final LazyOptional<IItemHandler> bonemealHandler = LazyOptional.of(() -> new IItemHandler() {
        @Override
        public int getSlots() {
            return 1;
        }

        @Override
        public @NotNull ItemStack getStackInSlot(int slot) {
            return itemHandler.getStackInSlot(BONEMEAL_SLOT);
        }

        @Override
        public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
            return stack.getItem() == Items.BONE_MEAL ? itemHandler.insertItem(BONEMEAL_SLOT, stack, simulate) : stack;
        }

        @Override
        public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
            return ItemStack.EMPTY; // Prevent extraction from bonemeal slot
        }

        @Override
        public int getSlotLimit(int slot) {
            return itemHandler.getSlotLimit(BONEMEAL_SLOT);
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return stack.getItem() == Items.BONE_MEAL; // Only allow bonemeal
        }
    });

    // Handler that only allows extraction from OUTPUT_SLOT
    private final LazyOptional<IItemHandler> outputHandler = LazyOptional.of(() -> new IItemHandler() {
        @Override
        public int getSlots() {
            return 1;
        }

        @Override
        public @NotNull ItemStack getStackInSlot(int slot) {
            return itemHandler.getStackInSlot(OUTPUT_SLOT);
        }

        @Override
        public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
            return stack; // Prevent inserting into output slot
        }

        @Override
        public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
            return itemHandler.extractItem(OUTPUT_SLOT, amount, simulate);
        }

        @Override
        public int getSlotLimit(int slot) {
            return itemHandler.getSlotLimit(OUTPUT_SLOT);
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return false; // Prevent inserting into output slot
        }
    });

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            if (side == Direction.DOWN) {
                return outputHandler.cast(); // Only allow extraction from output
            } else if (side == Direction.UP) {
                return inputHandler.cast(); // Insert items into input slot from the top
            } else {
                return bonemealHandler.cast(); // Allow bonemeal input from the sides
            }
        }
        return super.getCapability(cap, side);
    }


}
