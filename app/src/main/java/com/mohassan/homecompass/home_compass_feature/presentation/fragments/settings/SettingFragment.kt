package com.mohassan.homecompass.home_compass_feature.presentation.fragments.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mohassan.homecompass.R
import com.mohassan.homecompass.auth_feature.presentation.activity.IntroActivity
import com.mohassan.homecompass.auth_feature.presentation.viewmodel.UserViewModel
import com.mohassan.homecompass.core.utils.ShowCustomDialog.showCustomDialog
import com.mohassan.homecompass.databinding.FragmentSettingBinding
import com.mohassan.homecompass.home_compass_feature.presentation.interfaces.CustomDialogListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment(), CustomDialogListener {

    private var _binding: FragmentSettingBinding? = null
    private val viewModel :UserViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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
            showCustomDialog(R.drawable.ic_delete_account, requireContext(),this)
        }
//        binding.linearLogOut.setOnClickListener {
//            showCustomDialog(R.drawable.ic_logout_24, requireContext(),this)
//        }
        binding.linearLogOut.setOnClickListener {
            viewModel.logout()
            val intent = Intent(requireActivity(), IntroActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onLogoutClicked() {
        val intent = Intent(requireActivity(), IntroActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    override fun onDeleteAccount() {
        TODO("Not yet implemented")
    }
}