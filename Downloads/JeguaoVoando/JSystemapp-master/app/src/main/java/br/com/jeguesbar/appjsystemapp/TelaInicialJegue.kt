package br.com.jeguesbar.appjsystemapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager

class TelaInicialJegue : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial_jegue)

        val background = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(2000)

                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }

    }



