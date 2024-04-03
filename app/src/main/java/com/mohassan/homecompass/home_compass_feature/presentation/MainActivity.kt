package com.mohassan.homecompass.home_compass_feature.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.viewModels
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.mohassan.homecompass.R
import com.mohassan.homecompass.core.utils.ShowCustomDialog.showCustomDialog
import com.mohassan.homecompass.databinding.ActivityHomeBinding
import com.mohassan.homecompass.home_compass_feature.presentation.interfaces.CustomDialogListener
import com.mohassan.homecompass.auth_feature.presentation.activity.IntroActivity

class MainActivity : AppCompatActivity(), CustomDialogListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
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
        navView.setupWithNavController(navController)

        binding.logoutBtn.setOnClickListener {
            showCustomDialog(R.drawable.ic_logout_24, this, this)
        }

        viewModel.userName.observe(this) { userName ->
            binding.navView.getHeaderView(0)
                .findViewById<TextView>(R.id.txt_header_username).text = userName
        }

        viewModel.email.observe(this) { email ->
            binding.navView.getHeaderView(0)
                .findViewById<TextView>(R.id.txt_header_email).text = email
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.chatBot_menu -> {
                val navController = findNavController(R.id.nav_host_fragment_content_main)
                navController.navigate(R.id.nav_chatBot)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
        //TODO("Not yet implemented")
    }
}