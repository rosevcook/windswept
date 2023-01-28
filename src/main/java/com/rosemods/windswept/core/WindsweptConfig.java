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
		public final ConfigValue<Boolean> iceBoatNerf;
		public final ConfigValue<Boolean> birchBranches;
		public final ConfigValue<Boolean> strays;

		private Common(ForgeConfigSpec.Builder builder) {
			builder.comment("Vanilla gameplay tweaks");
			builder.push("tweaks");
			this.iceBoatNerf = builder.comment("Nerfs Boats ability to go extremely fast when on ice and other splippery blocks (off by default).").define("Ice Boat Nerf", false);
			this.birchBranches = builder.comment("Tall Birch Trees will generate branches growing out the side of the tree.").define("Birch Branches", true);
			this.strays = builder.comment("All skeletons that spawn in snowy biomes are replaced with strays").define("Strays Only", true);
			builder.pop();
		}

	}
	
	public static class Client {	
		public final ConfigValue<Boolean> powderSnowParticles;
		public Client(ForgeConfigSpec.Builder builder) {
			builder.comment("Client tweaks");
			builder.push("client");
			this.powderSnowParticles = builder.comment("If Powder Snow should drop snow particles if there is no block below.").define("Powder Snow Particles", true);
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
