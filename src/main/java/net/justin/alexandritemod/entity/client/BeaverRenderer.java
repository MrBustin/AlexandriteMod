package net.justin.alexandritemod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.justin.alexandritemod.AlexandriteMod;
import net.justin.alexandritemod.entity.custom.BeaverEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BeaverRenderer extends MobRenderer <BeaverEntity, BeaverModel<BeaverEntity>> {
    public BeaverRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BeaverModel<>(pContext.bakeLayer(BeaverModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(BeaverEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID,"textures/entity/beaver/beaver.png");
    }

    @Override
    public void render(BeaverEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        if(pEntity.isBaby()){
            pPoseStack.scale(0.5f,0.5f,0.5f);
        }else {
            pPoseStack.scale(1f,1f,1f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
