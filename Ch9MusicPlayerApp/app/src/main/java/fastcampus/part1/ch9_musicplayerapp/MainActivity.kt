package fastcampus.part1.ch9_musicplayerapp

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fastcampus.part1.ch9_musicplayerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    //private var mediaPlayer : MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pauseButton.setOnClickListener { mediaPlayerPause() }

        binding.playButton.setOnClickListener { mediaPlayerPlay() }

        binding.stopButton.setOnClickListener { mediaPlayerStop() }
    }

    private fun mediaPlayerPause() {
        // Service 시작
        val intent = Intent(this, MediaPlayerService::class.java)
            .apply { action = MEDIA_PLAYER_PAUSE }
        startService(intent)

//        mediaPlayer?.pause()
    }

    private fun mediaPlayerPlay() {
        // Service 시작
        val intent = Intent(this, MediaPlayerService::class.java)
            .apply { action = MEDIA_PLAYER_PLAY }
        startService(intent)

//        if(mediaPlayer == null) {
//            mediaPlayer = MediaPlayer.create(this, R.raw.drama).apply {
//                this.isLooping = true
//            }
//        }
//        mediaPlayer?.start()
    }

    private fun mediaPlayerStop() {
        // Service 시작
        val intent = Intent(this, MediaPlayerService::class.java)
            .apply { action = MEDIA_PLAYER_STOP }
        startService(intent)

//        mediaPlayer?.stop()
//        mediaPlayer?.release()
//        mediaPlayer = null
    }
}