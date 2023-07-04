package com.example.guessthenumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.guessthenumber.databinding.ActivityLevelBinding
import com.example.guessthenumber.setData.level
import com.example.guessthenumber.setData.stage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class LevelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLevelBinding
    lateinit var userDetail: UserDetails

//
//    var stage: Int = 0
//    var level: Int = 0

    //    val level = MutableSharedFlow<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_level)
        userDetail = UserDetails(this)
//        Toast.makeText(this, "${level}  & ${stage}", Toast.LENGTH_SHORT).show()
        Log.d("level&state1", "onCreate: ${setData.level}  & $stage")
        /*   lifecycleScope.launch {
               getlevel()
           }
           lifecycleScope.launch {
               level.collect {
                   Log.d("mylevelval", "getlevel: $it")
               }
           }
           Toast.makeText(this, "$level  & $state", Toast.LENGTH_SHORT).show()*/
        getlevel()

//        Toast.makeText(this, "$level  & $stage", Toast.LENGTH_LONG).show()
        Log.d("level&state2", "onCreate: $level & $stage")
    }


    /*    suspend fun getlevel() {

            userDetail.getLevel().collect() {

                level.emit(it.toString().toInt())

                Log.d("myvalue", "$level ")
            }


        }*/
    private fun getlevel() {
        lifecycleScope.launch {
            userDetail.getLevel().collect() {

                binding.levelid.text = it.toString()
                level = it.toString().toInt()
//                Toast.makeText(this@LevelActivity, "$level  & $stage", Toast.LENGTH_SHORT).show()
                Log.d("level&state3", "onCreate: $level & $stage")
                getstate()
                Log.d("myinside", "getlevel: $level")

            }
        }

    }

    private fun getstate() {
        lifecycleScope.launch {
            userDetail.getState().collect() {
                binding.stageid.text = it.toString()
                stage = it.toString().toInt()
//                Toast.makeText(this@LevelActivity, "$level  & $stage", Toast.LENGTH_SHORT).show()
                openlevel()
                Log.d("level&state4", "onCreate: $level & $stage")
                Log.d("myinside", "getstate: $stage")
            }
        }

    }

    private fun openlevel() {

        binding.btnlevel1.setOnClickListener {
            gotostage()

        }
        binding.btnlevel2.setOnClickListener {
            gotostage()
        }
        binding.btnlevel3.setOnClickListener {
            gotostage()
        }
        binding.btnlevel4.setOnClickListener {
            gotostage()
        }
        binding.btnlevel5.setOnClickListener {
            gotostage()
        }


    }


    private fun gotostage() {
        startActivity(Intent(this@LevelActivity, StageActivity::class.java))
    }


}