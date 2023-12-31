package com.example.guessthenumber

import android.content.Context
import android.media.MediaPlayer
import android.widget.Toast

object setData {
    const val name: String = "name"
    var level: Int = 1
    var stage: Int = 1
    var range: Int = -1
    var maxstep: Int = -1
    var currentstage: Int = -0
    var currentlevel: Int = -0

    var soundFlag: Int = 1

    fun setMaxSteps(context: Context) {
        when (stage) {
            1 -> maxstep = 10
            2 -> maxstep = 9
            3 -> maxstep = 8
            4 -> maxstep = 7
            else -> {
                maxstep = 6
            }
        }
    }

    fun setrange(context: Context) {
        when (level) {
            1 -> range = 2
            2 -> range = 2
            3 -> range = 2
            4 -> range = 2
            else -> {
                range = 3
            }

        }
    }

    fun playsound(context: Context, sound: Int, musicFlag: Int) {
        //code to play sound
        if (musicFlag == 1) {
            var mediaPlayer = MediaPlayer.create(context, sound)
            mediaPlayer.start()
        }

    }
}