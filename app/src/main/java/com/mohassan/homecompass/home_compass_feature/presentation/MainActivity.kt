package com.mohassan.homecompass.home_compass_feature.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mohassan.homecompass.R
import com.mohassan.homecompass.core.utils.ShowCustomDialog.showCustomDialog
import com.mohassan.homecompass.databinding.ActivityHomeBinding
import com.mohassan.homecompass.home_compass_feature.presentation.interfaces.CustomDialogListener
import com.mohassan.homecompass.auth_feature.presentation.activity.IntroActivity
import com.mohassan.homecompass.core.utils.SharedPrefManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CustomDialogListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SharedPrefManager.init(this)
        setupBinding()
        setupActionBar()
        setupNavigation()
        setupViewModel()
        setupUI()
    }

    private fun setupBinding() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    private fun setupActionBar() {
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_chatBot,
                R.id.nav_searchMissing,
                R.id.nav_setting
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupNavigation() {
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        navView.setupWithNavController(navController)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.loadUserDetails()
    }

    private fun setupUI() {
        setupLogoutButton()
        observeUserDetails()
    }

    private fun setupLogoutButton() {
        binding.logoutBtn.setOnClickListener {
            showCustomDialog(R.drawable.ic_logout_24, this, this)
        }
    }

    private fun observeUserDetails() {
        viewModel.userDetailsLiveData.observe(this) { userDetails ->
            val headerName = binding.navView.getHeaderView(0).findViewById<TextView>(R.id.txt_header_username)
            headerName.text = "${userDetails.firstName} ${userDetails.lastName}"

            val headerEmail = binding.navView.getHeaderView(0).findViewById<TextView>(R.id.txt_header_email)
            headerEmail.text = userDetails.email
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.chatBot_menu -> {
                navigateToChatBot()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToChatBot() {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        navController.navigate(R.id.nav_chatBot)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onLogoutClicked() {
        val intent = Intent(this, IntroActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDeleteAccount() {
        //("Not yet implemented")
    }
}