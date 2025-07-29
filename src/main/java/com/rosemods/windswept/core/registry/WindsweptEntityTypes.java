package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.client.render.entity.animal.FrostbiterRenderer;
import com.rosemods.windswept.client.render.entity.monster.ChilledRenderer;
import com.rosemods.windswept.client.render.entity.projectile.FrostArrowRenderer;
import com.rosemods.windswept.common.entity.Frostbiter;
import com.rosemods.windswept.common.entity.Chilled;
import com.rosemods.windswept.common.entity.FrostArrow;
import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Windswept.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WindsweptEntityTypes {
    public static final EntitySubRegistryHelper HELPER = Windswept.REGISTRY_HELPER.getEntitySubHelper();

    public static final RegistryObject<EntityType<Chilled>> CHILLED = HELPER.createLivingEntity("chilled", Chilled::new, MobCategory.MONSTER, .6f, 2f);
    public static final RegistryObject<EntityType<Frostbiter>> FROSTBITER = HELPER.createLivingEntity("frostbiter", Frostbiter::new, MobCategory.CREATURE, 1.05f, 1.7f);
    public static final RegistryObject<EntityType<FrostArrow>> FROST_ARROW = HELPER.createEntity("frost_arrow", FrostArrow::new, FrostArrow::new, MobCategory.MISC, .5f, .5f);

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(CHILLED.get(), Zombie.createAttributes().build());
        event.put(FROSTBITER.get(), Frostbiter.createFrostbiterAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawns(SpawnPlacementRegisterEvent event) {
        event.register(CHILLED.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        event.register(FROSTBITER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Frostbiter::checkFrostbiterSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerClient() {
        EntityRenderers.register(CHILLED.get(), ChilledRenderer::new);
        EntityRenderers.register(FROSTBITER.get(), FrostbiterRenderer::new);
        EntityRenderers.register(FROST_ARROW.get(), FrostArrowRenderer::new);
    }

}
