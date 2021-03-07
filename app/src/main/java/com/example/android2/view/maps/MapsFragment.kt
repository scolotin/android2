package com.example.android2.view.maps

import android.location.Address
import android.location.Geocoder
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.android2.R
import com.example.android2.model.Actor
import com.example.android2.viewmodel.MainFragmentVMContainer
import com.example.android2.viewmodel.MainFragmentViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class MapsFragment : Fragment() {

    private lateinit var map: GoogleMap

    private val viewModel: MainFragmentViewModel by lazy {
        ViewModelProvider(this).get(MainFragmentViewModel::class.java)
    }

    private val callback = OnMapReadyCallback { googleMap ->
       map = googleMap
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.actorDetailsLiveData.observe(viewLifecycleOwner, { showLocation(it) })

        val actor = arguments?.getParcelable<Actor>(BUNDLE_EXTRA)
        actor?.let {
            viewModel.getActorDetails(it.id)
        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun goToAddress(addresses: MutableList<Address>, view: View, searchText: String) {
        val location = LatLng(addresses[0].latitude, addresses[0].longitude)
        view.post {
            map.addMarker(MarkerOptions().position(location).title(searchText))
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15F))
        }
    }

    private fun showLocation(mainFragmentVMContainer: MainFragmentVMContainer) {
        val geoCoder = Geocoder(context)
        val name = mainFragmentVMContainer.actorDetails?.name
        val placeOfBirth = mainFragmentVMContainer.actorDetails?.place_of_birth
        Thread {
            try {
                val addresses = geoCoder.getFromLocationName(placeOfBirth, 1)
                if (addresses.size > 0) {
                    name?.let {
                        goToAddress(addresses, requireView(), it)
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }.start()
    }

    companion object {
        const val BUNDLE_EXTRA = "maps"

        fun newInstance(bundle: Bundle) = MapsFragment().apply {
            arguments = bundle
        }
    }
}