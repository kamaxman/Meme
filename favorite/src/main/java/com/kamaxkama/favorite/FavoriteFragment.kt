package com.kamaxkama.favorite

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView

class FavoriteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val textView = TextView(requireContext())
        textView.text = "Ini dari modul Favorite"
        textView.textSize = 24f
        return textView
    }
}

