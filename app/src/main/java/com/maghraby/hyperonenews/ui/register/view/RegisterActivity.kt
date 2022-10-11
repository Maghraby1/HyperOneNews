package com.maghraby.hyperonenews.ui.register.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.viewModels
import com.google.android.material.textfield.TextInputEditText
import com.maghraby.hyperonenews.R
import com.maghraby.hyperonenews.data.database.entity.UserEntity
import com.maghraby.hyperonenews.ui.home.view.MainActivity
import com.maghraby.hyperonenews.ui.register.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    lateinit var signupBtn: Button
    lateinit var nameEt : TextInputEditText
    lateinit var usernameEt : TextInputEditText
    lateinit var passwordEt : TextInputEditText
    lateinit var confirmPasswordEt : TextInputEditText

    private val viewModel: RegisterViewModel by viewModels()

    var initialObserve = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setupUI()
        setupListeners()
        setupObservers()
    }
    private fun setupObservers() {
        viewModel.isSuccess.observe(this){
            if(initialObserve){
                initialObserve = false
                return@observe
            }
            if(it){
                startActivity(Intent(this,MainActivity::class.java))
            }else{
                makeToast(getString(R.string.register_error))
            }
        }
    }
    private fun setupListeners() {
        signupBtn.setOnClickListener {
            if(validate()){
                viewModel.addUser(UserEntity(0, usernameEt.text.toString(),nameEt.text.toString(),passwordEt.text.toString()))
            }
        }
    }
    private fun makeToast(text: String){
        Toast.makeText(this,text,LENGTH_LONG).show()
    }
    private fun validate() : Boolean {
        if(nameEt.text.toString().isEmpty()){
            makeToast(getString(R.string.register_name_error))
            return false
        }
        if(usernameEt.text.toString().isEmpty()){
            makeToast(getString(R.string.register_username_error))
            return false
        }
        if(passwordEt.text.toString().isEmpty() || confirmPasswordEt.text.toString().isEmpty()){
            makeToast(getString(R.string.register_password_error))
            return false
        }
        if(!passwordEt.text.toString().equals(confirmPasswordEt.text.toString())){
            makeToast(getString(R.string.register_password_confirmation_error))
            return false
        }
        return true
    }
    private fun setupUI() {
        signupBtn = findViewById(R.id.signupBtn)
        nameEt = findViewById(R.id.nameEt)
        usernameEt = findViewById(R.id.usernameEt)
        passwordEt = findViewById(R.id.passwordEt)
        confirmPasswordEt = findViewById(R.id.confirm_passwordEt)
    }
}