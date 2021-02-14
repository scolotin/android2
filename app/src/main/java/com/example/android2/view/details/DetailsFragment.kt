package com.example.android2.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android2.R
import com.example.android2.databinding.FragmentDetailedBinding
import com.example.android2.model.Film

class DetailsFragment : Fragment() {
    private lateinit var viewBinding: FragmentDetailedBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        inflater.inflate(R.layout.fragment_detailed, container, false)
        viewBinding = FragmentDetailedBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val film = arguments?.getParcelable<Film>(BUNDLE_EXTRA)
        film?.let {
            viewBinding.filmTitle.text = it.name
            viewBinding.metadata.text = it.year.toString()
            viewBinding.genre.text = it.genre
            viewBinding.rate.text = it.rate.toString()
            viewBinding.description.text = it.description
        }
    }

    companion object {
        const val BUNDLE_EXTRA = "film"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}