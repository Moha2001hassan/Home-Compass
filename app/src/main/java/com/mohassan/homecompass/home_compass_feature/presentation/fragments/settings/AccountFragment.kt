package com.mohassan.homecompass.home_compass_feature.presentation.fragments.settings

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mohassan.homecompass.R
import com.mohassan.homecompass.databinding.FragmentAccountBinding
import com.mohassan.homecompass.home_compass_feature.presentation.viewmodel.FeedViewModel
import com.mohassan.homecompass.home_compass_feature.presentation.viewmodel.UserDetailsViewModel
import de.hdodenhof.circleimageview.CircleImageView
import java.util.Calendar

class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private lateinit var viewModel: UserDetailsViewModel

    //private val calendar = Calendar.getInstance()
    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { _ -> }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupEditButton()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[UserDetailsViewModel::class.java]
        viewModel.userDetailsLiveData.observe(viewLifecycleOwner) { userDetails ->
            val data = "email"
            Log.d("TestAccountFragment", data)
        }
    }

    private fun setupEditButton() {
        binding.btnEdit.setOnClickListener {
            showBottomSheetDialog()
        }
    }

    @SuppressLint("InflateParams")
    private fun showBottomSheetDialog() {
        val dialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.userdata_bottom_sheet, null)
        val btnClose = view.findViewById<Button>(R.id.idBtnDismiss)
        val btnUpdateData = view.findViewById<Button>(R.id.idBtnPost)
        val btnDatePicker = view.findViewById<Button>(R.id.btnDatePicker)
        val imgUpdatePicker = view.findViewById<CircleImageView>(R.id.imgUpdatePicker)

        btnClose.setOnClickListener { dialog.dismiss() }
        btnUpdateData.setOnClickListener {
            //publishPost(view)
            dialog.dismiss()
        }
        imgUpdatePicker.setOnClickListener {
            galleryLauncher.launch("image/*")
        }
        btnDatePicker.setOnClickListener {
            showDatePickerDialog()
        }

        dialog.setCancelable(false)
        dialog.setContentView(view)
        dialog.show()
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                // Do something with the selected date
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"

                // TODO
                Log.d("test",selectedDate)
            },
            year,
            month,
            dayOfMonth
        )
        datePickerDialog.show()
    }

}