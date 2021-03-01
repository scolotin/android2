package com.example.android2.view.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android2.R
import com.example.android2.databinding.FragmentSettingsBinding
import com.example.android2.model.IS_ADULT

class SettingsFragment : Fragment() {

    private var _viewBinding: FragmentSettingsBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        inflater.inflate(R.layout.fragment_settings, container, false)
        _viewBinding = FragmentSettingsBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (savedInstanceState) {
            null -> {
                loadSetting()
            }
        }

        viewBinding.isAdult.setOnClickListener {
            saveSettings()
        }
    }

    private fun loadSetting() {
        activity?.let {
            viewBinding.isAdult.isChecked = it.getPreferences(Context.MODE_PRIVATE).getBoolean(IS_ADULT, false)
        }
    }

    private fun saveSettings() {
        activity?.let {
            with(it.getPreferences(Context.MODE_PRIVATE).edit()) {
                putBoolean(IS_ADULT, viewBinding.isAdult.isChecked)
                apply()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}