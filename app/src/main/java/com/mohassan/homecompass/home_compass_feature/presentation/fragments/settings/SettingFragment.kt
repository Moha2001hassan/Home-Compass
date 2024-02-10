package com.mohassan.homecompass.home_compass_feature.presentation.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mohassan.homecompass.R
import com.mohassan.homecompass.core.utils.ShowCustomDialog.showCustomDialog
import com.mohassan.homecompass.databinding.FragmentSettingBinding
import com.mohassan.homecompass.home_compass_feature.presentation.viewmodel.SearchMissingViewModel

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val settingViewModel =
            ViewModelProvider(this)[SearchMissingViewModel::class.java]

        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.linearAccount.setOnClickListener {
            findNavController().navigate(R.id.action_nav_setting_to_accountFragment)
        }
        binding.linearReport.setOnClickListener {
            findNavController().navigate(R.id.action_nav_setting_to_reportFragment)
        }
        binding.linearNotifications.setOnClickListener {
            findNavController().navigate(R.id.action_nav_setting_to_notificationsFragment)
        }

        binding.ivBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.linearDeleteAccount.setOnClickListener {
            showCustomDialog(R.drawable.ic_delete_account, requireContext())
        }
        binding.linearLogOut.setOnClickListener {
            showCustomDialog(R.drawable.ic_logout_24, requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}