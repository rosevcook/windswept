package com.rosemods.windswept.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WillOTheWispParticle extends TextureSheetParticle {
    protected WillOTheWispParticle(ClientLevel level, double x, double y, double z, SpriteSet sprites) {
        super(level, x, y, z);
        this.setSize(.01f, .01f);
        this.pickSprite(sprites);
        this.quadSize *= this.random.nextFloat() * .6F + .6F;
        this.lifetime = Mth.randomBetweenInclusive(level.random, 500, 1000);
        this.hasPhysics = false;
        this.friction = 1f;
        this.gravity = .01f;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @OnlyIn(Dist.CLIENT)
    public record Provider(SpriteSet sprites) implements ParticleProvider<SimpleParticleType> {
        @Override
        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double xd, double yd, double zd) {
            return new WillOTheWispParticle(level, x, y, z, this.sprites);
        }

    }
}
