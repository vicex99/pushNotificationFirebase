package net.victor.firebasepushnotifcation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getMessage(notification: String){
        Toast.makeText(this, "", Toast.LENGTH_SHORT)
    }
}

