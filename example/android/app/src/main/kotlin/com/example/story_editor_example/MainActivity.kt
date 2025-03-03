package com.example.story_editor_example

import android.content.Intent
import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {
    companion object {
        private const val CHANNEL = "story_editor"
    }

    private var methodChannel: MethodChannel? = null

    private val pendingResults =
        mutableMapOf<Int, MethodChannel.Result>() // Map to store pending results

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1000) {
            val result = pendingResults[1000] // Retrieve and remove the result
            if (result != null) { // Check if a result was pending
                if (resultCode == 1000) {
                    val newData = data?.getStringExtra("Result") ?: ""
                    result.success(newData)
                } else if (resultCode == RESULT_CANCELED) {
                    result.success("cancelled")
                }
            } else {
                Log.w("ActivityResult", "No pending result found for request code: $requestCode")
            }
        }
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        methodChannel =
            MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL) // Initialize here
        methodChannel?.setMethodCallHandler { call, result ->
            if (call.method == "openStoryEditor") {
                val intent = Intent(this, StoryCameraActivity::class.java)
                startActivityForResult(intent, 1000)

                // Store the result in a map with request code as key
                pendingResults[1000] = result
            } else {
                result.notImplemented()
            }
        }
    }

}
