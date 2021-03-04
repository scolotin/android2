package com.example.android2.view.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android2.R
import com.example.android2.databinding.FragmentHistoryBinding
import com.example.android2.viewmodel.HistoryViewModel
import com.example.android2.viewmodel.MainVMContainer

class HistoryFragment : Fragment() {

    private var _viewBinding: FragmentHistoryBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val viewModel: HistoryViewModel by lazy {
        ViewModelProvider(this).get(HistoryViewModel::class.java)
    }
    private val historyAdapter: HistoryFragmentAdapter by lazy {
        HistoryFragmentAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        inflater.inflate(R.layout.fragment_history, container, false)
        _viewBinding = FragmentHistoryBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.history.adapter = historyAdapter

        viewModel.historyLiveData.observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getAllHistory()
    }

    private fun renderData(mainContainer: MainVMContainer) {
        historyAdapter.setData(mainContainer.filmList)
    }

    companion object {
        fun newInstance() = HistoryFragment()
    }
}