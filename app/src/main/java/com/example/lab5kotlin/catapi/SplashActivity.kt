package com.example.lab5kotlin.catapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.example.lab5kotlin.R
import com.example.lab5kotlin.additionaldata.BreedDTO
import com.example.lab5kotlin.additionaldata.NetworkService
import java.lang.Thread.sleep

class SplashActivity : AppCompatActivity() {

    private var changed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (savedInstanceState == null
            || !savedInstanceState.getBoolean("changed", false)
        ){
            Thread{
                while (NetworkService.getBreeds() == listOf<BreedDTO?>())
                changeToMA()
            }.start()
            Thread{
                sleep(2000)
                changeToMA()
            }.start()
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putBoolean("changed", changed)
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        changed = savedInstanceState.getBoolean("changed")
    }

    private fun changeToMA() {
        if (!changed) {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            changed = true
            finish()
        }
    }

}
