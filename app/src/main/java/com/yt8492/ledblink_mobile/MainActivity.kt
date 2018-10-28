package com.yt8492.ledblink_mobile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()
        var switchStatus = false

        switch1.setOnCheckedChangeListener { _, isChecked ->
            switchStatus = isChecked
        }

        button.setOnClickListener { _ ->
            Log.d("hoge", "clicked")
            val values = mapOf<String, Any?>(
                "led" to switchStatus,
                "text" to editText.text.toString()
            )
            db.collection("devices")
                .document("pi1")
                .set(values)
                .addOnSuccessListener {
                    Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
