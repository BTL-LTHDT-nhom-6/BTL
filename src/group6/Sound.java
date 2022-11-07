package group6;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    private boolean boolSound = true;

    public void setBoolSound(boolean boolSound) {
        this.boolSound = boolSound;
    }

    public Sound(boolean a) {
        setBoolSound(a);
    }

    public synchronized void soundItem() {
        if (this.boolSound) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Clip clip = AudioSystem.getClip();
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("/Sound/item-39146.wav"));
                        clip.open(audioInputStream);
                        clip.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }
    public synchronized void soundPlayLV1() {
        if (this.boolSound) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Clip clip = AudioSystem.getClip();
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("/Sound/title_screen.wav"));
                        clip.open(audioInputStream);
                        clip.start();
                        clip.loop(20);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }
    public synchronized void soundPlayLV2() {
        if (this.boolSound) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Clip clip = AudioSystem.getClip();
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("/Sound/soundGame.wav"));
                        clip.open(audioInputStream);
                        clip.start();
                        clip.loop(28);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }
    public synchronized void soundBomb() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("/Sound/Explosion7.wav"));
                    clip.open(audioInputStream);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
    public synchronized void soundNextLevel() {
        if (this.boolSound) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Clip clip = AudioSystem.getClip();
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("/Sound/level_complete.wav"));
                        clip.open(audioInputStream);
                        clip.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }
    public synchronized void soundEnd() {
        if (this.boolSound) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Clip clip = AudioSystem.getClip();
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("/Sound/die.wav"));
                        clip.open(audioInputStream);
                        clip.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }
}
