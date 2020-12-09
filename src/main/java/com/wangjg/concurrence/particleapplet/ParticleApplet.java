package com.wangjg.concurrence.particleapplet;

import java.applet.*;

/**
 * @author wangjg
 * 2019/11/5
 */
@SuppressWarnings("WeakerAccess")
public class ParticleApplet extends Applet {
    protected Thread[] threads = null;

    protected final ParticleCanvas canvas = new ParticleCanvas(100);

    @Override
    public void init() {
        add(this.canvas);
    }

    protected Thread makeThread(final Particle p) {
        return new Thread(() -> {
            try {
                for (; ; ) {
                    p.move();
                    canvas.repaint();
                    // 100ms is arbitrary
                    Thread.sleep(100);
                }
            } catch (InterruptedException ignored) {
            }
        });
    }

    @Override
    public synchronized void start() {
        // just for demo
        int n = 10;

        if (this.threads == null) {
            Particle[] particles = new Particle[n];
            for (int i = 0; i < n; i++) {
                particles[i] = new Particle(50, 50);
            }
            this.canvas.setParticles(particles);

            threads = new Thread[n];

            for (int i = 0; i < n; i++) {
                threads[i] = this.makeThread(particles[i]);
                threads[i].start();
            }
        }
    }

    @Override
    public synchronized void stop() {
        // bypass if already stoped
        if (threads != null) {
            for (Thread thread : threads) {
                thread.interrupt();
            }
            threads = null;
        }
    }

    public static void main(String[] args) {
        ParticleApplet particleApplet = new ParticleApplet();
        particleApplet.start();
    }
}
