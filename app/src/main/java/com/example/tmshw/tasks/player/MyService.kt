package com.example.tmshw.tasks.player

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.example.tmshw.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MyService : Service(), MediaPlayer.OnPreparedListener {

    private var mediaPlayer: MediaPlayer? = null
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        mediaPlayer = MediaPlayer.create(this, R.raw.sound)
        mediaPlayer?.apply {
            setOnPreparedListener(this@MyService)

        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
    }

    override fun onPrepared(p0: MediaPlayer?) {
        mediaPlayer?.start()
    }

}