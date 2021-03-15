package timelord.simplykush;


import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
	
	public static final Config INSTANCE;
	public static final ForgeConfigSpec SPEC;
	
	public final ForgeConfigSpec.BooleanValue fancyKushModel;
		
	public Config(final ForgeConfigSpec.Builder builder){
			builder.comment("Common config settings")
					.push("common");
			fancyKushModel = builder
					.comment("This will make the kush models look fancy if true. Default is false.")
					.translation("simplykush.config.common.fancyKushModel")
					.define("fancyKushModel", false);
			builder.pop();
		}
	
	static {
		final Pair<Config, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Config::new);
		SPEC = specPair.getRight();
		INSTANCE = specPair.getLeft();
	}
	
	public static void register(final ModLoadingContext  MLContext){
		MLContext.registerConfig(ModConfig.Type.COMMON, SPEC);
	}
}
