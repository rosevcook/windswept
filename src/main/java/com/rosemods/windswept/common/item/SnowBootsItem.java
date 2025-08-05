package com.rosemods.windswept.common.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.rosemods.windswept.common.entity.Frostbiter;
import com.rosemods.windswept.core.other.WindsweptTiers;
import com.rosemods.windswept.core.other.tags.WindsweptBlockTags;
import com.rosemods.windswept.core.registry.WindsweptAttributes;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class SnowBootsItem extends DyeableArmorItem {
    private static final UUID SNOW_SPEED_UUID = UUID.fromString("713d8671-29b8-4600-ba51-580b91cc24cf");
    private static final UUID SPEED_MODIFIER_SNOW_SPEED_UUID = UUID.fromString("c51348dc-85bf-4ac5-8428-deefefec04aa");

    public SnowBootsItem(Properties properties) {
        super(WindsweptTiers.SNOW_BOOTS, Type.BOOTS, properties);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getDefaultAttributeModifiers(slot));
        //if (this.slot == slot)
            builder.put(WindsweptAttributes.SNOW_SPEED.get(), new AttributeModifier(SNOW_SPEED_UUID,
                    "Snow speed modifier", .2f, AttributeModifier.Operation.MULTIPLY_BASE));

        return builder.build();

    }

    // Util //

    public static boolean canApplySnowSpeed(LivingEntity entity) {
        BlockPos below = entity.getOnPos(.500001f);

        return isSnowingAt(entity) || ((entity.level().getBlockState(below).is(WindsweptBlockTags.SNOW_BOOTS_BLOCKS)
                || entity.level().getBlockState(below.above()).is(WindsweptBlockTags.SNOW_BOOTS_BLOCKS)) && !entity.level().getBlockState(entity.getOnPos()).isAir());
    }

    private static boolean isSnowingAt(LivingEntity entity) {
        BlockPos pos = entity.blockPosition();
        return entity.level().isRaining() && entity.level().getBiome(pos).get().getPrecipitationAt(pos) == Biome.Precipitation.SNOW;
    }

    public static boolean canSpawnSnowParticle(LivingEntity entity) {
        return entity.tickCount % 5 == 0 && entity.getDeltaMovement().x != 0d && entity.getDeltaMovement().z != 0d
                && !entity.isSpectator() && canApplySnowSpeed(entity)
                && (entity.getItemBySlot(EquipmentSlot.FEET).is(WindsweptItems.SNOW_BOOTS.get()) || entity instanceof Frostbiter);
    }

    public static void spawnSnowParticle(LivingEntity entity) {
        Vec3 vec3 = entity.getDeltaMovement();
        entity.level().addParticle(ParticleTypes.SNOWFLAKE,
                entity.getX() + (entity.level().random.nextDouble() - .5d) * (double) entity.getBbWidth(),
                entity.getY() + .1d,
                entity.getZ() + (entity.level().random.nextDouble() - .5d) * (double) entity.getBbWidth(), vec3.x * -.2d,
                .1d, vec3.z * -.2d);
    }

    public static void removeSnowSpeed(LivingEntity entity) {
        AttributeInstance speed = entity.getAttribute(Attributes.MOVEMENT_SPEED);

        if (speed != null && speed.getModifier(SPEED_MODIFIER_SNOW_SPEED_UUID) != null)
            speed.removeModifier(SPEED_MODIFIER_SNOW_SPEED_UUID);
    }

    public static void tryAddSnowSpeed(LivingEntity entity) {
        if (entity.getItemBySlot(EquipmentSlot.FEET).is(WindsweptItems.SNOW_BOOTS.get())) {
            AttributeInstance speed = entity.getAttribute(Attributes.MOVEMENT_SPEED);

            if (speed != null) {
                if (speed.getModifier(SPEED_MODIFIER_SNOW_SPEED_UUID) == null)
                    speed.addTransientModifier(new AttributeModifier(SPEED_MODIFIER_SNOW_SPEED_UUID, "Snow speed boost", .2f, AttributeModifier.Operation.MULTIPLY_BASE));

                if (entity.level().random.nextFloat() < .02f)
                    entity.getItemBySlot(EquipmentSlot.FEET).hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(EquipmentSlot.FEET));
            }
        }
    }

}
