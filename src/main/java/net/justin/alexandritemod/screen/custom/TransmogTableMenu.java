package net.justin.alexandritemod.screen.custom;

import net.justin.alexandritemod.block.ModBlocks;
import net.justin.alexandritemod.block.entity.custom.TransmogTableBlockEntity;
import net.justin.alexandritemod.common.TransmogRegistry;
import net.justin.alexandritemod.screen.ModMenuTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TransmogTableMenu extends AbstractContainerMenu {
    private final TransmogTableBlockEntity blockEntity;
    private final Level level;
    private List<ResourceLocation> selectableTransmogs = List.of();
    private final DataSlot selectedTransmogIndex = DataSlot.standalone();
    private final Runnable slotUpdateListener;
    private final ContainerLevelAccess access;

    private final Container inputContainer = new SimpleContainer(1) {
        @Override
        public void setChanged() {
            super.setChanged();
            TransmogTableMenu.this.updateResult();
            TransmogTableMenu.this.slotUpdateListener.run();
        }
    };
    private final ResultContainer outputContainer = new ResultContainer();

    public TransmogTableMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), ContainerLevelAccess.NULL);
    }

    public TransmogTableMenu(int pContainerId, Inventory inv, BlockEntity blockEntity, ContainerLevelAccess access) {
        super(ModMenuTypes.TRANSMOG_TABLE_MENU.get(), pContainerId);

        this.blockEntity = (TransmogTableBlockEntity) blockEntity;
        this.level = inv.player.level();
        this.access = access;
        this.slotUpdateListener = () -> {};
        addDataSlot(selectedTransmogIndex);


        this.addSlot(new SlotItemHandler(this.blockEntity.getInventory(), 0, 86, 91) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof SwordItem || stack.getItem() instanceof PickaxeItem ||
                        stack.getItem() instanceof BowItem || stack.getItem() instanceof CrossbowItem ||
                        stack.getItem() instanceof ArmorItem;
            }

            @Override
            public void setChanged() {
                super.setChanged();
                TransmogTableMenu.this.slotsChanged(new SimpleContainer(TransmogTableMenu.this.blockEntity.getInventory().getStackInSlot(0)));
                TransmogTableMenu.this.slotUpdateListener.run();
            }
        });


        this.addSlot(new Slot(this.outputContainer, 0, 149, 91) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                if (isValidTransmogIndex(selectedTransmogIndex.get())) {
                    ResourceLocation transmogId = selectableTransmogs.get(selectedTransmogIndex.get());
                    TransmogRegistry.applyTransmog(stack, transmogId);
                    player.displayClientMessage(Component.literal("Taking out item with transmog: " + selectedTransmogIndex.get()), false);
                }
                TransmogTableMenu.this.blockEntity.getInventory().setStackInSlot(0, ItemStack.EMPTY);
                TransmogTableMenu.this.selectedTransmogIndex.set(-1);
                //TransmogTableMenu.this.updateResult();
            }
        });

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(access, pPlayer, ModBlocks.TRANSMOG_TABLE_BLOCK.get());
    }

    public void setPreviewSelection(int index) {
        if (index >= 0 && index < this.selectableTransmogs.size()) {
            this.selectedTransmogIndex.set(index);
        }
    }

    public void applyTransmog(Player player) {
        ItemStack inputItem = blockEntity.getInventory().getStackInSlot(0);
        this.selectableTransmogs = TransmogRegistry.getTransmogsForItem(inputItem.getItem());

        if (!isValidTransmogIndex(selectedTransmogIndex.get())) return;

        ResourceLocation transmogId = selectableTransmogs.get(selectedTransmogIndex.get());
        ItemStack result = inputItem.copy();
        TransmogRegistry.applyTransmog(result, transmogId);

        this.outputContainer.setItem(0, result);
        this.blockEntity.getInventory().setStackInSlot(0, ItemStack.EMPTY);
        this.selectedTransmogIndex.set(-1);
        this.selectableTransmogs = List.of();
        this.updateResult();
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        // This method can now be left for legacy compatibility or wired only to Apply if desired
        return false;
    }

    private void updateResult() {
        ItemStack itemStack = blockEntity.getInventory().getStackInSlot(0);
        if (itemStack.isEmpty()) {
            this.selectableTransmogs = List.of();
            return;
        }

        this.selectableTransmogs = TransmogRegistry.getTransmogsForItem(itemStack.getItem());
    }

    private boolean isValidTransmogIndex(int pIndex) {
        return pIndex >= 0 && pIndex < this.selectableTransmogs.size();
    }

    @Override
    public void slotsChanged(Container pInventory) {
        updateResult();
    }

    public List<ResourceLocation> getSelectableTransmogs() {
        return this.selectableTransmogs;
    }

    public int getSelectedTransmogIndex() {
        return this.selectedTransmogIndex.get();
    }


    // DO NOT TOUCH ANYTHING PAST HERE


    /*
    CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
     must assign a slot number to each of the slots used by the GUI.
     For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
     Each time we add a Slot to the container, it automatically increases the slotIndex, which means
      0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
      9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
      36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
      */

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 1;  // must be the number of slots you have!

    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }



    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 118 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 176));
        }
    }

}
