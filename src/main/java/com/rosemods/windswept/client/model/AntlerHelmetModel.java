package com.rosemods.windswept.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class AntlerHelmetModel extends HumanoidModel<LivingEntity> {
    public static final AntlerHelmetModel INSTANCE = new AntlerHelmetModel(createBodyLayer().bakeRoot());
    private final ModelPart helmet;

    public AntlerHelmetModel(ModelPart root) {
        super(root);
        this.helmet = root.getChild("helmet");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);
        PartDefinition root = meshdefinition.getRoot();

        PartDefinition helmet = root.addOrReplaceChild("helmet", CubeListBuilder.create().texOffs(0, 0).addBox(-4f, -8f, -4f, 8f, 8f, 8f, new CubeDeformation(.7f)), PartPose.offset(0f, 24f, 0f));
        helmet.addOrReplaceChild("antlers", CubeListBuilder.create().texOffs(0, 15).addBox(-10f, -1.5f, -.25f, 30f, 14f, 0f, new CubeDeformation(0f)), PartPose.offsetAndRotation(-5f, -6f, 1f, .2618f, 0f, 0f));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.helmet.copyFrom(this.head);
        super.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.helmet);
    }

}
