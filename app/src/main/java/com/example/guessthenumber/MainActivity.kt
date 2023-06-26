package com.example.guessthenumber

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guessthenumber.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var stringlist = mutableListOf<String>()
    private lateinit var error: String
    var uno: Int = -1
    var i: Int = 0
    var rno: Int = -1
    private lateinit var m: String


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        rno = generateRandomNumber()
        binding.apply {
            rcvOfError.layoutManager = LinearLayoutManager(this@MainActivity)
            rcvOfError.adapter = RcvAdapter(stringlist)

            btnSubmit.setOnClickListener {
                takeinput()
                if (uno==-1){
                    Toast.makeText(this@MainActivity, "Input Cann't be Blank", Toast.LENGTH_SHORT).show()
                }
                else{
                    i++
                }



                txtcount.text = "Step count $i"


                when {
                    uno > rno -> {
                        txtmsg.text = "You entered a greater number"
                        m = "You entered a greater number"
                        addToList("$uno :$m")
                    }

                    uno < rno -> {
                        if (uno==-1){
                            Toast.makeText(this@MainActivity, "Input Cann't be Blank", Toast.LENGTH_SHORT).show()
                        }
                        else{ txtmsg.text = "You entered a smaller number"
                            m = "You entered a smaller number"
                            addToList("$uno :$m")}

                    }

                    else -> {
                        Toast.makeText(this@MainActivity, "YOU WON!!!!!!", Toast.LENGTH_SHORT)
                            .show()
                        m = "You entered the correct number"
                        addToList("$uno :$m")
                        txtmsg.text = "You entered the correct number"
                        txtRandomNo.text = "Random No is, $rno!"
                        txtWon.text = "YOU WON!!!!!!"
                        txtcount.text = "Step count $i"
                    }


                }
                rcvOfError.adapter?.notifyDataSetChanged()
                txtinput.text!!.clear()
                uno=-1

            }


        }

    }

    private fun takeinput() {

        val input = binding.txtinput.text!!.trim().toString()
        if (input.isNotEmpty()) {
            uno = input.toInt()
        } else {
//            uno = -1
            binding.txtmsg.text = "Enter the correct number..."
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

