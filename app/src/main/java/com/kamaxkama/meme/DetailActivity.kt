package com.kamaxkama.meme

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.kamaxkama.core.domain.model.Meme
import com.kamaxkama.meme.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.detailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail Meme"

        binding.detailToolbar.navigationIcon?.setTint(resources.getColor(android.R.color.white))

        val meme = intent.getParcelableExtra<Meme>(EXTRA_MEME)
        val fromFavorite = intent.getBooleanExtra(EXTRA_FROM_FAVORITE, false)

        if (fromFavorite) {
            binding.btnFavorite.text = "Hapus dari Favorite" // 🟡 ubah sesuai kebutuhan
        } else {
            binding.btnFavorite.text = "Tambahkan ke Favorite"
        }


        meme?.let { memeData ->
            showMeme(memeData)

            binding.btnFavorite.setOnClickListener {
                lifecycleScope.launch {
                    if (fromFavorite) {
                        viewModel.delete(memeData)
                        Toast.makeText(
                            this@DetailActivity,
                            "Dihapus dari favorite",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish() // ✅ Kembali ke daftar favorite
                    } else {
                        val isFavorite = viewModel.isFavorite(memeData.imageUrl)
                        viewModel.toggleFavorite(memeData)
                        val text = if (isFavorite) {
                            "Dihapus dari favorite"
                        } else {
                            "Ditambahkan ke favorite"
                        }
                        Toast.makeText(this@DetailActivity, text, Toast.LENGTH_SHORT).show()
                    }
                }
            }
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_MEME = "extra_meme"
        const val EXTRA_FROM_FAVORITE = "extra_from_favorite"
    }
}
