package com.example.story_editor

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler


class StoryEditorPlugin : FlutterPlugin, MethodCallHandler, ActivityAware {

    private lateinit var channel: MethodChannel
    private lateinit var mContext: Context
    private var activity: Activity? = null
    private var activityResultLauncher: ActivityResultLauncher<Intent>? = null

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "story_editor")
        channel.setMethodCallHandler(this)
        mContext = flutterPluginBinding.applicationContext
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        if (call.method == "openStoryEditor") {
            val intent = Intent(mContext, StoryCameraActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            activityResultLauncher?.launch(intent)

        } else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
        activity = null
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        activity = binding.activity
        // *** KEY CHANGE: Check if activity is a ComponentActivity ***
        if (activity is ComponentActivity) {
            activityResultLauncher = (activity as ComponentActivity).registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                val data = result.data
                val message =
                    data?.getStringExtra("result_key") ?: "Story edited successfully" // Example
                channel.invokeMethod("storyEditorResult", message) // Notify Flutter
            }
        } else {
            channel.invokeMethod("storyEditorResult", "Error: Activity is not a ComponentActivity")
        }
    }

    override fun onDetachedFromActivityForConfigChanges() {

    }

    override fun onDetachedFromActivity() {
        activity = null
        activityResultLauncher = null
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        activity = binding.activity
        activity = binding.activity
        if (activity is ComponentActivity) {
            activityResultLauncher = (activity as ComponentActivity).registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                val data = result.data
                val message =
                    data?.getStringExtra("result_key") ?: "Story edited successfully"
                channel.invokeMethod("storyEditorResult", message)
            }
        } else {
            channel.invokeMethod("storyEditorResult", "Error: Activity is not a ComponentActivity")
        }
    }


}
