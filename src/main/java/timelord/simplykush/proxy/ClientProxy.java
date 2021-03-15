package timelord.simplykush.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.system.CallbackI;
import timelord.simplykush.*;

public class ClientProxy implements ISidedProxy{
	
	@Override
	public PlayerEntity getPlayer () {
		return Minecraft.getInstance().player;
	}
	
	@Override
	public World getWorld () {
		return Minecraft.getInstance().level;
	}
	
	@Override
	public void init () {
		Registry.clientInit();;
		FMLJavaModLoadingContext.get().getModEventBus().addListener(SimplyKush::client);
	}
}
