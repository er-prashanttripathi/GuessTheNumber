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
import com.example.guessthenumber.setData.level
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
        binding.apply {

            vlevelid.text="Level:$level"
            vstageid.text="Stage:$stage"
        }
//        Toast.makeText(this, "${level}  & ${stage}", Toast.LENGTH_SHORT).show()
        Log.d("levelNstate1", "onCreate: ${level}  & $stage")
        openlevel()
//        getuserlevel()
        Log.d("levelNstate2", "onCreate: $level & $stage")
    }



   /* private fun getuserlevel() {
        lifecycleScope.launch {
            userDetail.getLevel().collect() {

                binding.levelid.text = it.toString()
                level = it.toString().toInt()


                Log.d("levelNstate3", "onCreate: $level & $stage")
                getuserstate()
                Log.d("myinside", "getlevel: $level")

            }
        }

    }*/

    /*private fun getuserstate() {
        lifecycleScope.launch {
            userDetail.getState().collect() {
                binding.stageid.text = it.toString()
                stage = it.toString().toInt()

                openlevel()
                Log.d("levelNstate4", "onCreate: $level & $stage")

            }
        }

    }*/

    private fun openlevel() {
        when (level) {
            1 -> {
                binding.btnlevel1.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel1.setOnClickListener {
                    currentlevel=1
                    gotostage()
                }
            }

            2 -> {
                binding.btnlevel1.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel2.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel1.setOnClickListener {
                    currentlevel=1
                    gotostage()
                }
                binding.btnlevel2.setOnClickListener {
                    currentlevel=2
                    gotostage()
                }

            }

            3 -> {
                binding.btnlevel1.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel2.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel3.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel1.setOnClickListener {
                    currentlevel=1
                    gotostage()
                }
                binding.btnlevel2.setOnClickListener {
                    currentlevel=2
                    gotostage()
                }
                binding.btnlevel3.setOnClickListener {
                    currentlevel=3
                    gotostage()
                }

            }

            4 -> {

                binding.btnlevel1.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel2.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel3.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel4.setBackgroundColor(Color.parseColor(activeColor))
                binding.btnlevel1.setOnClickListener {
                    currentlevel=1
                    gotostage()
                }
                binding.btnlevel2.setOnClickListener {
                    currentlevel=2
                    gotostage()
                }
                binding.btnlevel3.setOnClickListener {
                    currentlevel=3
                    gotostage()
                }
                binding.btnlevel4.setOnClickListener {
                    currentlevel=4
                    gotostage()
                }
            }



            else -> {
                Toast.makeText(this@LevelActivity, "Level Over", Toast.LENGTH_SHORT).show()
            }

        }


    }


    private fun gotostage() {
        startActivity(Intent(this@LevelActivity, StageActivity::class.java))
        finish()
    }


}