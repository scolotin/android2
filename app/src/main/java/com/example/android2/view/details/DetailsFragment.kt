package com.example.android2.view.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android2.R
import com.example.android2.databinding.FragmentDetailsBinding
import com.example.android2.model.Film
import com.example.android2.viewmodel.DetailsViewModel

class DetailsFragment : Fragment() {
    private var _viewBinding: FragmentDetailsBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        inflater.inflate(R.layout.fragment_details, container, false)
        _viewBinding = FragmentDetailsBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val film = arguments?.getParcelable<Film>(BUNDLE_EXTRA)
        film?.let {
            viewBinding.filmTitle.text = it.title
            viewBinding.metadata.text = it.release_date
            viewBinding.genre.text = it.genre
            viewBinding.rate.text = it.vote_average.toString()
            viewBinding.description.text = it.overview
            viewModel.loadNote(film)?.run {
                viewBinding.filmNote.setText(this, TextView.BufferType.EDITABLE)
            }
        }

        viewBinding.filmNote.setOnEditorActionListener { v, i, _ ->
            when (i) {
                EditorInfo.IME_ACTION_DONE -> {
                    film?.note = viewBinding.filmNote.text.toString()
                    viewModel.saveNote(film!!)
                    val imm =
                        v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                    true
                }
                else -> false
            }
        }
    }

    companion object {
        const val BUNDLE_EXTRA = "film"

        fun newInstance(bundle: Bundle) = DetailsFragment().apply {
            arguments = bundle
        }
    }
}