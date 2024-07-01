package com.chrisgalhur.exercisi_final

import android.content.res.Resources
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowInsetsController
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResumeActivity : AppCompatActivity() {

    //region VARIABLES
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var seekBar: SeekBar
    private var handler = Handler()
    //endregion VARIABLES

    //region ON CREATE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resume)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //region UI
        //Barra Notificaciones Azul y simbolos del movil en blanco
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.statusBarColor = ContextCompat.getColor(this, R.color.background_app)
            window.insetsController?.setSystemBarsAppearance(
                0,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        }
        
        val clTop: ConstraintLayout = findViewById(R.id.clTopResume)
        val ivNum: ImageView = findViewById(R.id.ivNumResume)
        val tvTitle: TextView = findViewById(R.id.tvTitleResume)
        val ivPlace: ImageView = findViewById(R.id.ivPlaceResume)
        val tvExplanation: TextView = findViewById(R.id.tvExplanationResume)
        val tvTimeCurrent: TextView = findViewById(R.id.tvTimeCurrentResume)
        val tvTimeTotal: TextView = findViewById(R.id.tvTimeTotalResume)
        val btPlay: ImageView = findViewById(R.id.btPlayResume)
        seekBar = findViewById(R.id.seekBarResume)
        //endregion UI

        //region SET DATA
        try {
            val position = intent.getIntExtra("position", 0)
            setImageview(position, ivNum)
            setTitle(position, tvTitle)
            setPlace(position, ivPlace)
            setExplanation(position, tvExplanation)
            setMediaPlayer(tvTimeTotal, tvTimeCurrent)
        } catch (e: Resources.NotFoundException) {
            Log.e("Error: ", e.toString())
        }
        //endregion SET DATA

        //region SETONCLICKLISTENER CONSTRAINTLAYOUT
        clTop.setOnClickListener {
            finish()
        }
        //endregion SETONCLICKLISTENER CONSTRAINTLAYOUT

        //region MEDIA REPRODUCTION
        btPlay.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
                btPlay.setImageResource(R.drawable.pause_custom)
                handler.postDelayed(runnable, 1000) // Movemos el seekbar cada segundo
            } else {
                mediaPlayer.pause()
                btPlay.setImageResource(R.drawable.play_custom)
                handler.removeCallbacks(runnable) // Paramos el movimiento del seekbar
            }
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
        //endregion MEDIA REPRODUCTION
    }
    //endregion ON CREATE

    //region SET IMAGEVIEW
    private fun setImageview(position: Int, ivNum: ImageView) {
        val images = listOf(
            "@drawable/num1",
            "@drawable/num2",
            "@drawable/num3",
            "@drawable/num4",
            "@drawable/num5",
            "@drawable/num6",
            "@drawable/num7",
            "@drawable/num8",
            "@drawable/num9",
            "@drawable/num10",
            "@drawable/num11"
        )
        val imageId = resources.getIdentifier(images[position], null, packageName)
        ivNum.setImageResource(imageId)
    }
    //endregion SET IMAGEVIEW

    //region SET TITLE
    private fun setTitle(position: Int, tvTitle: TextView) {
        val titles = listOf(R.string.introduccion, R.string.acantilados, R.string.cala_pedrosa, R.string.foradada, R.string.tres_coves, R.string.montgo, R.string.la_escala, R.string.empuries, R.string.medes_emergidas, R.string.medes_sumergidas, R.string.cierre)
        tvTitle.text = getString(titles[position])
    }
    //endregion SET TITLE

    //region SET PLACE
    private fun setPlace(position: Int, ivPlace: ImageView) {
        val places = listOf(R.drawable.introduccio_1, R.drawable.acantilados_2, R.drawable.cala_pedrosa_3, R.drawable.foradada_4, R.drawable.tres_coves_5, R.drawable.montgo_6, R.drawable.l_escala_7, R.drawable.empuries_8, R.drawable.medes_emergides_9, R.drawable.medes_sumergidas_10, R.drawable.cierre_11)
        ivPlace.setImageResource(places[position])
    }
    //endregion SET PLACE

    //region SET EXPLANATION
    private fun setExplanation(position: Int, tvExplanation: TextView) {
        val explanations = listOf(R.string.explicacion_introduccion, R.string.explicacion_acantilados, R.string.explicacion_cala_pedrosa, R.string.explicacion_foradada, R.string.explicacion_tres_coves, R.string.explicacion_montgo, R.string.explicacion_la_escala, R.string.explicacion_empuries, R.string.explicacion_medes_emergidas, R.string.explicacion_medes_sumergidas, R.string.explicacion_cierre)
        val explanation = getString(explanations[position])
        tvExplanation.text = explanation
    }
    //endregion SET EXPLANATION

    //region SET MEDIA PLAYER
    private fun setMediaPlayer(tvTimeTotal: TextView, tvTimeCurrent: TextView) {
        tvTimeCurrent.text = "00:00:00"
        //Aquí elegiríamos el audio a reproducir si los tuvieramos, esto solo es un ejemplo
        mediaPlayer = MediaPlayer.create(this, R.raw.star_wars)
        tvTimeTotal.text = prettyTime(mediaPlayer.duration)
    }
    //endregion SET MEDIA PLAYER

    //region SEEK BAR
    private fun updateSeekBar() {
        seekBar.max = mediaPlayer.duration
        seekBar.progress = mediaPlayer.currentPosition
    }

    private val runnable: Runnable = object : Runnable {
        override fun run() {
            updateSeekBar()
            val tvTimeCurrent: TextView = findViewById(R.id.tvTimeCurrentResume)
            tvTimeCurrent.text = prettyTime(mediaPlayer.currentPosition)
            handler.postDelayed(this, 1000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
        handler.removeCallbacks(runnable)
    }
    //endregion SEEK BAR

    //region PRETTY TIME
    private fun prettyTime(timeMedia: Int): CharSequence? {
        val hours = timeMedia / 3600000
        val minutes = timeMedia % 3600000 / 60000
        val seconds = timeMedia % 60000 / 1000
        return if (hours > 0) {
            String.format("%02d:%02d:%02d", hours, minutes, seconds)//%02d dos dígitos
        } else {
            String.format("00:%02d:%02d", minutes, seconds)
        }
    }
    //endregion PRETTY TIME
}