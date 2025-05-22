package com.kamaxkama.favorite

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kamaxkama.favorite.databinding.FragmentFavoriteBinding
import com.kamaxkama.meme.ui.MemeAdapter
import com.kamaxkama.core.domain.repository.FavoriteRepository
import com.kamaxkama.core.di.FavoriteEntryPoint
import com.kamaxkama.core.domain.usecase.GetFavoriteMemesUseCase
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FavoriteViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val entryPoint = EntryPointAccessors.fromApplication(
            context.applicationContext,
            FavoriteEntryPoint::class.java
        )
        val repository: FavoriteRepository = entryPoint.favoriteRepository()


        val useCase = GetFavoriteMemesUseCase(repository)
        viewModel = FavoriteViewModel(useCase)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("FavoriteFragment", "onViewCreated dipanggil")
        binding.rvFavorites.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            viewModel.favorites.collectLatest { list ->
                if (list.isEmpty()) {
                    binding.tvEmpty.visibility = View.VISIBLE
                    binding.rvFavorites.visibility = View.GONE
                } else {
                    binding.tvEmpty.visibility = View.GONE
                    binding.rvFavorites.visibility = View.VISIBLE

                    binding.rvFavorites.adapter = MemeAdapter(list) { meme ->
                        val intent = android.content.Intent(requireContext(), com.kamaxkama.meme.DetailActivity::class.java)
                        intent.putExtra(com.kamaxkama.meme.DetailActivity.EXTRA_MEME, meme)
                        intent.putExtra(com.kamaxkama.meme.DetailActivity.EXTRA_FROM_FAVORITE, true)
                        startActivity(intent)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
