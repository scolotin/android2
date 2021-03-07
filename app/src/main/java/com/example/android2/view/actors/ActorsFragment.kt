package com.example.android2.view.actors

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.android2.R
import com.example.android2.databinding.FragmentActorsBinding
import com.example.android2.model.Actor
import com.example.android2.model.OnActorsItemViewClickListener
import com.example.android2.view.maps.MapsFragment
import com.example.android2.viewmodel.MainFragmentVMContainer
import com.example.android2.viewmodel.MainFragmentViewModel

class ActorsFragment : Fragment() {
    private var _viewBinding: FragmentActorsBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val viewModel: MainFragmentViewModel by lazy {
        ViewModelProvider(this).get(MainFragmentViewModel::class.java)
    }

    private val actorsAdapter = ActorsFragmentAdapter(object : OnActorsItemViewClickListener {
        override fun onItemViewClick(actor: Actor) {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .add(R.id.container, MapsFragment.newInstance(Bundle().apply {
                        putParcelable(MapsFragment.BUNDLE_EXTRA, actor)
                    }))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        inflater.inflate(R.layout.fragment_actors, container, false)
        _viewBinding = FragmentActorsBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.actorsList.adapter = actorsAdapter

        viewModel.run {
            actorListLiveData.observe(viewLifecycleOwner, { renderActorsList(it) })
            getActorList()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun renderActorsList(mainFragmentContainer: MainFragmentVMContainer) {
        mainFragmentContainer.actorList?.let {
            actorsAdapter.setActorsList(it)
        }
    }

    companion object {
        fun newInstance() = ActorsFragment()
    }
}