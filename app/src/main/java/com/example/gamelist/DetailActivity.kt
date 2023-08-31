package com.example.gamelist

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.gamelist.databinding.ActivityDetailBinding


@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val DATA_GAME = "data_game"
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Game>(DATA_GAME)
        if (data != null) {
            binding.imgGame.setImageResource(data.photo)
            binding.tvGameTitle.text = data.title
            binding.tvGameDescription.text = data.description
            binding.tvDetailPrice.text = data.price
            binding.tvDetailRelease.text = data.release
        }

        supportActionBar?.title = data?.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val shareButton: Button = findViewById(R.id.action_share)

        shareButton.setOnClickListener {
            val data = intent.getParcelableExtra<Game>(DATA_GAME)

            if (data != null) {
                shareDetail(data.title, data.description)
            } else {
                showToast("Data game tidak ditemukan")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun shareDetail(nameGame: String, informations: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        val detailText = "Informasi detail game:\nNama: $nameGame\n$informations"
        shareIntent.putExtra(Intent.EXTRA_TEXT, detailText)
        startActivity(Intent.createChooser(shareIntent, "Bagikan informasi melalui:"))
    }
}