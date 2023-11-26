package com.rosemods.windswept.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.rosemods.windswept.common.entity.Frostbiter;
import com.teamabnormals.blueprint.core.endimator.Endimator;
import com.teamabnormals.blueprint.core.endimator.entity.EndimatorEntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class FrostbiterModel<T extends Frostbiter> extends EndimatorEntityModel<T> {
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