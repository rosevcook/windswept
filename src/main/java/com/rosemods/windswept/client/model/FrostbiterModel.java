package com.rosemods.windswept.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.rosemods.windswept.common.entity.Frostbiter;
import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.endimator.Endimator;
import com.teamabnormals.blueprint.core.endimator.entity.EndimatorEntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class FrostbiterModel<T extends Frostbiter> extends EndimatorEntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Windswept.REGISTRY_HELPER.prefix("frostbiter"), "main");
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart tail;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;

    private final ModelPart leash;
    private final ModelPart bell;
    private final ModelPart leftAntler;
    private final ModelPart rightAntler;


    public FrostbiterModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.tail = root.getChild("tail");
        this.leftArm = root.getChild("left_arm");
        this.rightArm = root.getChild("right_arm");
        this.leftLeg = root.getChild("left_leg");
        this.rightLeg = root.getChild("right_leg");

        this.leash = this.head.getChild("leash");
        this.bell = this.leash.getChild("bell");
        this.leftAntler = this.head.getChild("left_antler");
        this.rightAntler = this.head.getChild("right_antler");

        this.endimator = Endimator.compile(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -8.0F, 0.0F, 20.0F, 19.0F, 25.0F, new CubeDeformation(0.0F))
                .texOffs(90, 0).addBox(-10.0F, -9.0F, 0.0F, 20.0F, 19.0F, 25.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 3.0F, -15.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(64, 86).addBox(-8.0F, -4.0F, -20.0F, 16.0F, 12.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(84, 114).addBox(-8.0F, -4.0F, -4.0F, 16.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -14.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition leash = head.addOrReplaceChild("leash", CubeListBuilder.create().texOffs(0, 118).addBox(-10.0F, -7.6906F, -0.8419F, 20.0F, 20.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -2.0F, -0.4363F, 0.0F, 0.0F));

        PartDefinition bell = leash.addOrReplaceChild("bell", CubeListBuilder.create().texOffs(0, 138).addBox(-2.5F, -6.0F, -2.0F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 149).addBox(-3.5F, -8.0F, -3.0F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.3094F, -0.8419F, 3.1416F, 0.0F, 0.0F));

        PartDefinition left_antler = head.addOrReplaceChild("left_antler", CubeListBuilder.create().texOffs(29, 118).addBox(-8.0F, -20.0F, 0.0F, 16.0F, 20.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -1.0F, -4.0F, 0.0F, 0.6981F, 0.0F));

        PartDefinition right_antler = head.addOrReplaceChild("right_antler", CubeListBuilder.create().texOffs(28, 138).addBox(-8.0F, -20.0F, 0.0F, 16.0F, 20.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -1.0F, -4.0F, 0.0F, -0.6981F, 0.0F));

        PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(76, 44).addBox(-7.0F, 0.0F, 1.0F, 14.0F, 11.0F, 24.0F, new CubeDeformation(0.5F))
                .texOffs(0, 44).addBox(-7.0F, 0.0F, 0.6F, 14.0F, 18.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 10.0F, -0.1745F, 0.0F, 0.0F));

        PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 86).mirror().addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(9.0F, 0.0F, -7.0F));

        PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 86).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, 0.0F, -7.0F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 86).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 20.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 4.0F, 10.0F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 86).mirror().addBox(-4.0F, 0.0F, -4.0F, 8.0F, 20.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-9.0F, 4.0F, 10.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.rightLeg.xRot = Mth.cos(limbSwing * .6662f) * 1.2f * limbSwingAmount;
        this.leftLeg.xRot = Mth.cos(limbSwing * .6662f + Mth.PI) * 1.2f * limbSwingAmount;
        this.rightArm.xRot = Mth.cos(limbSwing * .6662f + Mth.PI) * 1.2f * limbSwingAmount;
        this.leftArm.xRot = Mth.cos(limbSwing * .6662f) * 1.2f * limbSwingAmount;

        this.tail.yRot = Mth.cos(limbSwing * .6662f + Mth.PI) * limbSwingAmount;
        this.body.zRot = Mth.cos(limbSwing * .6662f) * .2f * limbSwingAmount;
        this.bell.zRot = Mth.cos(limbSwing * .6662f) * .6f * limbSwingAmount;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (this.young) {
            poseStack.pushPose();
            poseStack.scale(.55f, .55f, .55f);
            poseStack.translate(0f, 1.5f, 0f);
            head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            poseStack.popPose();
            poseStack.pushPose();
            poseStack.scale(.5f, .5f, .5f);
            poseStack.translate(0f, 1.5f, 0f);
            this.renderBody(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            poseStack.popPose();
        } else {
            head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            this.renderBody(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        }
    }

    private void renderBody(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

}