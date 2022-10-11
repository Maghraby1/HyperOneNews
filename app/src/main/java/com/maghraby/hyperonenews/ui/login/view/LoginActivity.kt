package com.maghraby.hyperonenews.ui.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import com.maghraby.hyperonenews.R
import com.maghraby.hyperonenews.ui.home.view.MainActivity
import com.maghraby.hyperonenews.ui.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    lateinit var loginBtn: Button
    lateinit var usernameET: TextView
    lateinit var passwordET: TextView
    private val viewModel : LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupUI()
        setupListeners()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.user.observe(this){
            if(it!=null) {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    private fun setupListeners() {
        loginBtn.setOnClickListener {
            viewModel.login(usernameET.text.toString(),passwordET.text.toString())
        }
    }

    private fun setupUI() {
        loginBtn = findViewById(R.id.loginBtn)
        usernameET = findViewById(R.id.usernameEt)
        passwordET = findViewById(R.id.passwordEt)
    }
}