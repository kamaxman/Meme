package com.kamaxkama.meme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.kamaxkama.core.domain.model.Meme
import com.kamaxkama.meme.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // GUNAKAN Toolbar dari layout, bukan bawaan theme
        setSupportActionBar(binding.detailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = "Detail Meme"


        binding.detailToolbar.navigationIcon?.setTint(resources.getColor(android.R.color.white))

        val meme = intent.getParcelableExtra<Meme>(EXTRA_MEME)
        meme?.let {
            showMeme(it)
        }
    }


    private fun showMeme(meme: Meme) {
        binding.tvTitle.text = meme.title
        binding.tvSubreddit.text = "r/${meme.subreddit}"
        binding.tvAuthor.text = "by u/${meme.author}"
        Glide.with(this)
            .load(meme.imageUrl)
            .into(binding.imgDetail)
    }

    companion object {
        const val EXTRA_MEME = "extra_meme"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

}
