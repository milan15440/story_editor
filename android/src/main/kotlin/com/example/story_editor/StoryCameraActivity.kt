package com.example.story_editor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button


class StoryCameraActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_story_camera_activity)

        val buttonGiveMeBack = this.findViewById<Button>(R.id.buttonHome)

        buttonGiveMeBack.setOnClickListener {
            val intent = Intent()
            intent.putExtra("Result", "Coming from story camera")
            setResult(
                1000, intent
            )
            finish()
        }
    }
}