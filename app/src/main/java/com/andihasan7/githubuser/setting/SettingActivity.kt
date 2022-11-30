package com.andihasan7.githubuser.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andihasan7.githubuser.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    private lateinit var set_binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        set_binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(set_binding.root)
    }
}