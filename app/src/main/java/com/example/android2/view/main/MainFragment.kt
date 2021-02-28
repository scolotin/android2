package com.example.android2.view.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.android2.R
import com.example.android2.databinding.FragmentMainBinding
import com.example.android2.model.*
import com.example.android2.view.details.DetailsFragment
import com.example.android2.viewmodel.MainFragmentVMContainer
import com.example.android2.viewmodel.MainFragmentViewModel

class MainFragment : Fragment() {
    private lateinit var viewBinding: FragmentMainBinding
    private val viewModel: MainFragmentViewModel by lazy {
        ViewModelProvider(this).get(MainFragmentViewModel::class.java)
    }

    private val loadResultsReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.getStringExtra(MAIN_LOAD_RESULT_EXTRA)) {
                MAIN_DATA_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
                MAIN_REQUEST_ERROR_EXTRA -> TODO(PROCESS_ERROR)
                MAIN_REQUEST_ERROR_MESSAGE_EXTRA -> TODO(PROCESS_ERROR)
                MAIN_URL_MALFORMED_EXTRA -> TODO(PROCESS_ERROR)
                MAIN_RESPONSE_SUCCESS_EXTRA -> intent.getParcelableArrayListExtra<Film>(MAIN_FILM_LIST_EXTRA)?.let {
                    MainFragmentVMContainer(it)
                }?.let {
                    renderFilmList(it)
                }
                else -> TODO(PROCESS_ERROR)
            }
        }
    }

    private val previewAdapter = MainFragmentAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(film: Film) {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            LocalBroadcastManager.getInstance(it)
                .registerReceiver(loadResultsReceiver, IntentFilter(MAIN_INTENT_FILTER))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        inflater.inflate(R.layout.fragment_main, container, false)
        viewBinding = FragmentMainBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.preview.adapter = previewAdapter

        context?.let {
            it.startService(Intent(it, MainService::class.java))
        }
    }

    override fun onDestroy() {
        previewAdapter.removeListener()

        context?.let {
            LocalBroadcastManager.getInstance(it).unregisterReceiver(loadResultsReceiver)
        }

        super.onDestroy()
    }

    private fun renderFilmList(mainFragmentContainer: MainFragmentVMContainer) {
        previewAdapter.setFilmList(mainFragmentContainer.filmList)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}