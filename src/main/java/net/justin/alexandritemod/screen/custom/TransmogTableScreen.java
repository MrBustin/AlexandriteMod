package net.justin.alexandritemod.screen.custom;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.justin.alexandritemod.AlexandriteMod;

import net.justin.alexandritemod.common.ModDataComponents;
import net.justin.alexandritemod.common.TransmogRegistry;
import net.justin.alexandritemod.item.ModItems;

import net.justin.alexandritemod.network.ApplyTransmogPacket;
import net.justin.alexandritemod.network.PacketHandler;
import net.justin.alexandritemod.network.TransmogSelectPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

import net.minecraft.client.gui.components.Button;

import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;

import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TransmogTableScreen extends AbstractContainerScreen<TransmogTableMenu> {

    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "textures/gui/transmog_table/transmog_table_gui.png");

    private int selectedTransmogIndex = -1;
    private List<ResourceLocation> transmogOptions = List.of();
    private final Map<ResourceLocation, ItemStack> previewItemCache = new HashMap<>();

    public TransmogTableScreen(TransmogTableMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    private int scrollOffset = 0;
    private float previewRotation = 0;
    private boolean isDraggingPreview = false;
    private double lastMouseX = 0;
    private long autoRotateStartTime = System.currentTimeMillis();
    private boolean isDraggingScrollbar = false;
    private int scrollbarClickY = 0;


    @Override
    protected void init() {
        super.init();
        this.imageWidth = 176;
        this.imageHeight = 200;
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        // Get transmogs from the menu
        transmogOptions = menu.getSelectableTransmogs();
        selectedTransmogIndex = menu.getSelectedTransmogIndex();


        clearWidgets();

        // Scrollable 4-column grid of transmog icons
        int iconSize = 16;
        int columns = 4;
        int spacing = 4;
        int visibleRows = 3;
        int maxRows = (int) Math.ceil(transmogOptions.size() / (float) columns);
        scrollOffset = Mth.clamp(scrollOffset, 0, Math.max(0, maxRows - visibleRows));
        int gridX = this.leftPos + 84;
        int gridY = this.topPos + 20;

        for (int i = 0; i < transmogOptions.size(); i++) {
            final int index = i;
            ResourceLocation transmogId = transmogOptions.get(i);

            ItemStack iconStack = new ItemStack(ModItems.ALEXANDRITE_SWORD.get());
            iconStack.set(ModDataComponents.CUSTOM_MODEL_INDEX.get(), TransmogRegistry.getTransmogIndex(transmogId));

            int col = i % columns;
            int row = i / columns;

            if (row < scrollOffset || row >= scrollOffset + visibleRows) continue;
            int yPos = gridY + (row - scrollOffset) * (iconSize + spacing);

            TransmogIconButton iconButton = new TransmogIconButton(
                    gridX + col * (iconSize + spacing),
                    yPos,
                    iconStack,
                    Component.literal(transmogId.getPath().replace("_", " ")), index,
                    b -> {
                        selectedTransmogIndex = index;
                        PacketHandler.sendToServer(new TransmogSelectPacket(index));
                        selectedTransmogIndex = index;
                    });

            iconButton.setSelected(index == selectedTransmogIndex);
            this.addRenderableWidget(iconButton);
        }

        // Apply button (leave position as is)
        int applyButtonX = this.leftPos + 105;
        int applyButtonY = this.topPos + 90;
        this.addRenderableWidget(Button.builder(Component.literal("Apply"), b -> {
            if (!menu.getSlot(0).getItem().isEmpty() && selectedTransmogIndex >= 0 && selectedTransmogIndex < transmogOptions.size()) {
                PacketHandler.sendToServer(new ApplyTransmogPacket(selectedTransmogIndex));
            }
        }).pos(applyButtonX, applyButtonY).size(40, 20).build());

        // Leave inventory label placement unchanged
        this.inventoryLabelY = this.topPos + 25;
    }

    @Override
    public void containerTick() {
        super.containerTick();

        // Update transmog list if item changed
        List<ResourceLocation> newList = menu.getSelectableTransmogs();
        if (!newList.equals(transmogOptions)) {
            this.init(); // Refresh buttons if options changed
            return;
        }

        // 🔄 Highlight the correct button
        for (GuiEventListener widget : this.children()) {
            if (widget instanceof TransmogIconButton button) {
                button.setSelected(button.getIndex() == menu.getSelectedTransmogIndex());
            }
        }
    }


    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        // Define the scroll area bounds
        int iconSize = 16;
        int spacing = 4;
        int columns = 4;
        int visibleRows = 3;

        int gridX = this.leftPos + 84;
        int gridY = this.topPos + 20;
        int gridWidth = columns * (iconSize + spacing) - spacing;
        int gridHeight = visibleRows * (iconSize + spacing) - spacing;

        boolean overGrid = mouseX >= gridX && mouseX < gridX + gridWidth &&
                mouseY >= gridY && mouseY < gridY + gridHeight;

        if (overGrid) {
            int maxRows = (int) Math.ceil(transmogOptions.size() / (float) columns);
            int maxScroll = Math.max(0, maxRows - visibleRows);

            if (maxScroll > 0 && scrollY != 0) {
                scrollOffset = Mth.clamp(scrollOffset - (int) Math.signum(scrollY), 0, maxScroll);
                this.init(); // rebuild UI with new offset
                return true;
            }
        }

        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }


    private ItemStack getPreviewItemStack() {
        if (selectedTransmogIndex < 0 || selectedTransmogIndex >= transmogOptions.size()) {
            return ItemStack.EMPTY;
        }

        ItemStack base = menu.getSlot(0).getItem();
        if (base.isEmpty()) return ItemStack.EMPTY;

        ItemStack preview = base.copy();
        ResourceLocation transmogId = transmogOptions.get(selectedTransmogIndex);
        TransmogRegistry.applyTransmog(preview, transmogId);
        return preview;
    }


    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0 && isMouseOverPreview(mouseX, mouseY)) {
            isDraggingPreview = true;
            float time = (System.currentTimeMillis() % 8000L) / 8000.0F * 360.0F;
            previewRotation = time;
            lastMouseX = mouseX;

            return true;
        }

        if (button == 0) {
            int iconSize = 16;
            int spacing = 4;
            int columns = 4;
            int visibleRows = 3;
            int maxRows = (int) Math.ceil(transmogOptions.size() / (float) columns);

            if (maxRows > visibleRows) {
                int scrollBarX = this.leftPos + 84 + columns * (iconSize + spacing) + 2;
                int scrollBarY = this.topPos + 30;
                int scrollBarHeight = visibleRows * (iconSize + spacing) - spacing;
                int thumbHeight = 10;

                float scrollFraction = scrollOffset / (float) (maxRows - visibleRows);
                int thumbY = scrollBarY + (int)((scrollBarHeight - thumbHeight) * scrollFraction);

                if (mouseX >= scrollBarX && mouseX < scrollBarX + 3 && mouseY >= thumbY && mouseY < thumbY + thumbHeight) {
                    isDraggingScrollbar = true;
                    scrollbarClickY = (int) mouseY - thumbY;
                    return true;
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0 && isDraggingPreview) {
            isDraggingPreview = false;

            float time = (System.currentTimeMillis() % 8000L) / 8000.0F * 360.0F;
            long currentTime = System.currentTimeMillis();

            // Calculate new base time so it starts at previewRotation
            long offset = (long) ((previewRotation / 360.0f) * 8000.0f);
            autoRotateStartTime = currentTime - offset;
            return true;
        }

        if (button == 0 && isDraggingScrollbar) {
            isDraggingScrollbar = false;
            return true;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (isDraggingPreview) {
            double dx = mouseX - lastMouseX;
            previewRotation -= (float) dx * 1f; // sensitivity
            lastMouseX = mouseX;
            return true;
        }

        if (isDraggingScrollbar) {
            int iconSize = 16;
            int spacing = 4;
            int columns = 4;
            int visibleRows = 3;
            int maxRows = (int) Math.ceil(transmogOptions.size() / (float) columns);
            int maxScroll = Math.max(0, maxRows - visibleRows);

            int scrollBarY = this.topPos + 30;
            int scrollBarHeight = visibleRows * (iconSize + spacing) - spacing;
            int thumbHeight = 10;

            float newScrollFraction = (float) ((mouseY - scrollbarClickY - scrollBarY) / (scrollBarHeight - thumbHeight));
            scrollOffset = Mth.clamp((int) (newScrollFraction * maxScroll), 0, maxScroll);

            this.init();
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    private boolean isMouseOverPreview(double mouseX, double mouseY) {
        int boxCenterX = this.leftPos + 40;
        int boxCenterY = this.topPos + 60;
        int boxSize = 60;

        int boxX = boxCenterX - boxSize / 2;
        int boxY = boxCenterY - boxSize / 2;

        return mouseX >= boxX && mouseX < boxX + boxSize &&
                mouseY >= boxY && mouseY < boxY + boxSize;
    }


    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);


        // 3D preview rendering
        ItemStack previewStack = getPreviewItemStack();
        if (!previewStack.isEmpty()) {
            int previewX = leftPos + 40;
            int previewY = topPos + 60;

            int boxCenterX = this.leftPos + 40;
            int boxCenterY = this.topPos + 60;
            int boxSize = 60;
            int boxX = boxCenterX - boxSize / 2;
            int boxY = boxCenterY - boxSize / 2;

            long elapsed = System.currentTimeMillis() - autoRotateStartTime;
            float time = (elapsed % 8000L) / 8000.0F * 360.0F;

            float totalRotation = isDraggingPreview ? previewRotation : time;

            //guiGraphics.fill(boxX, boxY, boxX + boxSize, boxY + boxSize, 0x44FF0000);
            PoseStack pose = guiGraphics.pose();
            pose.pushPose();

            pose.translate(previewX, previewY, 200.0F);
            pose.scale(40.0F, 40.0F, 40.0F);
            pose.mulPose(Axis.YP.rotationDegrees(totalRotation));
            pose.mulPose(Axis.XP.rotationDegrees(180));

            Minecraft.getInstance().getItemRenderer().renderStatic(
                    previewStack,
                    ItemDisplayContext.GUI,
                    15728880,
                    OverlayTexture.NO_OVERLAY,
                    pose,
                    guiGraphics.bufferSource(),
                    Minecraft.getInstance().level,
                    0
            );

            pose.popPose();
            Lighting.setupFor3DItems();
        }

        // Scrollbar rendering
        int iconSize = 16;
        int spacing = 4;
        int columns = 4;
        int visibleRows = 3;
        int maxRows = (int) Math.ceil(transmogOptions.size() / (float) columns);

        if (maxRows > visibleRows) {
            int scrollBarX = this.leftPos + 84 + columns * (iconSize + spacing) + 2;
            int scrollBarY = this.topPos + 20;
            int scrollBarHeight = visibleRows * (iconSize + spacing) - spacing;

            guiGraphics.fill(scrollBarX, scrollBarY, scrollBarX + 3, scrollBarY + scrollBarHeight, 0xFFAAAAAA);

            float scrollFraction = scrollOffset / (float) (maxRows - visibleRows);
            int thumbHeight = 10;
            int thumbY = scrollBarY + (int)((scrollBarHeight - thumbHeight) * scrollFraction);

            guiGraphics.fill(scrollBarX, thumbY, scrollBarX + 3, thumbY + thumbHeight, 0xFF444444);
        }
    }



    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
        // Render each preview item manually on top of its corresponding button
        int xStart = this.leftPos + 98;
        int yStart = this.topPos + 20;
        int buttonHeight = 20;

        for (int i = 0; i < transmogOptions.size(); i++) {
            ResourceLocation transmogId = transmogOptions.get(i);
            ItemStack preview = previewItemCache.get(transmogId);
            if (preview != null && !preview.isEmpty()) {
                int itemX = xStart + 2;
                int itemY = yStart + (i * (buttonHeight + 2)) + 2;
                guiGraphics.renderItem(preview, itemX, itemY);
            }
        }

    }
}