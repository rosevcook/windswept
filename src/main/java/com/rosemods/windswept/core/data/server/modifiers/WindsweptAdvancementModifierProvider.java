package com.rosemods.windswept.core.data.server.modifiers;

import java.util.List;
import java.util.Objects;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptEffects;
import com.rosemods.windswept.core.registry.WindsweptEntities;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.teamabnormals.blueprint.common.advancement.modification.AdvancementModifierProvider;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.CriteriaModifier;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.EffectsChangedModifier;

import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.KilledTrigger;
import net.minecraft.advancements.critereon.MobEffectsPredicate;
import net.minecraft.advancements.critereon.PlacedBlockTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class WindsweptAdvancementModifierProvider extends AdvancementModifierProvider {

	public WindsweptAdvancementModifierProvider(GatherDataEvent event) {
		super(event.getGenerator(), Windswept.MODID);
	}

	@Override
	protected void registerEntries() {
		final List<Block> seedsBlocks = List.of(WindsweptBlocks.WILD_BERRY_BUSH_PIPS.get());
		final List<EntityType<?>> killedMobs = List.of(WindsweptEntities.CHILLED.get());

		this.entry("balanced_diet").selects("husbandry/balanced_diet").addModifier(this.food().requirements(RequirementsStrategy.AND).build());
		this.entry("all_effects").selects("nether/all_effects").addModifier(new EffectsChangedModifier("all_effects", false, this.effects()));
		this.entry("plant_seed").selects("husbandry/plant_seed").addModifier(this.seedsBlocks(seedsBlocks).addIndexedRequirements(0, false, this.getNames(ForgeRegistries.BLOCKS, seedsBlocks)).build());
		this.entry("kill_a_mob").selects("adventure/kill_a_mob").addModifier(this.killedMobs(killedMobs).addIndexedRequirements(0, false, this.getNames(ForgeRegistries.ENTITY_TYPES, killedMobs)).build());
		this.entry("kill_all_mobs").selects("adventure/kill_all_mobs").addModifier(this.killedMobs(killedMobs).requirements(RequirementsStrategy.AND).build());
		this.entry("walk_on_powder_snow_with_leather_boots").selects("adventure/walk_on_powder_snow_with_leather_boots").addModifier(this.snowBoots().addIndexedRequirements(0, false, "snow_boots").build());
	}
	
	private CriteriaModifier.Builder builder() {
		return CriteriaModifier.builder(this.modId);
	}
	
	private <T> String[] getNames(IForgeRegistry<T> registry, List<? extends T> listIn) {
		String[] names = new String[listIn.size()];
		for(int i = 0; i < listIn.size(); i++) names[i] = registry.getKey(listIn.get(i)).getPath();
		return names;
	}
	
	private CriteriaModifier.Builder food() {
		CriteriaModifier.Builder food = this.builder();
		WindsweptItems.HELPER.getDeferredRegister().getEntries().forEach(i -> {
			if (i.get().getFoodProperties() != null)
				food.addCriterion(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(i.get())).getPath(), ConsumeItemTrigger.TriggerInstance.usedItem(i.get()));
		});
		return food;
	}
	
	private MobEffectsPredicate effects() {
		MobEffectsPredicate effects = MobEffectsPredicate.effects();
		WindsweptEffects.HELPER.getDeferredRegister().getEntries().forEach(e -> effects.and(e.get()));
		return effects;
	}
	
	private CriteriaModifier.Builder seedsBlocks(List<Block> seedsBlocksIn) {
		CriteriaModifier.Builder seedsBlocks = this.builder();
		seedsBlocksIn.forEach(s -> seedsBlocks.addCriterion(ForgeRegistries.BLOCKS.getKey(s).getPath(), PlacedBlockTrigger.TriggerInstance.placedBlock(s)));
		return seedsBlocks;
	}
	
	private CriteriaModifier.Builder killedMobs(List<EntityType<?>> killedMobsIn) {
		CriteriaModifier.Builder killedMobs = this.builder();
		killedMobsIn.forEach(e -> killedMobs.addCriterion(ForgeRegistries.ENTITY_TYPES.getKey(e).getPath(), KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(e))));
		return killedMobs;
	}
	
	private CriteriaModifier.Builder snowBoots() {
		return this.builder().addCriterion("snow_boots", PlayerTrigger.TriggerInstance.walkOnBlockWithEquipment(Blocks.POWDER_SNOW, WindsweptItems.SNOW_BOOTS.get()));
	}
	
}
