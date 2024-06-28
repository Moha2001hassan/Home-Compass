package com.mohassan.homecompass.home_compass_feature.presentation.fragments.settings

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mohassan.homecompass.auth_feature.presentation.viewmodel.UserViewModel
import com.mohassan.homecompass.databinding.FragmentAccountBinding
import com.mohassan.homecompass.databinding.UserdataBottomSheetBinding
import com.mohassan.homecompass.home_compass_feature.presentation.viewmodel.UserDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Calendar

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var bottomSheetBinding: UserdataBottomSheetBinding
    private val viewModel: UserDetailsViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                bottomSheetBinding.imgUpdatePicker.setImageURI(it)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        bottomSheetBinding = UserdataBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupEditButton()

        lifecycleScope.launch {
            viewModel.userData.collect { userData ->
                userData?.let {
                    binding.apply {
                        tvAccountName.text = "${it.firstName} ${it.lastName}, hi👋"
                        tvEmail.text = it.email
                        tvUserName.text = it.username
                        Log.d("Hi", tvAccountName.text.toString())
                    }
                }
            }
        }

        userViewModel.isDonor.observe(viewLifecycleOwner) { isDonor ->
            Log.e("AccountFragment", "Is user donor?: $isDonor")
        }
    }

    private fun setupViewModel() {
        viewModel.selectedDateLiveData.observe(viewLifecycleOwner) { selectedDate ->
            bottomSheetBinding.tvDate.text = selectedDate
            Log.e("AccountFragment", selectedDate)
        }
    }

    private fun setupEditButton() {
        binding.btnEdit.setOnClickListener {
            showBottomSheetDialog()
        }
    }

    /*
    private fun setInitialUserData() {
        val initialUserData = viewModel.getUserData()
        binding.apply {
            tvAccountName.text = "${initialUserData.firstName} ${initialUserData.lastName}"
            tvEmail.text = initialUserData.email
            tvAddress.text = initialUserData.address
            tvPhoneNumber.text = initialUserData.phone
            tvBirthday.text = initialUserData.birthDate
            tvGender.text = initialUserData.gender
        }
    }
    */

    private fun showBottomSheetDialog() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.apply {
            setContentView(bottomSheetBinding.root)
            setCancelable(false)
        }
        setupBottomSheet(dialog)
        dialog.show()
    }

    private fun setupBottomSheet(dialog: BottomSheetDialog) {
        bottomSheetBinding.apply {
            btnDismiss.setOnClickListener { dialog.dismiss() }
            btnSaveData.setOnClickListener { saveUserData(dialog) }
            imgUpdatePicker.setOnClickListener { galleryLauncher.launch("image/*") }
            btnDatePicker.setOnClickListener { showDatePickerDialog() }
        }
    }

    private fun saveUserData(dialog: BottomSheetDialog) {
//        viewModel.updateUserData(
//            firstName = bottomSheetBinding.etFirstName.text.toString(),
//            lastName = bottomSheetBinding.etLastName.text.toString(),
//            email = bottomSheetBinding.etEmail.text.toString(),
//            gender = getSelectedGender(),
//            birthDate = bottomSheetBinding.tvDate.text.toString(),
//            address = bottomSheetBinding.etAddress.text.toString(),
//            phone = bottomSheetBinding.etPhone.text.toString()
//        )
        //setInitialUserData()
        dialog.dismiss()
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                viewModel.updateSelectedDate(selectedDate)
            },
            year,
            month,
            dayOfMonth
        ).show()
    }

//    private fun getSelectedGender(): String {
//        val checkedRadioButtonId = bottomSheetBinding.radioGroup.checkedRadioButtonId
//        return if (checkedRadioButtonId != -1) {
//            bottomSheetBinding.radioGroup.findViewById<RadioButton>(checkedRadioButtonId).text.toString()
//        } else {
//            "male"
//        }
//    }
}
