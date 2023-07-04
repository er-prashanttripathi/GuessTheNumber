package com.example.guessthenumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.guessthenumber.databinding.ActivityMainBinding
import com.example.guessthenumber.databinding.ActivityStageBinding
import com.example.guessthenumber.setData.level
import com.example.guessthenumber.setData.maxstep
import com.example.guessthenumber.setData.range
import com.example.guessthenumber.setData.stage

class StageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stage)
        Toast.makeText(this@StageActivity, "$level & $stage", Toast.LENGTH_SHORT).show()
        setrange()
       openGame()

}

    private fun openGame() {
        binding.btnstage1.setOnClickListener {
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
        }
    }

    private fun gotoGame() {
        startActivity(Intent(this@StageActivity, MainActivity::class.java))
    }

    private fun setMaxSteps() {
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
    }
}