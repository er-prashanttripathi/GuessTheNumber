package com.example.guessthenumber

import android.content.Context
import android.widget.Toast

object setData {
    const val name:String="name"
    var level:Int=1
    var stage:Int=1
    var range:Int=-1
    var maxstep:Int=-1
    var currentstage:Int=-1
    var currentlevel:Int=-1

     fun setMaxSteps(context: Context) {
        when (stage) {
            1 -> maxstep = 10
            2 -> maxstep = 9
            3 -> maxstep = 8
            4 -> maxstep = 7
            5 -> maxstep = 6
            else -> {
                Toast.makeText(context, "Stage ${stage} Complete", Toast.LENGTH_SHORT).show()
            }
        }
    }

     fun setrange(context: Context) {
        when (level) {
            1 -> range = 2
            2 -> range = 2
            3 -> range = 3
            4 -> range = 4
            5 -> range = 5
            else -> {
                Toast.makeText(context, "Game Over", Toast.LENGTH_SHORT).show()
            }

        }
    }
}