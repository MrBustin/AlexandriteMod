package net.justin.alexandritemod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.justin.alexandritemod.block.custom.TransmogTableBlock;
import net.justin.alexandritemod.block.entity.custom.PedestalBlockEntity;
import net.justin.alexandritemod.block.entity.custom.TransmogTableBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

public class TransmogTableBlockRenderer implements BlockEntityRenderer<TransmogTableBlockEntity> {
    public TransmogTableBlockRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(TransmogTableBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        if (pBlockEntity.inventory != null) {
            ItemStack stack = pBlockEntity.inventory.getStackInSlot(0);

            if (!stack.isEmpty()) {
                pPoseStack.pushPose();

                // Check if the item is a sword, pickaxe, bow, or armor
                boolean isToolOrWeapon = stack.getItem() instanceof SwordItem ||
                        stack.getItem() instanceof PickaxeItem ||
                        stack.getItem() instanceof BowItem ||
                        stack.getItem() instanceof CrossbowItem;
                boolean isArmor = stack.getItem() instanceof ArmorItem;

                if (isToolOrWeapon || isArmor) {
                    // Position and scale for tools/weapons/armor
                    pPoseStack.translate(0.5f, 0.85f, 0.5f);
                    pPoseStack.scale(0.6f, 0.6f, 0.6f);
                    pPoseStack.mulPose(Axis.XP.rotationDegrees(90));
                }


                // Render the item
                itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED,
                        pPackedLight, OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource,
                        pBlockEntity.getLevel(), 1);

                pPoseStack.popPose();
            }
        }
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
