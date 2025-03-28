package com.example.practicpo.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.practicpo.databinding.ActivityIntentHandlerBinding


class NewPostResultContract : ActivityResultContract<Unit, String?>(){
    override fun createIntent(context: Context, input: Unit): Intent =
        Intent(context, ActivityIntentHandlerBinding::class.java)

    override fun parseResult(resultCode: Int, intent: Intent?): String? =
        if (resultCode == Activity.RESULT_OK) {
            intent?.getStringExtra(Intent.EXTRA_TEXT)
        } else {
            null
        }

}