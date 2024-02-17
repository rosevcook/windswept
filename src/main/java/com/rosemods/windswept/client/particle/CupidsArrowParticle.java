package com.rosemods.windswept.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CupidsArrowParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    protected CupidsArrowParticle(ClientLevel level, double x, double y, double z, double xd, double yd, double zd, SpriteSet sprites) {
        super(level, x, y, z, xd, yd, zd);
        this.gravity = .225f;
        this.friction = 1f;
        this.sprites = sprites;
        this.xd = xd + (Math.random() * 2d - 1d) * (double) .05f;
        this.yd = yd + (Math.random() * 2d - 1d) * (double) .05f;
        this.zd = zd + (Math.random() * 2d - 1d) * (double) .05f;
        this.quadSize = .1f * (this.random.nextFloat() * this.random.nextFloat() * 1f + 1f);
        this.lifetime = (int) (16d / ((double) this.random.nextFloat() * .8d + .2d)) + 2;
        this.setSpriteFromAge(sprites);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
        this.xd *= .95f;
        this.yd *= .9f;
        this.zd *= .95f;
    }

    @OnlyIn(Dist.CLIENT)
    public record Provider(SpriteSet sprites) implements ParticleProvider<SimpleParticleType> {
        @Override
        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double xd, double yd, double zd) {
            return new CupidsArrowParticle(level, x, y, z, xd, yd, zd, this.sprites);
        }

    }
}