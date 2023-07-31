package com.example.example04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.Toast
import com.example.example04.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.activityMain)

        val channel = Channel<Int>()
        var backgroundScope = CoroutineScope(Dispatchers.Default + Job())

        val stopBtn = findViewById<Button>(R.id.stopBtn)
        val startBtn = findViewById<Button>(R.id.startBtn)
        val resumeBtn = findViewById<Button>(R.id.resumeBtn)
        val pauseBtn = findViewById<Button>(R.id.pauseBtn)

        var sum = 0L
        var isStop = true
        var isStart = false
        var isPause = false

        var mainScope = GlobalScope.launch(Dispatchers.Main){
            channel.consumeEach {
                var hour = it / 3600
                var min = (it - it / 3600 * 3600) / 60
                var sec = it % 60

                lateinit var str: String
                if (hour < 10){
                    str = "0" + hour.toString()
                }
                else{
                    str = hour.toString()
                }

                str += ":"

                if (min < 10) str += "0"
                str += min.toString()

                str += ":"

                if (sec < 10) str += "0"
                str += sec.toString()

                binding.time.text = str
            }
        }
        val delayTime = 1000L

        startBtn.setOnClickListener {
            if (isStop == false){
                Toast.makeText(this, "Stop watch is not stopped", Toast.LENGTH_SHORT).show()
            }
            else {
                if(backgroundScope.isActive) backgroundScope.cancel()
                backgroundScope = CoroutineScope(Dispatchers.Default + Job())
                backgroundScope.launch {

                    for (i in 1..2_000_000_000) {
                        delay(delayTime)
                        sum += 1
                        channel.send(sum.toInt())
                    }
                }
                stopBtn.isClickable = true
                isStop = false
                isStart = true
            }
        }
        stopBtn.setOnClickListener {
            if (isStart){
                isStop = true
                isStart = false

                backgroundScope.cancel()

                backgroundScope = CoroutineScope(Dispatchers.Default + Job())
                backgroundScope.launch{
                    sum = 0
                    channel.send(sum.toInt())
                }
            }

        }

        pauseBtn.setOnClickListener {
            if (isStart){
                backgroundScope.cancel()
                isPause = true
            }
        }

        resumeBtn.setOnClickListener { // 이어서 실행
            if (isStart && isPause) {
                var t_time = binding.time.text
                var hour = t_time.split(':')[0].toInt()
                var min = t_time.split(':')[1].toInt()
                var sec = t_time.split(':')[2].toInt()
                sum = hour * 3600L + min * 60 + sec
                backgroundScope.cancel()
                backgroundScope = CoroutineScope(Dispatchers.Default + Job())
                backgroundScope.launch {

                    for (i in 1..2_000_000_000) {
                        delay(delayTime)
                        sum += 1
                        channel.send(sum.toInt())
                    }
                }

                isPause = false
            }
        }


    }
}