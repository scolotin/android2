package com.example.android2.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android2.R
import com.example.android2.databinding.FragmentMainBinding
import com.example.android2.model.Film
import com.example.android2.model.OnItemViewClickListener
import com.example.android2.view.details.DetailsFragment
import com.example.android2.viewmodel.MainFragmentVMContainer
import com.example.android2.viewmodel.MainFragmentViewModel

class MainFragment : Fragment() {
    private lateinit var viewBinding: FragmentMainBinding
    private lateinit var viewModel: MainFragmentViewModel

    private val previewAdapter = MainFragmentAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(film: Film) {
            val manager = activity?.supportFragmentManager
            if (manager != null) {
                val bundle = Bundle()
                bundle.putParcelable(DetailsFragment.BUNDLE_EXTRA, film)
                manager.beginTransaction()
                    .add(R.id.container, DetailsFragment.newInstance(bundle))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        inflater.inflate(R.layout.fragment_main, container, false)
        viewBinding = FragmentMainBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.preview.adapter = previewAdapter

        viewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        viewModel.getFilmListLiveData().observe(viewLifecycleOwner, { renderFilmList(it) })
        viewModel.getFilmList()
    }

    override fun onDestroy() {
        previewAdapter.removeListener()
        super.onDestroy()
    }

    private fun renderFilmList(mainFragmentContainer: MainFragmentVMContainer) {
        previewAdapter.setFilmList(mainFragmentContainer.filmList)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}