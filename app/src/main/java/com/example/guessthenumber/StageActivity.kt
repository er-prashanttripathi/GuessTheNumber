package com.example.guessthenumber

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.guessthenumber.databinding.ActivityMainBinding
import com.example.guessthenumber.databinding.ActivityStageBinding
import com.example.guessthenumber.setData.currentstage
import com.example.guessthenumber.setData.level
import com.example.guessthenumber.setData.maxstep
import com.example.guessthenumber.setData.range
import com.example.guessthenumber.setData.setMaxSteps
import com.example.guessthenumber.setData.setrange
import com.example.guessthenumber.setData.stage
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class StageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStageBinding
    val activeColor = "#009688"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stage)
        Toast.makeText(this@StageActivity, "$level & $stage", Toast.LENGTH_SHORT).show()
        setrange(this@StageActivity)
        setMaxSteps(this@StageActivity)
        /*// The runBlocking function blocks the execution of the current coroutine until all the coroutines inside it have completed.
                runBlocking {
                    // Start the coroutine
                    launch {
                        setrange(this@StageActivity)
                        setMaxSteps(this@StageActivity)

                    }
                }*/
        openGame()
    }

    private fun openGame() {
        when (stage) {
            1 -> {
                binding.btnstage1.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnstage1.setOnClickListener {
                    currentstage=1
                    gotoGame()
                }
            }

            2 -> {
                binding.btnstage1.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnstage2.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnstage1.setOnClickListener {
                    currentstage=1
                    gotoGame()
                }
                binding.btnstage2.setOnClickListener {
                    currentstage=2
                    gotoGame()
                }
            }

            3 -> {
                binding.btnstage1.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnstage2.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnstage3.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnstage1.setOnClickListener {
                    currentstage=1
                    gotoGame()
                }
                binding.btnstage2.setOnClickListener {
                    currentstage=2
                    gotoGame()
                }
                binding.btnstage3.setOnClickListener {
                    currentstage=3
                    gotoGame()
                }

            }

            4 -> {

                binding.btnstage1.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnstage2.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnstage3.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnstage4.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnstage1.setOnClickListener {
                    currentstage=1
                    gotoGame()
                }
                binding.btnstage2.setOnClickListener {
                    currentstage=2
                    gotoGame()
                }
                binding.btnstage3.setOnClickListener {
                    currentstage=3
                    gotoGame()
                }
                binding.btnstage4.setOnClickListener {

                    currentstage=4
                    gotoGame()
                }
            }

            5 -> run {
                binding.btnstage1.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnstage2.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnstage3.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnstage4.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnstage5.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnstage1.setOnClickListener {
                    currentstage=1
                    gotoGame()
                }
                binding.btnstage2.setOnClickListener {
                    currentstage=2
                    gotoGame()
                }
                binding.btnstage3.setOnClickListener {
                    currentstage=3
                    gotoGame()
                }
                binding.btnstage4.setOnClickListener {

                    currentstage=4
                    gotoGame()
                }
                binding.btnstage5.setOnClickListener {
                    currentstage=5
                    gotoGame()
                }
            }

            else -> {
                Toast.makeText(this@StageActivity, "stage Over", Toast.LENGTH_SHORT).show()
            }

        }
        //------------------------
        /*      binding.btnstage1.setOnClickListener {
                  gotoGame()
              }
              binding.btnstage2.setOnClickListener {
                  gotoGame()
              }
              binding.btnstage3.setOnClickListener {
                  gotoGame()
              }
              binding.btnstage4.setOnClickListener {
                  gotoGame()
              }
              binding.btnstage5.setOnClickListener {
                  gotoGame()
              }*/
    }

    private fun gotoGame() {
        startActivity(Intent(this@StageActivity, MainActivity::class.java))
    }

    /*  private fun setMaxSteps() {
          when (stage) {
              1 -> maxstep = 10
              2 -> maxstep = 9
              3 -> maxstep = 8
              4 -> maxstep = 7
              5 -> maxstep = 6
              else -> {
                  Toast.makeText(this, "Stage ${stage} Complete", Toast.LENGTH_SHORT).show()
              }
          }
      }

      private fun setrange() {
          when (level) {
              1 -> range = 10
              2 -> range = 10
              3 -> range = 10
              4 -> range = 10
              5 -> range = 10
              else -> {
                  Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show()
              }

          }
          setMaxSteps()
      }*/
}