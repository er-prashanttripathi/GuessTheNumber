package com.example.guessthenumber

//import com.example.guessthenumber.setData.currentstage
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guessthenumber.databinding.ActivityMainBinding
import com.example.guessthenumber.setData.currentlevel
import com.example.guessthenumber.setData.currentstage
import com.example.guessthenumber.setData.level
import com.example.guessthenumber.setData.maxstep
import com.example.guessthenumber.setData.playsound
import com.example.guessthenumber.setData.range
import com.example.guessthenumber.setData.soundFlag
import com.example.guessthenumber.setData.stage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var stringlist = mutableListOf<String>()
    private lateinit var error: String
    var inputno: Int = -1
    var stepcount: Int = 0
    var rno: Int = -1
    lateinit var userDetailStage: UserDetails

    private lateinit var onBackPressCallback: OnBackPressedCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Toast.makeText(this, "$level,$range,$maxstep", Toast.LENGTH_SHORT).show()
        rno = generateRandomNo(range)
        userDetailStage = UserDetails(this@MainActivity)


        onBackPressCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Handle back press event here
                moveBackToStage()
            }
        }
        onBackPressedDispatcher.addCallback(this, onBackPressCallback)
        binding.apply {
            btnNo0.setOnClickListener {
                playclicksound()
                txtinput.append("0")
            }
            btnNo1.setOnClickListener {
                playclicksound()
                txtinput.append("1")
            }
            btnNo2.setOnClickListener {
                playclicksound()
                txtinput.append("2")
            }
            btnNo3.setOnClickListener {
                playclicksound()
                txtinput.append("3")
            }
            btnNo4.setOnClickListener {
                playclicksound()
                txtinput.append("4")
            }
            btnNo5.setOnClickListener {
                playclicksound()
                txtinput.append("5")
            }
            btnNo6.setOnClickListener {
                playclicksound()
                txtinput.append("6")
            }
            btnNo7.setOnClickListener {
                playclicksound()
                txtinput.append("7")
            }
            btnNo8.setOnClickListener {
                playclicksound()
                txtinput.append("8")
            }
            btnNo9.setOnClickListener {
                playclicksound()
                txtinput.append("9")
            }


        binding.txtlevel.text = "Level: $currentlevel"
            binding.txtstage.text = "Stage: $currentstage" +
                    ""
            binding.txtstepleft.text = "Steps Left: $maxstep"
            txtmsg.text = "Enter a number between 1 to $range & $rno"
            rcvOfError.layoutManager = LinearLayoutManager(this@MainActivity)
            rcvOfError.adapter = RcvAdapter(stringlist)
            btnSubmit.setOnClickListener {
                playclicksound()
                takeInput()
                increaseStepCount()
                txtcount.text = "Step count $stepcount"
                checkCondition()
                rcvOfError.adapter?.notifyDataSetChanged()
                txtinput.text!!.clear()
                inputno = -1
            }
        }
    }

    private fun playclicksound() {
        setData.playsound(this@MainActivity, R.raw.clickbutton, soundFlag)
    }

    override fun onDestroy() {
        super.onDestroy()
        onBackPressCallback.remove()
    }

    private fun increaseStepCount() {
        if (inputno == -1) {
            Toast.makeText(this@MainActivity, "Input can't be blank", Toast.LENGTH_SHORT).show()
        } else {
            stepcount++
        }
    }

    private fun checkCondition() {
        binding.apply {
            when {
                inputno > rno -> {
                    txtmsg.text = "You entered a greater number"
                    val m = "You entered a greater number"
                    addToList("$inputno: $m")
                }

                inputno < rno -> {
                    if (inputno == -1) {
                        Toast.makeText(
                            this@MainActivity,
                            "Input can't be blank",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        txtmsg.text = "You entered a smaller number"
                        val m = "You entered a smaller number"
                        addToList("$inputno: $m")
                    }

                }


                else -> {
                    Toast.makeText(this@MainActivity, "YOU WON!!!!!!", Toast.LENGTH_SHORT).show()
                    val m = "You entered the correct number"

                    CoroutineScope(Dispatchers.IO).launch {
                        userDetailStage.storeUserLevel(level)
                        userDetailStage.storeUserStage(stage)
                    }

                    addToList("$inputno: $m")
                    txtmsg.text = "You entered the correct number"
                    txtRandomNo.text = "Answer is $rno!"
                    txtWon.text = "YOU WON!!!!!!"
                    txtcount.text = "You took $stepcount steps to complete Stage $stage"
                    cardStageOver.visibility = View.VISIBLE
                    playsound(this@MainActivity, R.raw.gamebonus, soundFlag)

                    btnGoToStage.setOnClickListener {
                        moveBackToStage()
                    }
                    btnGoToLevel.setOnClickListener {
                        moveBackToLevel()
                    }

                    if (stage < 5 ) {
                        Log.d("level&stage1", "level: $level Stage: $stage")
                        stage++
                        setData.setrange(this@MainActivity)
                        setData.setMaxSteps(this@MainActivity)
                        Log.d("level&stage2", "level: $level Stage: $stage")
                    } else {
                        Log.d("level&stage3", "level: $level Stage: $stage")
                        if (level < 5 && stage == 5) {
                            Log.d("level&stage4", "level: $level Stage: $stage")
                            level++
                            stage = 1
                            setData.setrange(this@MainActivity)
                            setData.setMaxSteps(this@MainActivity)
                            Log.d("level&stage5", "level: $level Stage: $stage")
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "Congratulations! All Levels Completed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

            }
        }
    }


    private fun moveBackToLevel() {
        playsound(this, R.raw.jump, soundFlag)
        startActivity(Intent(this@MainActivity, LevelActivity::class.java))
        finish()
    }

    private fun moveBackToStage() {
        playsound(this, R.raw.jump, soundFlag)
        startActivity(Intent(this@MainActivity, StageActivity::class.java))
        finish()
    }

    private fun generateRandomNo(maxran: Int): Int {
        return Random.nextInt(1, maxran)
    }

    private fun takeInput() {
        val input = binding.txtinput.text!!.trim().toString()
        if (maxstep > 0) {
            if (input.isNotEmpty()) {
                binding.txtlevel.text = "Level: $level"
                binding.txtstage.text = "Level: $stage"
                binding.txtstepleft.text = "$maxstep Left"
                inputno = input.toInt()
                maxstep--
            } else {
                binding.txtmsg.text = "Enter the correct number..."
            }
        } else {
            Toast.makeText(this@MainActivity, "No Chance Left", Toast.LENGTH_SHORT).show()
            binding.cardMovesOver.visibility = View.VISIBLE
            playsound(this, R.raw.outofmove, soundFlag)
            binding.btnMoveGoToStage.setOnClickListener {
                moveBackToStage()
            }
        }
    }

    private fun addToList(mymsg: String) {
        stringlist.add(mymsg)
    }
}

fun generateRandomNumber(): Int {
    return Random.nextInt(1, 101)
}

private fun userInput(): Int {
    print("Enter a number between 1 to 100: ")
    return readLine()!!.toInt()
}
