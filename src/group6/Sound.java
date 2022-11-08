package group6;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import static group6.BombermanGame.*;

public class Sound {
    private boolean boolSound = true;

    public boolean isBoolSound() {
        return boolSound;
    }

    public void setBoolSound(boolean boolSound) {
        this.boolSound = boolSound;
        tfSound = boolSound;
    }

    public Sound(boolean a) {
        setBoolSound(a);
    }
    private Clip clip;

    public synchronized void sound(String url) {
        if (this.boolSound) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (url.equals("/Sound/title_screen.wav")) {
                            clip = AudioSystem.getClip();
                            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(url));
                            clip.open(audioInputStream);
                            clip.start();
                            clip.loop(20);
                        } else if (url.equals("/Sound/soundGame.wav")){
                            clip = AudioSystem.getClip();
                            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(url));
                            clip.open(audioInputStream);
                            clip.start();
                            clip.loop(28);
                        }else if (url.equals("/Sound/menuPlay.wav")) {
                            clip = AudioSystem.getClip();
                            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(url));
                            clip.open(audioInputStream);
                            clip.start();
                            clip.loop(25);
                        } else {
                            clip = AudioSystem.getClip();
                            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(url));
                            clip.open(audioInputStream);
                            clip.start();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }
    public void stopM() {
        clip.stop();
    }
    public void startM() {
        clip.start();
    }
    public void closeM() {
        clip.close();
    }

}
