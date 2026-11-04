package com.example.laboratorio8pmdm

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class Laboratorio3Activity : AppCompatActivity() {

    private var player: ExoPlayer? = null
    private var alarma: MediaPlayer? = null
    private lateinit var tvEstado: TextView

    // Mapa de canales: nombre del puesto -> URL de streaming .mp4
    private val canales = mapOf(
        "Minas Tirith" to "https://media.w3.org/2010/05/sintel/trailer.mp4", //Que pena que quería poner otros videos y he tenido que poner unos genéricos
        "Isengard" to "https://media.w3.org/2010/05/bunny/trailer.mp4",
        "Rivendell" to "https://media.w3.org/2010/05/video/movie_300.mp4"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laboratorio3)

        val spinner = findViewById<Spinner>(R.id.spinnerCanales)
        val playerView = findViewById<PlayerView>(R.id.playerView)
        tvEstado = findViewById(R.id.tvEstado)

        // ExoPlayer acoplado a la vista
        val exoPlayer = ExoPlayer.Builder(this).build()
        player = exoPlayer
        playerView.player = exoPlayer

        // Listener de estados del búfer
        exoPlayer.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                when (state) {
                    Player.STATE_BUFFERING -> tvEstado.text = "Cargando Búfer..."
                    Player.STATE_READY -> tvEstado.text = "Transmitiendo en vivo"
                    Player.STATE_ENDED -> {
                        tvEstado.text = "Conexión terminada"
                        sonarAlarma()
                    }
                }
            }
        })

        //Spinner poblado con ArrayAdapter
        val nombres = canales.keys.toList()
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, nombres)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val url = canales.getValue(nombres[position])
                exoPlayer.setMediaItem(MediaItem.fromUri(url))
                exoPlayer.prepare()
                exoPlayer.playWhenReady = true
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    //Alarma acústica en paralelo con MediaPlayer (res/raw/campanas_alarma.mp3)
    private fun sonarAlarma() {
        alarma?.release()
        alarma = MediaPlayer.create(this, R.raw.campanas_alarma)
        alarma?.start()
    }

    //Libera ambos motores
    override fun onDestroy() {
        super.onDestroy()
        player?.release()
        player = null
        alarma?.release()
        alarma = null
    }
}
