package com.maghraby.hyperonenews.ui.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.viewModels
import com.google.android.material.textfield.TextInputEditText
import com.maghraby.hyperonenews.R
import com.maghraby.hyperonenews.ui.home.view.MainActivity
import com.maghraby.hyperonenews.ui.login.viewmodel.LoginViewModel
import com.maghraby.hyperonenews.ui.register.view.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    lateinit var loginBtn: Button
    lateinit var registerBtn: Button
    lateinit var usernameET: TextInputEditText
    lateinit var passwordET: TextInputEditText
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
            }else{
                Toast.makeText(this,getString(R.string.login_error), LENGTH_LONG).show()
            }
        }
    }

    private fun setupListeners() {
        loginBtn.setOnClickListener {
            viewModel.login(usernameET.text.toString(),passwordET.text.toString())
        }

        registerBtn.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }

    private fun setupUI() {
        loginBtn = findViewById(R.id.loginBtn)
        registerBtn = findViewById(R.id.registerBtn)
        usernameET = findViewById(R.id.usernameEt)
        passwordET = findViewById(R.id.passwordEt)
    }
}