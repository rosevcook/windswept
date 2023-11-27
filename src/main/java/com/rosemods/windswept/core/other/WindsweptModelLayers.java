package com.rosemods.windswept.core.other;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public final class WindsweptModelLayers {
    public static final ModelLayerLocation CHILLED = createLocation("chilled");
    public static final ModelLayerLocation FROSTBITER = createLocation("frostbiter");

    private static ModelLayerLocation createLocation(String name) {
        return new ModelLayerLocation(Windswept.REGISTRY_HELPER.prefix(name), "main");
    }

    // Mob Models //

    public static LayerDefinition createChilledBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 16.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(40, 48).mirror().addBox(-1.0F, -2.0F, 1.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.3F)).mirror(false).texOffs(40, 32).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, -1.5708F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(40, 48).mirror().addBox(-3.0F, -2.0F, 1.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.3F)).mirror(false).texOffs(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-5.0F, 2.0F, 0.0F, -1.5708F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createFrostbiterBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -8.0F, -26.0F, 20.0F, 19.0F, 25.0F, new CubeDeformation(0.0F))
                .texOffs(90, 0).addBox(-10.0F, -9.0F, -26.0F, 20.0F, 19.0F, 25.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 3.0F, 11.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(84, 114).addBox(-8.0F, -4.0F, -4.0F, 16.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -14.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition front = head.addOrReplaceChild("front", CubeListBuilder.create().texOffs(64, 86).addBox(-8.0F, -4.0F, -20.0F, 16.0F, 12.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition front_eyes_closed = head.addOrReplaceChild("front_eyes_closed", CubeListBuilder.create().texOffs(128, 86).addBox(-8.0F, -4.0F, -20.0F, 16.0F, 12.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leash = head.addOrReplaceChild("leash", CubeListBuilder.create().texOffs(0, 118).addBox(-10.0F, -7.6906F, -0.8419F, 20.0F, 20.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -2.0F, -0.4363F, 0.0F, 0.0F));

        PartDefinition bell = leash.addOrReplaceChild("bell", CubeListBuilder.create().texOffs(0, 138).addBox(-2.5F, -6.0F, -2.0F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 149).addBox(-3.5F, -8.0F, -3.0F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.3094F, -0.8419F, 3.1416F, 0.0F, 0.0F));

        PartDefinition left_antler = head.addOrReplaceChild("left_antler", CubeListBuilder.create().texOffs(40, 119).addBox(-8.0F, -20.0F, 0.0F, 16.0F, 20.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -1.0F, -4.0F, 0.0F, 0.6981F, 0.0F));

        PartDefinition broken_left_antler = head.addOrReplaceChild("broken_left_antler", CubeListBuilder.create().texOffs(1, 158).addBox(-8.0F, -20.0F, 0.0F, 16.0F, 20.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -1.0F, -4.0F, 0.0F, 0.6981F, 0.0F));

        PartDefinition right_antler = head.addOrReplaceChild("right_antler", CubeListBuilder.create().texOffs(39, 139).addBox(-8.0F, -20.0F, 0.0F, 16.0F, 20.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -1.0F, -4.0F, 0.0F, -0.6981F, 0.0F));

        PartDefinition broken_right_antler = head.addOrReplaceChild("broken_right_antler", CubeListBuilder.create().texOffs(0, 178).addBox(-8.0F, -20.0F, 0.0F, 16.0F, 20.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -1.0F, -4.0F, 0.0F, -0.6981F, 0.0F));

        PartDefinition leaves = head.addOrReplaceChild("leaves", CubeListBuilder.create().texOffs(58, 144).addBox(-8.0F, -4.1F, -20.0F, 16.0F, 0.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(76, 44).addBox(-7.0F, 0.0F, 1.0F, 14.0F, 11.0F, 24.0F, new CubeDeformation(0.5F))
                .texOffs(0, 44).addBox(-7.0F, 0.0F, 0.6F, 14.0F, 18.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 10.0F, -0.1745F, 0.0F, 0.0F));

        PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 86).mirror().addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(9.0F, 0.0F, -7.0F));

        PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 86).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, 0.0F, -7.0F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 86).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 20.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 4.0F, 10.0F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 86).mirror().addBox(-4.0F, 0.0F, -4.0F, 8.0F, 20.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-9.0F, 4.0F, 10.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

}
