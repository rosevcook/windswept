package com.rosemods.windswept.core.other.events;

import com.rosemods.windswept.common.entity.monster.Chilled;
import com.rosemods.windswept.common.item.SnowBootsItem;
import com.rosemods.windswept.common.item.WoodenMilkBucketItem;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.WindsweptConfig;
import com.rosemods.windswept.core.other.WindsweptDataProcessors;
import com.rosemods.windswept.core.other.tags.WindsweptBlockTags;
import com.rosemods.windswept.core.other.tags.WindsweptEntityTypeTags;
import com.rosemods.windswept.core.registry.WindsweptEffects;
import com.rosemods.windswept.core.registry.WindsweptEntityTypes;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedData;
import com.teamabnormals.blueprint.core.other.tags.BlueprintEntityTypeTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.ThornsEnchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Windswept.MOD_ID)
public class WindsweptEntityEvents {

    @SubscribeEvent
    public static void onEntityHurt(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        DamageSource source = event.getSource();
        Entity attacker = source.getEntity();

        if (attacker == null || entity == null)
            return;

        // thorns damage
        if (entity.hasEffect(WindsweptEffects.THORNS.get())) {
            int amplifier = entity.getEffect(WindsweptEffects.THORNS.get()).getAmplifier() + 1;
            RandomSource rand = entity.getRandom();

            if (ThornsEnchantment.shouldHit(amplifier, rand))
                attacker.hurt(DamageSource.thorns(entity), ThornsEnchantment.getDamage(amplifier, rand));
        }

    }

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        ItemStack stack = event.getItemStack();
        Entity target = event.getTarget();
        Player player = event.getEntity();
        Level level = event.getLevel();
        InteractionHand hand = event.getHand();

        // milk animal with wooden bucket
        if (stack.is(WindsweptItems.WOODEN_BUCKET.get()) && target.getType().is(BlueprintEntityTypeTags.MILKABLE)) {
            if (target instanceof Animal animal && animal.isBaby())
                return;

            WoodenMilkBucketItem.milkAnimal(player, hand, stack);
            event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
            event.setCanceled(true);
        }

    }

    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
        BlockState state = event.getPlacedBlock();

        if (state.is(WindsweptBlockTags.DEFAULT_WHITE_TEXT)) {
            SignBlockEntity sign = (SignBlockEntity) event.getLevel().getBlockEntity(event.getPos());

            if (sign != null)
                sign.setColor(DyeColor.WHITE);
        }
    }

    @SubscribeEvent
    public static void onBabySpawn(BabyEntitySpawnEvent event) {
        if (WindsweptConfig.COMMON.rabbitLitters.get() && event.getParentA() instanceof Rabbit parent && event.getParentB() instanceof Rabbit parentB) {
            Level level = parent.getCommandSenderWorld();
            int litter = level.random.nextInt(3);

            for (int i = 0; i < litter; i++) {
                Rabbit baby = EntityType.RABBIT.create(level);
                if (baby != null) {
                    baby.setBaby(true);
                    baby.setRabbitType(level.random.nextBoolean() ? parent.getRabbitType() : parentB.getRabbitType());
                    baby.moveTo(parent.getX(), parent.getY(), parent.getZ(), 0f, 0f);
                    level.addFreshEntity(baby);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingSpawn(LivingSpawnEvent.CheckSpawn event) {
        Mob mob = event.getEntity();
        LevelAccessor level = event.getLevel();
        MobSpawnType reason = event.getSpawnReason();

        // convert zombies to chilled && skeletons to strays in cold biomes
        if (mob != null && level instanceof ServerLevel && event.getResult() != Result.DENY && mob.getY() > 60 && (reason == MobSpawnType.NATURAL || reason == MobSpawnType.CHUNK_GENERATION) && level.getBiome(mob.blockPosition()).is(Tags.Biomes.IS_SNOWY)) {
            if (mob.getType() == EntityType.ZOMBIE) {
                mob = mob.convertTo(WindsweptEntityTypes.CHILLED.get(), true);

                if (mob instanceof Chilled chilled)
                    chilled.cncCompat(level.getRandom());
            } else if (mob.getType() == EntityType.SKELETON && WindsweptConfig.COMMON.strays.get()) {
                mob = mob.convertTo(EntityType.STRAY, true);

                if (mob != null)
                    mob.setItemInHand(InteractionHand.MAIN_HAND, Items.BOW.getDefaultInstance());
            }
        }

    }

    @SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity == null)
            return;

        // snow speed particles
        if (SnowBootsItem.canSpawnSnowParticle(entity))
            SnowBootsItem.spawnSnowParticle(entity);

        // chilled conversion in powder snow
        if (entity.getType().is(WindsweptEntityTypeTags.CONVERT_TO_CHILLED) && entity instanceof Mob mob) {
            IDataManager data = (IDataManager) mob;

            if (!mob.level.isClientSide && mob.isAlive() && !mob.isNoAi()) {
                if (data.getValue(WindsweptDataProcessors.IS_FREEZE_CONVERTING)) {
                    ammendData(data, WindsweptDataProcessors.FREEZE_CONVERT_TIME, -1);
                    if (data.getValue(WindsweptDataProcessors.FREEZE_CONVERT_TIME) < 0) {
                        mob.convertTo(WindsweptEntityTypes.CHILLED.get(), true);
                        data.clean();
                        if (!mob.isSilent())
                            mob.level.levelEvent(null, 1048, mob.blockPosition(), 0);
                    }
                } else if (mob.isInPowderSnow) {
                    ammendData(data, WindsweptDataProcessors.POWDER_SNOW_TIME, 1);
                    if (data.getValue(WindsweptDataProcessors.POWDER_SNOW_TIME) >= 140) {
                        data.setValue(WindsweptDataProcessors.FREEZE_CONVERT_TIME, 300);
                        data.setValue(WindsweptDataProcessors.IS_FREEZE_CONVERTING, true);
                    }
                } else
                    data.setValue(WindsweptDataProcessors.POWDER_SNOW_TIME, -1);
            }
        }

    }

    private static void ammendData(IDataManager data, TrackedData<Integer> tracked, int change) {
        data.setValue(tracked, data.getValue(tracked) + change);
    }

}