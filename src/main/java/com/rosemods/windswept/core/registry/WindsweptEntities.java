package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.client.render.ChilledRenderer;
import com.rosemods.windswept.common.entity.*;
import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = Windswept.MODID, bus = Bus.MOD)
public class WindsweptEntities {
	public static final EntitySubRegistryHelper HELPER = Windswept.REGISTRY_HELPER.getEntitySubHelper();
	
	public static final RegistryObject<EntityType<Chilled>> CHILLED = HELPER.createLivingEntity("chilled", Chilled::new, MobCategory.MONSTER, .6f, 1.95f);
	
	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(CHILLED.get(), Zombie.createAttributes().build());
	}
	
	@OnlyIn(Dist.CLIENT)
	public static void registerClient() {
		EntityRenderers.register(CHILLED.get(), ChilledRenderer::new);
	}
	
	public static void registerSpawns() {
		SpawnPlacements.register(CHILLED.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
	}
	
}
