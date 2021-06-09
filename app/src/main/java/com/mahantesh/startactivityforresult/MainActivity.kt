package com.mahantesh.startactivityforresult

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        private const val FIRST_ACTIVITY_REQUEST_CODE = 1;
        private const val SECOND_ACTIVITY_REQUEST_CODE = 2;

        const val NAME = "name"
        const val EMAIL = "email"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLaunchFirstActivity.setOnClickListener {
            val intent = Intent(this, FirstActivity::class.java)
            startActivityForResult(intent, FIRST_ACTIVITY_REQUEST_CODE)
        }

        btnLaunchSecondActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){
            if (requestCode == FIRST_ACTIVITY_REQUEST_CODE){
                tvFirstActivityResult.text = "First Activity Result Success"
            } else if (requestCode == SECOND_ACTIVITY_REQUEST_CODE){
                if (data != null){
                    val name = data.getStringExtra(NAME)
                    val email = data.getStringExtra(EMAIL)
                    tvSecondActivityResult.text = "$name => $email"
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED){
            Log.e("Cancelled", "onActivityResult: Cancelled")
            Toast.makeText(this@MainActivity, "Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}