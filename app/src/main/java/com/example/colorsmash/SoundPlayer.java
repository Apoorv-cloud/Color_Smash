package com.example.colorsmash;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

public class SoundPlayer {

    private AudioAttributes audioAttributes;
    final int SOUND_POOL_MAX = 3;

    private static SoundPool soundPool;
    private static int hitOrangeSound;
    private static int hitPinkSound;
    private static int hitBlackSound;
    private static int gameOverSound;

    public SoundPlayer(Context context)
    {
        // Soundpool available in lollipop API 21
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build();
            soundPool = new SoundPool.Builder().setAudioAttributes(audioAttributes).setMaxStreams(SOUND_POOL_MAX).build();


        }else
        {
            soundPool = new SoundPool(SOUND_POOL_MAX, AudioManager.STREAM_MUSIC,0);

        }

        hitOrangeSound = soundPool.load(context,R.raw.orange,1);
        hitBlackSound = soundPool.load(context,R.raw.black,1);
        hitPinkSound = soundPool.load(context,R.raw.pink,1);
        gameOverSound = soundPool.load(context,R.raw.gameover,1);
    }

    public void playHitOrangeSound()
    {
        soundPool.play(hitOrangeSound,1.0f,1.0f,1,0,1.0f);
    }

    public void playHitBlackSound()
    {
        soundPool.play(hitBlackSound,1.0f,1.0f,1,0,1.0f);
    }

    public void playHitBonusSound()
    {
        soundPool.play(hitPinkSound,1.0f,1.0f,1,0,1.0f);
    }

    public void playGameOverSound()
    {
        soundPool.play(gameOverSound,1.0f,1.0f,1,0,1.0f);
    }
}
