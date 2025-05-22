package com.kamaxkama.meme.ui

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kamaxkama.meme.DetailActivity
import com.kamaxkama.meme.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMemes.layoutManager = LinearLayoutManager(requireContext())

        // ✅ Tampilkan data dan tambahkan klik untuk buka detail
        lifecycleScope.launch {
            viewModel.memes.collectLatest { memes ->
                if (memes.isNotEmpty()) {
                    binding.rvMemes.adapter = MemeAdapter(memes) { meme ->
                        val intent = Intent(requireContext(), DetailActivity::class.java)
                        intent.putExtra(DetailActivity.EXTRA_MEME, meme)
                        startActivity(intent)
                    }
                }
            }
        }

        // ✅ Tampilkan indikator loading
        lifecycleScope.launch {
            viewModel.isLoading.collectLatest { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }

        // ✅ Tampilkan pesan error
        lifecycleScope.launch {
            viewModel.error.collectLatest { error ->
                error?.let {
                    Toast.makeText(requireContext(), "Error: $it", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
