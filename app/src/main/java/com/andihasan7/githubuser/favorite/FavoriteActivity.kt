package com.andihasan7.githubuser.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andihasan7.githubuser.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var fav_binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fav_binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(fav_binding.root)
    }
}