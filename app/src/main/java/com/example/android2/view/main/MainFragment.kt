package com.example.android2.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android2.R
import com.example.android2.databinding.FragmentMainBinding
import com.example.android2.model.*
import com.example.android2.view.details.DetailsFragment
import com.example.android2.viewmodel.MainVMContainer
import com.example.android2.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private var _viewBinding: FragmentMainBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private val previewAdapter = MainFragmentAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(film: Film) {
            viewModel.saveFilmToDB(film)
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .add(R.id.container, DetailsFragment.newInstance(Bundle().apply {
                        putParcelable(DetailsFragment.BUNDLE_EXTRA, film)
                    }))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        inflater.inflate(R.layout.fragment_main, container, false)
        _viewBinding = FragmentMainBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.preview.adapter = previewAdapter

        viewModel.run {
            filmListLiveData.observe(viewLifecycleOwner, { renderFilmList(it) })
            getFilmList()
        }
    }

    override fun onDestroy() {
        previewAdapter.removeListener()

        super.onDestroy()
    }

    private fun renderFilmList(mainContainer: MainVMContainer) {
        previewAdapter.setFilmList(mainContainer.filmList)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}