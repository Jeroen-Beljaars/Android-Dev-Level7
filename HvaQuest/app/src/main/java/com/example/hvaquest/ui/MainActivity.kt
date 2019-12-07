package com.example.hvaquest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.hvaquest.R

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: QuestionViewModel
    private var goToPreviousQuestion: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(QuestionViewModel::class.java)
        initNavigation()
    }

    private fun initNavigation() {
        // The NavController
        val navController = findNavController(R.id.navHostFragment)

        // Connect the navHostFragment with the ActionBar.
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.locationClueFragment -> this.goToPreviousQuestion = true
                R.id.questionFragment -> this.goToPreviousQuestion = false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val navController = findNavController(R.id.navHostFragment)
        return when (item?.itemId) {
            android.R.id.home -> {
                if (this.goToPreviousQuestion) viewModel.previousQuestion()
                navController.navigateUp()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
