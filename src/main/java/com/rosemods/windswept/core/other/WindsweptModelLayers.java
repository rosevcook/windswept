package com.rosemods.windswept.core.other;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public final class WindsweptModelLayers {
    public static final ModelLayerLocation CHILLED = createLocation("chilled");
    public static final ModelLayerLocation FROSTBITER = createLocation("frostbiter");

    private static ModelLayerLocation createLocation(String name) {
        return new ModelLayerLocation(Windswept.location(name), "main");
    }

    // Mob Models //
    public static LayerDefinition createChilledBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4f, -8f, -4f, 8f, 8f, 8f, new CubeDeformation(0f)), PartPose.offset(0f, 0f, 0f));
        partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4f, -8f, -4f, 8f, 8f, 8f, new CubeDeformation(.5f)), PartPose.offset(0f, 0f, 0f));
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4f, 0f, -2f, 8f, 12f, 4f, new CubeDeformation(0f)).texOffs(16, 32).addBox(-4f, 0f, -2f, 8f, 16f, 4f, new CubeDeformation(.25f)), PartPose.offset(0f, 0f, 0f));
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1f, -2f, -2f, 4f, 12f, 4f, new CubeDeformation(0f)).mirror(false).texOffs(40, 48).mirror().addBox(-1f, -2f, 1f, 4f, 12f, 4f, new CubeDeformation(.3f)).mirror(false).texOffs(40, 32).mirror().addBox(-1f, -2f, -2f, 4f, 12f, 4f, new CubeDeformation(.25f)).mirror(false), PartPose.offsetAndRotation(5f, 2f, 0f, -1.5708f, 0f, 0f));
        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3f, -2f, -2f, 4f, 12f, 4f, new CubeDeformation(0f)).texOffs(40, 48).mirror().addBox(-3f, -2f, 1f, 4f, 12f, 4f, new CubeDeformation(.3f)).mirror(false).texOffs(40, 32).addBox(-3f, -2f, -2f, 4f, 12f, 4f, new CubeDeformation(.25f)), PartPose.offsetAndRotation(-5f, 2f, 0f, -1.5708f, 0f, 0f));
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.9f, 0f, -2f, 4f, 12f, 4f, new CubeDeformation(0f)).mirror(false), PartPose.offset(1.9f, 12f, 0f));
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.1f, 0f, -2f, 4f, 12f, 4f, new CubeDeformation(0f)), PartPose.offset(-1.9f, 12f, 0f));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createFrostbiterBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-10f, -8f, -26f, 20f, 19f, 25f, new CubeDeformation(0f)).texOffs(90, 0).addBox(-10f, -9f, -26f, 20f, 19f, 25f, new CubeDeformation(.5f)), PartPose.offset(0f, 3f, 11f));
        body.addOrReplaceChild("saddle", CubeListBuilder.create().texOffs(32, 166).addBox(-10f, -9f, -26f, 20f, 10f, 25f, new CubeDeformation(.6f)), PartPose.offset(0f, 0f, 0f));
        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(84, 114).addBox(-8f, -4f, -4f, 16f, 12f, 6f, new CubeDeformation(0f)), PartPose.offsetAndRotation(0f, 0f, -14f, .4363f, 0f, 0f));
        head.addOrReplaceChild("front", CubeListBuilder.create().texOffs(64, 86).addBox(-8f, -4f, -20f, 16f, 12f, 16f, new CubeDeformation(0f)), PartPose.offset(0f, 0f, 0f));
        head.addOrReplaceChild("front_eyes_closed", CubeListBuilder.create().texOffs(128, 86).addBox(-8f, -4f, -20f, 16f, 12f, 16f, new CubeDeformation(0f)), PartPose.offset(0f, 0f, 0f));
        PartDefinition leash = head.addOrReplaceChild("leash", CubeListBuilder.create().texOffs(0, 118).addBox(-10f, -7.6906f, -.8419f, 20f, 20f, 0f, new CubeDeformation(0f)), PartPose.offsetAndRotation(0f, 1f, -2f, -.4363f, 0f, 0f));
        leash.addOrReplaceChild("bell", CubeListBuilder.create().texOffs(0, 138).addBox(-2.5f, -6f, -2f, 5f, 6f, 5f, new CubeDeformation(0f)).texOffs(0, 149).addBox(-3.5f, -8f, -3f, 7f, 2f, 7f, new CubeDeformation(0f)), PartPose.offsetAndRotation(0f, 12.3094f, -.8419f, 3.1416f, 0f, 0f));
        head.addOrReplaceChild("left_antler", CubeListBuilder.create().texOffs(40, 119).addBox(-8f, -20f, 0f, 16f, 20f, 0f, new CubeDeformation(0f)), PartPose.offsetAndRotation(-8f, -1f, -4f, 0f, .6981f, 0f));
        head.addOrReplaceChild("broken_left_antler", CubeListBuilder.create().texOffs(1, 158).addBox(-8f, -20f, 0f, 16f, 20f, 0f, new CubeDeformation(0f)), PartPose.offsetAndRotation(-8f, -1f, -4f, 0f, .6981f, 0f));
        head.addOrReplaceChild("right_antler", CubeListBuilder.create().texOffs(39, 139).addBox(-8f, -20f, 0f, 16f, 20f, 0f, new CubeDeformation(0f)), PartPose.offsetAndRotation(8f, -1f, -4f, 0f, -.6981f, 0f));
        head.addOrReplaceChild("broken_right_antler", CubeListBuilder.create().texOffs(0, 178).addBox(-8f, -20f, 0f, 16f, 20f, 0f, new CubeDeformation(0f)), PartPose.offsetAndRotation(8f, -1f, -4f, 0f, -.6981f, 0f));
        head.addOrReplaceChild("leaves", CubeListBuilder.create().texOffs(58, 144).addBox(-8f, -4.1f, -20f, 16f, 0f, 22f, new CubeDeformation(0f)), PartPose.offset(0f, 0f, 0f));
        partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(76, 44).addBox(-7f, 0f, 1f, 14f, 11f, 24f, new CubeDeformation(.5f)).texOffs(0, 44).addBox(-7f, 0f, .6f, 14f, 18f, 24f, new CubeDeformation(0f)), PartPose.offsetAndRotation(0f, -1f, 10f, -.1745f, 0f, 0f));
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 86).mirror().addBox(-4f, 0f, -4f, 8f, 24f, 8f, new CubeDeformation(0f)).mirror(false), PartPose.offset(9f, 0f, -7f));
        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 86).addBox(-4f, 0f, -4f, 8f, 24f, 8f, new CubeDeformation(0f)), PartPose.offset(-9f, 0f, -7f));
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 86).addBox(-4f, 0f, -4f, 8f, 20f, 8f, new CubeDeformation(0f)), PartPose.offset(9f, 4f, 10f));
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 86).mirror().addBox(-4f, 0f, -4f, 8f, 20f, 8f, new CubeDeformation(0f)).mirror(false), PartPose.offset(-9f, 4f, 10f));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

}
