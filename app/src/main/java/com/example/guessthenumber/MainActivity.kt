package com.example.guessthenumber


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guessthenumber.databinding.ActivityMainBinding
import com.example.guessthenumber.setData.currentstage
import com.example.guessthenumber.setData.level
import com.example.guessthenumber.setData.maxstep
import com.example.guessthenumber.setData.range
import com.example.guessthenumber.setData.setMaxSteps
import com.example.guessthenumber.setData.setrange
import com.example.guessthenumber.setData.stage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var stringlist = mutableListOf<String>()
    private lateinit var error: String
    var inputno: Int = -1
    var stepcount: Int = 0
    var rno: Int = -1
    lateinit var userDetail: UserDetails

    private lateinit var m: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Toast.makeText(this, "$level,$range,$maxstep", Toast.LENGTH_SHORT).show()
        rno = generateRandomNo(range)

        binding.apply {
            txtmsg.text = "Enter a no between 1 to $range & $rno"
            rcvOfError.layoutManager = LinearLayoutManager(this@MainActivity)
            rcvOfError.adapter = RcvAdapter(stringlist)
            btnSubmit.setOnClickListener {
                takeinput()
                increasestepcount()
                txtcount.text = "Step count $stepcount"
                checkcondition()
                rcvOfError.adapter?.notifyDataSetChanged()
                txtinput.text!!.clear()
                inputno = -1
            }
        }

    }

    private fun increasestepcount() {
        if (inputno == -1) {
            Toast.makeText(this@MainActivity, "Input Cann't be Blank", Toast.LENGTH_SHORT)
                .show()
        } else {
            stepcount++
        }
    }

    private fun checkcondition() {
        binding.apply {
            when {
                inputno > rno -> {
                    txtmsg.text = "You entered a greater number"
                    m = "You entered a greater number"
                    addToList("$inputno :$m")
                }

                inputno < rno -> {
                    if (inputno == -1) {
                        Toast.makeText(
                            this@MainActivity,
                            "Input Cann't be Blank",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        txtmsg.text = "You entered a smaller number"
                        m = "You entered a smaller number"
                        addToList("$inputno :$m")
                    }

                }

                else -> {
                    Toast.makeText(this@MainActivity, "YOU WON!!!!!!", Toast.LENGTH_SHORT)
                        .show()
                    m = "You entered the correct number"
                    addToList("$inputno :$m")
                    txtmsg.text = "You entered the correct number"
                    txtRandomNo.text = "Random No is, $rno!"
                    txtWon.text = "YOU WON!!!!!!"
                    txtcount.text = "Step count $stepcount"

                    if (stage < 5 && currentstage==stage) {
                        Log.d("level&stage1", "level:$level Stage:$stage")
                        stage++
                        setData.setrange(this@MainActivity)
                        setData.setMaxSteps(this@MainActivity)
                        Log.d("level&stage2", "level:$level Stage:$stage")

                    }
                    else {
                        Log.d("level&stage3", "level:$level Stage:$stage")
                        if (level < 5 && stage==5) {
                            Log.d("level&stage4", "level:$level Stage:$stage")
                            level++
                            stage=1
                            setData.setrange(this@MainActivity)
                            setData.setMaxSteps(this@MainActivity)
                            Log.d("level&stage5", "level:$level Stage:$stage")
//                            movebacktolevel()
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "Congratulations!, All Level Completed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
                    movebacktostage()
                }


            }
        }

    }


    private fun movebacktolevel() {
        startActivity(Intent(this@MainActivity, LevelActivity::class.java))
        finish()

    }

    private fun movebacktostage() {
        startActivity(Intent(this@MainActivity, StageActivity::class.java))
        finish()
    }

    private fun generateRandomNo(maxran: Int): Int {
        return Random.nextInt(1, maxran)
    }


    private fun takeinput() {

        val input = binding.txtinput.text!!.trim().toString()
        if (maxstep > 0) {
            if (input.isNotEmpty()) {
                binding.txtlevel.text = "Level:$level"
                binding.txtstage.text = "Level:$stage"
                binding.txtstepleft.text = "$maxstep:Left"
                inputno = input.toInt()
                maxstep--
            } else {

                binding.txtmsg.text = "Enter the correct number..."
            }
        } else {
            Toast.makeText(this@MainActivity, "No Chance Left", Toast.LENGTH_SHORT).show()
        }

    }

    private fun addToList(mymsg: String) {
        stringlist.add(mymsg)
    }
}

fun generateRandomNumber(): Int {
    return Random.nextInt(1, 101)
}

private fun userinput(): Int {
    print("Enter a No between 1 to 100: ")
    return readLine()!!.toInt()
}

