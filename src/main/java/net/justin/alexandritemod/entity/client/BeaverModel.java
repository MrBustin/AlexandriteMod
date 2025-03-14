package net.justin.alexandritemod.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.justin.alexandritemod.AlexandriteMod;
import net.justin.alexandritemod.entity.custom.BeaverEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class BeaverModel<T extends BeaverEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "beaver"), "main");
    private final ModelPart Beaver;
    //private final ModelPart head;

    public BeaverModel(ModelPart root) {
        this.Beaver = root.getChild("Beaver");
    //    this.head = Beaver.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Beaver = partdefinition.addOrReplaceChild("Beaver", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Body = Beaver.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -5.0F, -8.0F, 12.0F, 10.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 0.0F));

        PartDefinition Tail_r1 = Body.addOrReplaceChild("Tail_r1", CubeListBuilder.create().texOffs(0, 25).addBox(-5.0F, -2.0F, -1.0F, 6.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 6.0F, -0.6981F, 0.0F, 0.0F));

        PartDefinition LegLS = Body.addOrReplaceChild("LegLS", CubeListBuilder.create(), PartPose.offset(0.0F, 7.0F, 0.0F));

        PartDefinition BL = LegLS.addOrReplaceChild("BL", CubeListBuilder.create().texOffs(10, 48).addBox(-2.0F, 2.0F, -4.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 39).addBox(-2.0F, -3.0F, -2.0F, 3.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -2.0F, 6.0F));

        PartDefinition FL = LegLS.addOrReplaceChild("FL", CubeListBuilder.create().texOffs(44, 39).addBox(4.0F, -3.0F, -7.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 48).addBox(4.0F, 0.0F, -9.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition LegsRS = Body.addOrReplaceChild("LegsRS", CubeListBuilder.create(), PartPose.offset(0.0F, 7.0F, 0.0F));

        PartDefinition FR = LegsRS.addOrReplaceChild("FR", CubeListBuilder.create().texOffs(40, 47).addBox(-7.0F, 0.0F, -9.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(28, 44).addBox(-7.0F, -3.0F, -7.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition BR = LegsRS.addOrReplaceChild("BR", CubeListBuilder.create().texOffs(40, 45).addBox(-7.0F, 0.0F, 2.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(14, 39).addBox(-7.0F, -5.0F, 4.0F, 3.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = Beaver.addOrReplaceChild("head", CubeListBuilder.create().texOffs(36, 25).addBox(-5.0F, -4.0F, -4.0F, 10.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(28, 39).addBox(-3.0F, 0.0F, -6.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(20, 48).addBox(-1.0F, 3.0F, -5.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, -10.0F));

        PartDefinition Ears = head.addOrReplaceChild("Ears", CubeListBuilder.create(), PartPose.offset(0.0F, 7.0F, 10.0F));

        PartDefinition LeftEar_r1 = Ears.addOrReplaceChild("LeftEar_r1", CubeListBuilder.create().texOffs(40, 49).addBox(0.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -10.0F, -11.0F, 0.0F, -0.48F, 0.0F));

        PartDefinition RightEar_r1 = Ears.addOrReplaceChild("RightEar_r1", CubeListBuilder.create().texOffs(24, 48).addBox(-2.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -11.0F, -11.0F, 0.0F, 0.48F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(BeaverEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animateWalk(BeaverAnimations.BEAVER_WALKING, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimationState, BeaverAnimations.BEAVER_IDLE, ageInTicks, 1f);
        this.animate(entity.shakeAnimationState, BeaverAnimations.BEAVER_DRY_OFF, ageInTicks, 1f);

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        Beaver.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root(){
        return Beaver;
    }
}
