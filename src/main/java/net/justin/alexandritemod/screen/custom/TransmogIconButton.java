package net.justin.alexandritemod.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;

import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public class TransmogIconButton extends AbstractWidget implements GuiEventListener {
    private final ItemStack iconStack;
    private final Consumer<TransmogIconButton> onClick;
    private final int index;
    private boolean selected = false;

    public TransmogIconButton(int x, int y, ItemStack iconStack, Component tooltip, int index, Consumer<TransmogIconButton> onClick) {
        super(x, y, 20, 20, Component.empty());
        this.iconStack = iconStack;
        this.onClick = onClick;
        this.index = index;
        setTooltip(Tooltip.create(tooltip));
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        onClick.accept(this);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public int getIndex() {
        return index;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        RenderSystem.enableDepthTest();
        guiGraphics.fill(this.getX(), this.getY(), this.getX() + this.width, this.getY() + this.height, selected ? 0x80FFFFFF : 0x80000000);
        guiGraphics.renderItem(iconStack, this.getX() + 2, this.getY() + 2);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }
}
