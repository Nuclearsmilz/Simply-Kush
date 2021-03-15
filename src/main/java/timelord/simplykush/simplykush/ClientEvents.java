package timelord.simplykush.simplykush;

import com.ibm.icu.impl.ValidIdentifiers;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.*;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import timelord.simplykush.ClientConfig;

import java.util.*;

public class ClientEvents {
	
	@OnlyIn(Dist.CLIENT)
	static IRenderTypeBuffer.Impl DELAYED_RENDER = null;
	
	@OnlyIn(Dist.CLIENT)
	static float clientTicks = 0;
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public void onRenderLast(RenderWorldLastEvent event) {
		if(ClientConfig.BETTER_LAYERING.get()){
			RenderSystem.pushMatrix();
			RenderSystem.multMatrix(event.getMatrixStack().last().pose());
			// put delayed render/particle util events here
			RenderSystem.popMatrix();
		}
		clientTicks += event.getPartialTicks();
	}
	
	@OnlyIn(Dist.CLIENT)
	public static float getClientTicks () {
		return clientTicks;
	}
}
