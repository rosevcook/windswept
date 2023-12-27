package com.rosemods.windswept.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.rosemods.windswept.common.entity.animal.Frostbiter;
import com.rosemods.windswept.core.registry.WindsweptPlayableEndimations;
import com.teamabnormals.blueprint.core.endimator.Endimator;
import com.teamabnormals.blueprint.core.endimator.entity.EndimatorEntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class FrostbiterModel extends EndimatorEntityModel<Frostbiter> implements HeadedModel {
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart tail;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;

    private final ModelPart front;
    private final ModelPart frontEyesClosed;
    private final ModelPart leaves;
    private final ModelPart leash;
    private final ModelPart bell;
    private final ModelPart saddle;
    private final ModelPart leftAntler;
    private final ModelPart rightAntler;
    private final ModelPart brokenLeftAntler;
    private final ModelPart brokenRightAntler;

    public FrostbiterModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.tail = root.getChild("tail");
        this.leftArm = root.getChild("left_arm");
        this.rightArm = root.getChild("right_arm");
        this.leftLeg = root.getChild("left_leg");
        this.rightLeg = root.getChild("right_leg");

        this.front = this.head.getChild("front");
        this.frontEyesClosed = this.head.getChild("front_eyes_closed");
        this.leaves = this.head.getChild("leaves");
        this.leash = this.head.getChild("leash");
        this.bell = this.leash.getChild("bell");
        this.saddle = this.body.getChild("saddle");
        this.leftAntler = this.head.getChild("left_antler");
        this.rightAntler = this.head.getChild("right_antler");
        this.brokenLeftAntler = this.head.getChild("broken_left_antler");
        this.brokenRightAntler = this.head.getChild("broken_right_antler");

        this.endimator = Endimator.compile(root);
    }

    @Override
    public void setupAnim(Frostbiter frostbiter, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(frostbiter, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        boolean isShaking = frostbiter.isEndimationPlaying(WindsweptPlayableEndimations.FROSTBITER_SHAKE);
        boolean isEating = frostbiter.isEndimationPlaying(WindsweptPlayableEndimations.FROSTBITER_SHAKE);

        this.rightLeg.xRot = Mth.cos(limbSwing * .6662f) * .9f * limbSwingAmount;
        this.leftLeg.xRot = Mth.cos(limbSwing * .6662f + Mth.PI) * .9f * limbSwingAmount;

        if (!isEating) {
            this.rightArm.xRot = Mth.cos(limbSwing * .6662f + Mth.PI) * .9f * limbSwingAmount;
            this.leftArm.xRot = Mth.cos(limbSwing * .6662f) * .9f * limbSwingAmount;

            this.tail.yRot = Mth.cos(limbSwing * .6662f + Mth.PI) * limbSwingAmount;
            this.tail.zRot = Mth.cos(limbSwing * .6662f + Mth.PI) * .1f * limbSwingAmount;
            this.body.zRot = Mth.cos(limbSwing * .6662f) * .2f * limbSwingAmount;

            if (!isShaking) {
                this.head.yRot = headPitch * ((float) Math.PI / 180f);
                this.bell.zRot = Mth.cos(limbSwing * .6662f) * .6f * limbSwingAmount;
            }
        }

        this.front.visible = frostbiter.isNoEndimationPlaying();
        this.frontEyesClosed.visible = !this.front.visible;
        this.leaves.visible = frostbiter.hasAntlers();
        this.leash.visible = frostbiter.isTame();
        this.saddle.visible = frostbiter.isSaddled();
        this.leftAntler.visible = frostbiter.hasLeftAntler();
        this.rightAntler.visible = frostbiter.hasRightAntler();
        this.brokenLeftAntler.visible = !this.leftAntler.visible;
        this.brokenRightAntler.visible = !this.rightAntler.visible;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (this.young) {
            poseStack.pushPose();
            poseStack.scale(.55f, .55f, .55f);
            poseStack.translate(0f, 1.3f, .2f);
            head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            poseStack.popPose();

            poseStack.pushPose();
            poseStack.scale(.45f, .45f, .45f);
            poseStack.translate(0f, 1.5f, 0f);
            body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            poseStack.popPose();
        } else {
            head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        }
    }

    @Override
    public ModelPart getHead() {
        return this.head;
    }
}