package net.justin.alexandritemod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.justin.alexandritemod.AlexandriteMod;
import net.justin.alexandritemod.entity.custom.BeaverEntity;
import net.justin.alexandritemod.entity.custom.LumberJackEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class LumberJackRenderer extends MobRenderer <LumberJackEntity, LumberJackModel<LumberJackEntity>> {

    public LumberJackRenderer(EntityRendererProvider.Context pContext) {
        super(pContext,new LumberJackModel<>(pContext.bakeLayer(LumberJackModel.LAYER_LOCATION)), 0.5f);

        this.addLayer(new ItemInHandLayer<LumberJackEntity, LumberJackModel<LumberJackEntity>>(this, pContext.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(LumberJackEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID,"textures/entity/lumber_jack/lumber_jack.png");
    }

    @Override
    public void render(LumberJackEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        if(pEntity.isBaby()){
            pPoseStack.scale(0.5f,0.5f,0.5f);
        }else {
            pPoseStack.scale(1f,1f,1f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

}
