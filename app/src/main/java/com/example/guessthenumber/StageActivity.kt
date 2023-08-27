package com.example.guessthenumber

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import com.example.guessthenumber.databinding.ActivityMainBinding
import com.example.guessthenumber.databinding.ActivityStageBinding
import com.example.guessthenumber.setData.currentlevel
import com.example.guessthenumber.setData.currentstage
//import com.example.guessthenumber.setData.// currentstage
import com.example.guessthenumber.setData.level
import com.example.guessthenumber.setData.maxstep
import com.example.guessthenumber.setData.range
import com.example.guessthenumber.setData.setMaxSteps
import com.example.guessthenumber.setData.setrange
import com.example.guessthenumber.setData.soundFlag
import com.example.guessthenumber.setData.stage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class StageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStageBinding
    val activeColor = "#009688"

    private lateinit var onBackPressCallback: OnBackPressedCallback //Backpress Logic 1/3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stage)

        //------------------------Backpress Logic 2/3---------------------------
        onBackPressCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Handle back press event here
                startActivity(Intent(this@StageActivity, LevelActivity::class.java))
                finish()
            }
        }
        onBackPressedDispatcher.addCallback(this, onBackPressCallback)
        //------------------------Backpress Logic 2/3---------------------------
        binding.apply {
            levelid.text = "Level:$level"
            stageid.text = "Stage:$stage"
        }
        Log.d("levelNstateStage", "onCreate: $level & $stage")
        Toast.makeText(this@StageActivity, "$level & $stage", Toast.LENGTH_SHORT).show()
        setrange(this@StageActivity)
        setMaxSteps(this@StageActivity)
        openGame()
    }

    //Backpress Logic 3/3
    override fun onDestroy() {
        super.onDestroy()
        onBackPressCallback.remove()
    }
    //Backpress Logic 3/3

    private fun openGame() {
        if (currentlevel< level){
            binding.btnstage1.setBackgroundColor(Color.parseColor(activeColor))
            binding.btnstage2.setBackgroundColor(Color.parseColor(activeColor))
            binding.btnstage3.setBackgroundColor(Color.parseColor(activeColor))
            binding.btnstage4.setBackgroundColor(Color.parseColor(activeColor))
            binding.btnstage5.setBackgroundColor(Color.parseColor(activeColor))
            binding.btnstage1.setOnClickListener {
                 currentstage = 1
                gotoGame()
            }
            binding.btnstage2.setOnClickListener {
                 currentstage = 2
                gotoGame()
            }
            binding.btnstage3.setOnClickListener {
                 currentstage = 3
                gotoGame()
            }
            binding.btnstage4.setOnClickListener {

                 currentstage = 4
                gotoGame()
            }
            binding.btnstage5.setOnClickListener {
                 currentstage = 5
                gotoGame()
            }
        }
        else{
            when (stage) {
                1 -> {
                    binding.btnstage1.setBackgroundColor(Color.parseColor(activeColor))
                    binding.btnstage1.setOnClickListener {
                         currentstage = 1
                        gotoGame()

                    }
                }

                2 -> {
                    binding.btnstage1.setBackgroundColor(Color.parseColor(activeColor))
                    binding.btnstage2.setBackgroundColor(Color.parseColor(activeColor))
                    binding.btnstage1.setOnClickListener {
                        currentstage = 1
                        gotoGame()
                    }
                    binding.btnstage2.setOnClickListener {
                        currentstage = 2
                        gotoGame()
                    }
                }

                3 -> {
                    binding.btnstage1.setBackgroundColor(Color.parseColor(activeColor))
                    binding.btnstage2.setBackgroundColor(Color.parseColor(activeColor))
                    binding.btnstage3.setBackgroundColor(Color.parseColor(activeColor))
                    binding.btnstage1.setOnClickListener {
                        currentstage = 1
                        gotoGame()
                    }
                    binding.btnstage2.setOnClickListener {
                        currentstage = 2
                        gotoGame()
                    }
                    binding.btnstage3.setOnClickListener {
                        currentstage = 3
                        gotoGame()
                    }

                }

                4 -> {

                    binding.btnstage1.setBackgroundColor(Color.parseColor(activeColor))
                    binding.btnstage2.setBackgroundColor(Color.parseColor(activeColor))
                    binding.btnstage3.setBackgroundColor(Color.parseColor(activeColor))
                    binding.btnstage4.setBackgroundColor(Color.parseColor(activeColor))
                    binding.btnstage1.setOnClickListener {
                        currentstage = 1
                        gotoGame()
                    }
                    binding.btnstage2.setOnClickListener {
                        currentstage = 2
                        gotoGame()
                    }
                    binding.btnstage3.setOnClickListener {
                        currentstage = 3
                        gotoGame()
                    }
                    binding.btnstage4.setOnClickListener {

                        currentstage = 4
                        gotoGame()
                    }
                }

                5 -> {
                    binding.btnstage1.setBackgroundColor(Color.parseColor(activeColor))
                    binding.btnstage2.setBackgroundColor(Color.parseColor(activeColor))
                    binding.btnstage3.setBackgroundColor(Color.parseColor(activeColor))
                    binding.btnstage4.setBackgroundColor(Color.parseColor(activeColor))
                    binding.btnstage5.setBackgroundColor(Color.parseColor(activeColor))
                    binding.btnstage1.setOnClickListener {
                        currentstage = 1
                        gotoGame()
                    }
                    binding.btnstage2.setOnClickListener {
                        currentstage = 2
                        gotoGame()
                    }
                    binding.btnstage3.setOnClickListener {
                        currentstage = 3
                        gotoGame()
                    }
                    binding.btnstage4.setOnClickListener {

                        currentstage = 4
                        gotoGame()
                    }
                    binding.btnstage5.setOnClickListener {
                        currentstage = 5
                        gotoGame()
                    }
                }

                else -> {
                    Log.d("level&stage3", "level:$level Stage:$stage")
                    Toast.makeText(this@StageActivity, "stage Over", Toast.LENGTH_SHORT).show()
                }

            }
        }


    }

    private fun gotoGame() {
        setData.playsound(this, R.raw.interfacesound, soundFlag)
        startActivity(Intent(this@StageActivity, MainActivity::class.java))
        finish()
    }


}