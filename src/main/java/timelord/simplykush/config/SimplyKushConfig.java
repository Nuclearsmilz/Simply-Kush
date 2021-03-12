package timelord.simplykush.config;


import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class SimplyKushConfig {
	public static class Common {
		public final ForgeConfigSpec.BooleanValue fancyKushModel;
		
		Common(final ForgeConfigSpec.Builder builder){
			builder.comment("Common config settings")
					.push("common");
			fancyKushModel = builder
					.comment("This will make the kush models look fancy if true. Default is false.")
					.translation("simplykush.config.common.fancyKushModel")
					.define("fancyKushModel", false);
			builder.pop();
		}
	}
	
	public static class Client {}
	
	private static final ForgeConfigSpec commonSpec;
	public static final Common COMMON;
	
	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		commonSpec = specPair.getRight();
		COMMON = specPair.getLeft();
	}
	
	public static void register(final ModLoadingContext  MLContext){
		MLContext.registerConfig(ModConfig.Type.COMMON, commonSpec);
	}
}
