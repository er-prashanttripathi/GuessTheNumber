package com.example.guessthenumber

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.guessthenumber.databinding.ActivityLevelBinding
import com.example.guessthenumber.setData.currentlevel
//import com.example.guessthenumber.setData.currentlevel
import com.example.guessthenumber.setData.level
import com.example.guessthenumber.setData.soundFlag
import com.example.guessthenumber.setData.stage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class LevelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLevelBinding
    lateinit var userDetail: UserDetails
    val activeColor = "#009688"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_level)
        userDetail = UserDetails(this)
        binding.btnsound.setOnClickListener {
//sound Flag Logic
            if (soundFlag == 0) {
                soundFlag = 1
                binding.btnsound.setImageResource(R.drawable.baseline_volume_off)
                Toast.makeText(this, "soundFlag:$soundFlag", Toast.LENGTH_SHORT).show()
            }
            else {
                soundFlag = 0
                binding.btnsound.setImageResource(R.drawable.baseline_volume_up_24)
                Toast.makeText(this, "soundFlag:$soundFlag", Toast.LENGTH_SHORT).show()
            }

        }
        Toast.makeText(this, "${level}  & ${stage}", Toast.LENGTH_SHORT).show()
        Log.d("levelNstate1", "onCreate: ${level}  & $stage")
        getuserlevel()
        openlevel()
        Log.d("levelNstate2", "onCreate: $level & $stage")
    }


    private fun getuserlevel() {
        lifecycleScope.launch {
            userDetail.getLevel().collect() {
                var m = it.toString()
                binding.vlevelid.text = "Level:$m"
                level = it.toString().toInt()


                Log.d("levelNstate3", "onCreate: $level & $stage")
                getuserstate()
                Log.d("myinside", "getlevel: $level")

            }
        }

    }

    private fun getuserstate() {
        lifecycleScope.launch {
            userDetail.getState().collect() {
                var n = it.toString()
                binding.vstageid.text = "Stage:$n"
                stage = it.toString().toInt()

                openlevel()
                Log.d("levelNstate4", "onCreate: $level & $stage")

            }
        }

    }

    private fun openlevel() {
        when (level) {
            1 -> {
                binding.btnlevel1.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel1.setOnClickListener {
                    currentlevel = 1
                    gotostage()
                }
            }

            2 -> {
                binding.btnlevel1.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel1.setOnClickListener {
                    currentlevel = 1
                    gotostage()
                }
                binding.btnlevel2.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel2.setOnClickListener {
                    currentlevel = 2
                    gotostage()
                }

            }

            3 -> {
                binding.btnlevel1.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel2.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel3.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel1.setOnClickListener {
                    currentlevel = 1
                    gotostage()
                }
                binding.btnlevel2.setOnClickListener {
                    currentlevel = 2
                    gotostage()
                }
                binding.btnlevel3.setOnClickListener {
                    currentlevel = 3
                    gotostage()
                }

            }

            4 -> {

                binding.btnlevel1.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel2.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel3.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel4.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel1.setOnClickListener {
                    currentlevel = 1
                    gotostage()
                }
                binding.btnlevel2.setOnClickListener {
                    currentlevel = 2
                    gotostage()
                }
                binding.btnlevel3.setOnClickListener {
                    currentlevel = 3
                    gotostage()
                }
                binding.btnlevel4.setOnClickListener {
                    currentlevel = 4
                    gotostage()
                }
            }
            5 -> {

                binding.btnlevel1.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel2.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel3.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel4.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel5.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel1.setOnClickListener {
                    currentlevel = 1
                    gotostage()
                }
                binding.btnlevel2.setOnClickListener {
                    currentlevel = 2
                    gotostage()
                }
                binding.btnlevel3.setOnClickListener {
                    currentlevel = 3
                    gotostage()
                }
                binding.btnlevel4.setOnClickListener {
                    currentlevel = 4
                    gotostage()
                }
                binding.btnlevel5.setOnClickListener {
                    currentlevel = 5
                    gotostage()
                }
            }


            else -> {
                Toast.makeText(this@LevelActivity, "Level Over", Toast.LENGTH_SHORT).show()
            }

        }


    }


    private fun gotostage() {
        setData.playsound(this, R.raw.interfacesound, soundFlag)
        startActivity(Intent(this@LevelActivity, StageActivity::class.java))
        finish()
    }


}