package timelord.simplykush.util;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class RenderUtil {
	public static void spawnParticles(Entity entity, ParticleTypes particleType, int numberOfParticles, double xPos, double yPos, double zPos, double xOffset, double yOffset, double zOffset, double speed) {
		spawnParticles(entity.level, particleType, numberOfParticles, xPos, yPos, zPos, xOffset, yOffset, zOffset, speed);
	}
	
	/**
	 * Spawns particles
	 */
	public static void spawnParticles(World world, ParticleTypes particleType, int numberOfParticles, double xPos, double yPos, double zPos, double xOffset, double yOffset, double zOffset, double speed) {
		if(world instanceof ServerWorld) {
			//((ServerWorld) world).spawnParticle(particleType, xPos, yPos, zPos, numberOfParticles, xOffset, yOffset, zOffset, speed);
		}
		//else if(world.isClientSide) for(int i = 0; i < numberOfParticles; i++) world.spawnParticle(particleType, particleType.getShouldIgnoreRange(), xPos + world.rand.nextDouble() * xOffset, yPos + world.rand.nextDouble() * yOffset, zPos + world.rand.nextDouble() * zOffset, world.rand.nextDouble() * speed, world.rand.nextDouble() * speed, world.rand.nextDouble() * speed);
	}
	// copied from EnderDragonRenderer
	private static final float ROOT_3 = (float) (Math.sqrt(3.0D) / 2.0D);
}
