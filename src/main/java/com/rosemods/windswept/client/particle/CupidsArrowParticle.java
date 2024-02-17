package com.rosemods.windswept.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CupidsArrowParticle extends TextureSheetParticle {
    CupidsArrowParticle(ClientLevel p_106847_, double p_106848_, double p_106849_, double p_106850_) {
        super(p_106847_, p_106848_, p_106849_, p_106850_, 0.0D, 0.0D, 0.0D);
        this.speedUpWhenYMotionIsBlocked = true;
        this.friction = 0.86F;
        this.xd *= 0.01F;
        this.yd *= 0.01F;
        this.zd *= 0.01F;
        this.yd += 0.1D;
        this.quadSize *= 1.5F;
        this.lifetime = 16;
        this.hasPhysics = false;
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public float getQuadSize(float p_106860_) {
        return this.quadSize * Mth.clamp(((float) this.age + p_106860_) / (float) this.lifetime * 32.0F, 0.0F, 1.0F);
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet p_106884_) {
            this.sprite = p_106884_;
        }

        public Particle createParticle(SimpleParticleType p_106895_, ClientLevel p_106896_, double p_106897_, double p_106898_, double p_106899_, double p_106900_, double p_106901_, double p_106902_) {
            CupidsArrowParticle heartparticle = new CupidsArrowParticle(p_106896_, p_106897_, p_106898_, p_106899_);
            heartparticle.pickSprite(this.sprite);
            return heartparticle;
        }
    }
}