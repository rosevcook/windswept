package com.rosemods.windswept.core;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.apache.commons.lang3.tuple.Pair;

@EventBusSubscriber(modid = Windswept.MODID)
public class WindsweptConfig {	
	public static final Common COMMON;
	public static final Client CLIENT;
	public static final ForgeConfigSpec COMMON_SPEC;
	public static final ForgeConfigSpec CLIENT_SPEC;
	
	public static class Common {
		public final ConfigValue<Integer> woodenBucketDurabilty;
		public final ConfigValue<Boolean> iceBoatNerf;
		public final ConfigValue<Boolean> birchBranches;
		public final ConfigValue<Boolean> strays;
		public final ConfigValue<Boolean> roots;
		public final ConfigValue<Boolean> biggerFlowerHitbox;
		public final ConfigValue<Boolean> rabbitLitters;

		private Common(ForgeConfigSpec.Builder builder) {
			builder.comment("Windswept Content Tweaks").push("content");
			this.woodenBucketDurabilty = builder.comment("How much durability Wooden Buckets should have").defineInRange("Wooden Bucket Durability", 24, 1, 1000);
			builder.pop();

			builder.comment("Vanilla Gameplay Tweaks").push("tweaks");
			this.birchBranches = builder.comment("Tall Birch Trees will generate branches growing out the side of the tree").define("Birch Branches", true);
			this.strays = builder.comment("All skeletons that spawn in snowy biomes are replaced with strays").define("Strays Only", true);
			this.iceBoatNerf = builder.comment("Nerfs Boats ability to go extremely fast when on ice and other slippery blocks; default: false").define("Ice Boat Nerf", false);
			this.biggerFlowerHitbox = builder.comment("If the Hitbox for Flowers should be Larger").define("Larger Flower Hitbox", true);
			this.rabbitLitters = builder.comment("If Rabbits should have litters of 1-3").define("Rabbit Litters", true);
			builder.pop();

			builder.comment("Tweaks to Windswept Worldgen").push("worldgen");
			this.roots = builder.comment("Roots will grow under the dirt block below a tree, chestnut trees overhanging logs will grow roots").define("Tree Roots", true);
			builder.pop();
		}

	}
	
	public static class Client {	
		public final ConfigValue<Boolean> powderSnowParticles;
		public final ConfigValue<Boolean> largerRabbits;

		public Client(ForgeConfigSpec.Builder builder) {
			builder.push("particles");
			this.powderSnowParticles = builder.comment("If Powder Snow should drop snow Particles if there is no block below").define("Powder Snow Particles", true);
			builder.pop();

			builder.push("entity-renderers");
			this.largerRabbits = builder.comment("Rabbits will be 25% larger").define("Larger Rabbits", true);
			builder.pop();
		}
		
	}
	
	static {
		final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
		final Pair<Client, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(Client::new);
		
		COMMON = commonSpecPair.getLeft();
		CLIENT = clientSpecPair.getLeft();
		COMMON_SPEC = commonSpecPair.getRight();
		CLIENT_SPEC = clientSpecPair.getRight();
	}
	
}
