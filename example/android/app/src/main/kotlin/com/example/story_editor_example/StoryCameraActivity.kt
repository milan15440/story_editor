package com.example.story_editor_example

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import io.flutter.embedding.android.FlutterActivity

class StoryCameraActivity : FlutterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_story_camera_activity)

        val buttonGiveMeBack = this.findViewById<Button>(R.id.buttonHome)

        buttonGiveMeBack.setOnClickListener {
            val intent = Intent()
            intent.putExtra("Result", "Hello This result is coming from story_editor")
            activity.setResult(
                1000, intent
            )
            finish()
        }
    }
}