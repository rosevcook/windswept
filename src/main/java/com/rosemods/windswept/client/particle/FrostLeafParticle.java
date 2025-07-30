package com.rosemods.windswept.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FrostLeafParticle extends TextureSheetParticle {
    protected FrostLeafParticle(ClientLevel level, SpriteSet spriteSet, double x, double y, double z, double xd, double yd, double zd) {
        super(level, x, y, z, xd, yd, zd);

        this.setSpriteFromAge(spriteSet);
        this.gravity = .5f;
        this.friction = .95f;
        this.lifetime = 40 + level.random.nextInt(20);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @OnlyIn(Dist.CLIENT)
    public record Provider(SpriteSet sprites) implements ParticleProvider<SimpleParticleType> {
        @Override
        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double xd, double yd, double zd) {
            return new FrostLeafParticle(level, this.sprites, x, y, z, xd, yd, zd);
        }

    }

}
