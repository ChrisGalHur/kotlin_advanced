package com.chrisgalhur.exercisi_final

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.alterac.blurkit.BlurLayout

class MainActivity : AppCompatActivity() {

    //region VARIABLES
    var screenWidth: Float = Resources.getSystem().displayMetrics.widthPixels.toFloat()
    //endregion VARIABLES

    //region ON CREATE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
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

        val ivFish1: ImageView = findViewById(R.id.ivFish1Main)
        val ivFish2: ImageView = findViewById(R.id.ivFish2Main)
        val ivFish3: ImageView = findViewById(R.id.ivFish3Main)
        val ivFish4: ImageView = findViewById(R.id.ivFish4Main)
        val cardView1: CardView = findViewById(R.id.cardView1)
        val cardView2: CardView = findViewById(R.id.cardView2)
        val cardView3: CardView = findViewById(R.id.cardView3)
        val cardView4: CardView = findViewById(R.id.cardView4)
        val cardView5: CardView = findViewById(R.id.cardView5)
        val cardView6: CardView = findViewById(R.id.cardView6)
        val cardView7: CardView = findViewById(R.id.cardView7)
        val cardView8: CardView = findViewById(R.id.cardView8)
        val cardView9: CardView = findViewById(R.id.cardView9)
        val cardView10: CardView = findViewById(R.id.cardView10)
        val cardView11: CardView = findViewById(R.id.cardView11)
        //val cardView12: CardView = findViewById(R.id.cardView12)
        //set invisible
        ivFish1.visibility = View.INVISIBLE
        ivFish2.visibility = View.INVISIBLE
        ivFish3.visibility = View.INVISIBLE
        ivFish4.visibility = View.INVISIBLE
        //endregion UI

        //region GET SCREEN PERCENTAGE
        /*val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        val screenHeight = height.toFloat()
        val screenWidth = width.toFloat()
        val screenPercentage = screenHeight * 0.1
        val screenPercentage2 = screenHeight * 0.2
         */

        //region ANIMATIONS
        animateFish1(ivFish1)
        animateFish2(ivFish2)
        animateFish3(ivFish3)
        animateFish4(ivFish4)
        //endregion ANIMATIONS

        //region LISTENERS CARDVIEWS
        val cardViews = listOf<CardView>(cardView1, cardView2, cardView3, cardView4, cardView5,
            cardView6, cardView7, cardView8, cardView9, cardView10,
            cardView11)

        for ((index, cardView) in cardViews.withIndex()){
            cardView.setOnClickListener {
                animateCard(cardView, index)
            }
        }
        //endregion LISTENERS CARDVIEWS

        //region BLUR
        /*val blurLayout = findViewById<BlurLayout>(R.id.blurLayout1)
        updateBlur(blurLayout)*/

        /*val cardView = findViewById<CardView>(R.id.cardView5)

        cardView.post{
            val bitmap = getBitmapFromView(cardView)
            val blurredBitmap = blur(this, bitmap, 25f)
            cardView.background = BitmapDrawable(resources, blurredBitmap)
        }*/
        //endregion BLUR
    }
    //endregion ON CREATE

    //region ANIMATE FISH 1
    private fun animateFish1(ivToMove1:ImageView){
        ivToMove1.visibility = View.VISIBLE

        ivToMove1.post {
            val animatorX = ObjectAnimator.ofFloat(
                ivToMove1,
                "translationX",
                -ivToMove1.width.toFloat() -1000,
                screenWidth
            )
            animatorX.duration = 11000L
            animatorX.repeatCount = ObjectAnimator.INFINITE
            animatorX.startDelay = 1000L

            val animatorY = ObjectAnimator.ofFloat(
                ivToMove1,
                "translationY",
                1200f, 1400f
            )
            animatorY.duration = 11000L
            animatorY.repeatCount = ObjectAnimator.INFINITE
            animatorY.startDelay = 1000L

            animatorX.start()
            animatorY.start()
        }
    }
    //endregion ANIMATE FISH 1

    //region ANIMATE FISH 2
    private fun animateFish2(ivToMove2:ImageView){
        ivToMove2.visibility = View.VISIBLE

        val animatorX = ObjectAnimator.ofFloat(
            ivToMove2,
            "translationX",
            screenWidth,
            -ivToMove2.width.toFloat() -1000
        )
        animatorX.duration = 14000L
        animatorX.repeatCount = ObjectAnimator.INFINITE

        val animatorY = ObjectAnimator.ofFloat(
            ivToMove2,
            "translationY",
            2300f,
            1600f
        )
        animatorY.duration = 14000L
        animatorY.repeatCount = ObjectAnimator.INFINITE

        animatorX.start()
        animatorY.start()
    }
    //endregion ANIMATE FISH 2

    //region ANIMATE FISH 3
    private fun animateFish3(ivToMove3:ImageView){
        ivToMove3.visibility = View.VISIBLE

        val animatorX = ObjectAnimator.ofFloat(
            ivToMove3,
            "translationX",
            screenWidth,
            -ivToMove3.width.toFloat() -1000
        )
        animatorX.duration = 24000L
        animatorX.repeatCount = ObjectAnimator.INFINITE

        val animatorY = ObjectAnimator.ofFloat(
            ivToMove3,
            "translationY",
            900f,
            +950f
        )
        animatorY.duration = 24000L
        animatorY.repeatCount = ObjectAnimator.INFINITE

        animatorX.start()
        animatorY.start()
    }
    //endregion ANIMATE FISH 3

    //region ANIMATE FISH 4
    private fun animateFish4(ivToMove4:ImageView){
        ivToMove4.visibility = View.VISIBLE

        val animatorX = ObjectAnimator.ofFloat(
            ivToMove4,
            "translationX",
            screenWidth + 1500,
            ivToMove4.width.toFloat() - 800f
        )
        animatorX.duration = 17000L
        animatorX.repeatCount = ObjectAnimator.INFINITE
        animatorX.startDelay = 1000L

        val animatorY = ObjectAnimator.ofFloat(ivToMove4,
            "translationY",
            1200f,
            1700f
        )
        animatorY.duration = 17000L
        animatorY.repeatCount = ObjectAnimator.INFINITE
        animatorY.startDelay = 1000L

        animatorX.start()
        animatorY.start()
    }
    //endregion ANIMATE FISH 4

    //region ANIMATE CARD
    private fun animateCard(cardView: CardView, position: Int){
        //Cambiamos el color de fondo temporalmente para dar un efecto de pulsación
        cardView.setCardBackgroundColor(Color.parseColor("#70000000"))

        //Efecto de movimiento de tamaño
        cardView.animate().scaleX(0.9f).scaleY(0.9f).setDuration(200).withEndAction {
            cardView.animate().scaleX(1f).scaleY(1f).setDuration(200).start()
            //Le devolvemos el color de fondo original
            cardView.setCardBackgroundColor(Color.parseColor("#30000000"))
        }

        Handler(
            Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, ResumeActivity::class.java)
                intent.putExtra("position", position)
                startActivity(intent)
            }, 1000)
    }
    //endregion ANIMATE CARD

    //region BLUR
    fun updateBlur(blurLayout: BlurLayout) {
        blurLayout.invalidate()
    }

    fun blur(context: Context, image: Bitmap, radius: Float): Bitmap {
        val rs = RenderScript.create(context)
        val input = Allocation.createFromBitmap(rs, image)
        val output = Allocation.createTyped(rs, input.type)
        val script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        script.setRadius(radius)
        script.setInput(input)
        script.forEach(output)
        output.copyTo(image)
        return image
    }

    fun getBitmapFromView(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }
    //endregion BLUR
}