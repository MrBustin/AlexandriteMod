package net.justin.alexandritemod.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.justin.alexandritemod.AlexandriteMod;
import net.justin.alexandritemod.block.entity.custom.TransmogTableBlockEntity;
import net.justin.alexandritemod.network.PacketHandler;
import net.justin.alexandritemod.network.TransmogItemPacket;
import net.justin.alexandritemod.network.TransmogSelectPacket;
import net.minecraft.client.gui.GuiGraphics;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;



public class TransmogTableScreen extends AbstractContainerScreen<TransmogTableMenu> {

    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "textures/gui/transmog_table/transmog_table_gui.png");

    private List<ResourceLocation> transmogOptions = new ArrayList<>();
    private ItemStack transmogItem;
    private int selectedTransmogIndex = -1;// Tracks the selected transmog
    public TransmogTableBlockEntity blockEntity;



    public TransmogTableScreen(TransmogTableMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        transmogOptions = menu.getAvailableTransmogs();
        transmogItem = menu.getTransmogItem();

        clearWidgets();

        int xStart = this.leftPos + 40;
        int yStart = this.topPos + 20;
        int buttonWidth = 80; // Adjusted button width for better readability
        int buttonHeight = 20; // Button height

        // Create a button for each transmog option
        for (int i = 0; i < transmogOptions.size(); i++) {
            final int index = i; // Ensure 'index' is final or effectively final
            final String transmogLabel = "Transmog " + (i + 1); // Create a final variable for the label
            System.out.println("Adding transmog button: " + transmogOptions.get(i));

            // Create the button using the builder
            this.addRenderableWidget(Button.builder(
                            Component.literal(transmogLabel), // The label on the button
                            button -> { // The action when the button is pressed
                                selectedTransmogIndex = index;
                                applyTransmog();
                            })
                    .pos(xStart, yStart + (i * 22)) // Set the position
                    .size(buttonWidth, buttonHeight) // Set the size of the button
                    .build()); // Use 'build()' to finalize button creation
        }
    }

        private void applyTransmog() {
            if (selectedTransmogIndex < 0 || selectedTransmogIndex >= transmogOptions.size()) {
                System.out.println("Invalid transmog selection!");
                return;
            }

            ResourceLocation selectedTransmog = transmogOptions.get(selectedTransmogIndex);
            if (selectedTransmog == null) {
                System.out.println("Error: selectedTransmog is null!");
                return;
            }

            System.out.println("Applying transmog: " + selectedTransmog);




            // Send the selected transmog packet to the server
            PacketHandler.sendToServer(new TransmogSelectPacket(selectedTransmog));

            if (!transmogItem.isEmpty() && transmogItem.getCount() > 0) {
                PacketHandler.sendToServer(new TransmogItemPacket(transmogItem));
            }
        }



    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        pGuiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY); // Render tooltips if necessary
    }
}