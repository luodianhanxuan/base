package com.wangjg.concurrence.particleapplet;

import java.awt.*;

/**
 * @author wangjg
 * 2019/11/5
 */
@SuppressWarnings("WeakerAccess")
public class ParticleCanvas extends Canvas {
    private Particle[] particles = new Particle[0];

    public ParticleCanvas(int size) {
        setSize(new Dimension(size, size));
    }

    /**
     * intend to be called by applet
     */
    protected synchronized void setParticles(Particle[] ps) {
        if (ps == null) {
            throw new IllegalArgumentException("cannot set null");
        }
        this.particles = ps;
    }

    protected synchronized Particle[] getParticles() {
        return this.particles;
    }

    @Override
    public void paint(Graphics g) {
        Particle[] ps = getParticles();
        for (Particle p : ps) {
            p.draw(g);
        }
    }
}
