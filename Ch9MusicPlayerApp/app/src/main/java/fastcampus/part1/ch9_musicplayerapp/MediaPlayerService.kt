package fastcampus.part1.ch9_musicplayerapp

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MediaPlayerService : Service() {
    private var mediaPlayer : MediaPlayer? = null

    override fun onBind(intent: Intent): IBinder? {
        // not gonna implement
        // reason : We are going to use "Foreground" Service, not 'Bind' service
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            MEDIA_PLAYER_PAUSE -> {
                mediaPlayer?.pause()
            }
            MEDIA_PLAYER_PLAY -> {
                if(mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(baseContext, R.raw.drama).apply {
                        this.isLooping = true
                    }
                }
                mediaPlayer?.start()
            }
            MEDIA_PLAYER_STOP -> {
                mediaPlayer?.stop()
                mediaPlayer?.release()
                mediaPlayer = null
                stopSelf()  // stop explicitly is important
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }
}