package com.example.figma.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.figma.DBHelper
import com.example.figma.R
import com.google.android.material.textfield.TextInputLayout
import com.hbb20.CountryCodePicker

class SignUpActivity : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var phoneNumber: EditText
    private lateinit var password: EditText
    private lateinit var countryCode: CountryCodePicker
    private lateinit var confirmPassword: EditText
    private lateinit var textView: TextView
    private lateinit var register: Button
    private lateinit var eMail: EditText
    private lateinit var nameTextField: TextInputLayout
    private lateinit var emailTextField: TextInputLayout
    private lateinit var numberTextField: TextInputLayout
    private lateinit var passwordTextField: TextInputLayout
    private lateinit var confirmTextField: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)



        name = findViewById(R.id.name)
        phoneNumber = findViewById(R.id.phoneNumber)
        password = findViewById(R.id.password)
        confirmPassword = findViewById(R.id.confirmPassword)
        textView = findViewById(R.id.textView)
        register = findViewById(R.id.register)
        eMail = findViewById(R.id.eMail)
        countryCode = findViewById(R.id.countryCode)
        nameTextField = findViewById(R.id.nameTextField)
        emailTextField = findViewById(R.id.emailTextField)
        numberTextField = findViewById(R.id.numberTextField)
        passwordTextField = findViewById(R.id.passwordTextField)
        confirmTextField = findViewById(R.id.confirmTextField)

        register.setOnClickListener {

            var login = true

            nameTextField.setOnClickListener {
                nameTextField.error = null
            }

            if(name.text.isNotEmpty() && phoneNumber.text.isNotEmpty() &&
                password.text.isNotEmpty() && confirmPassword.text.isNotEmpty()) {

                if(name.text.toString().length <= 3) {
                    login = false
                    nameTextField.error = "Name is too Short!"

                    removeError(nameTextField, name)

                }

                if (!(Patterns.EMAIL_ADDRESS.matcher(eMail.text.toString().trim()).matches())) {

                    login = false
                    emailTextField.error = "Invalid Email Address!"

                    removeError(emailTextField, eMail)

                }



                if ((phoneNumber.text.toString().length < 10)) {
                    login = false
                    numberTextField.error = "Invalid PhoneNumber!"

                    removeError(numberTextField, phoneNumber)

                }

                if(password.text.toString().length >= 4) {

                    if(password.text.toString() != confirmPassword.text.toString()) {
                        login = false
                        confirmTextField.error = "Confirm Password is different!"

                        removeError(confirmTextField, confirmPassword)

                    }

                }

                else {
                    login = false
                    passwordTextField.error = "Password should contain atleast 4 characters!"

                    removeError(passwordTextField, password)
                    
                }


                if(login) {

                    if(!userExist(phoneNumber.text.toString())) {
                        addName()
                        Intent(this, MainActivity::class.java).also {
                            startActivity(it)
                        }

                    }

                    else {
                        Toast.makeText(this, "User already exist with the given phoneNumber!", Toast.LENGTH_SHORT).show()
                    }

                }


            }

            else {

                if(name.text.isEmpty()) {
                    nameTextField.error = "Cannot be empty!"
                    removeError(nameTextField, name)
                }

                if(eMail.text.isEmpty()) {
                    emailTextField.error = "Cannot be empty!"
                    removeError(emailTextField, eMail)
                }

                if(phoneNumber.text.isEmpty()) {
                    numberTextField.error = "Cannot be empty!"
                    removeError(numberTextField, phoneNumber)
                }

                if(password.text.isEmpty()) {
                    passwordTextField.error = "Cannot be empty!"
                    removeError(passwordTextField, password)
                }

                if(confirmPassword.text.isEmpty()) {
                    confirmTextField.error = "Cannot be empty!"
                    removeError(confirmTextField, confirmPassword)
                }


            }
        }

        textView.setOnClickListener{

            Intent(this, LoginActivity::class.java).also {
                startActivity(it)
            }
        }


    }



    private fun userExist(number: String): Boolean {

        val db = DBHelper(this, null)

        val cursor = db.getName()

        while (cursor.moveToNext()) {

            if(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NUMBER_COL)) == number) {

                return true

            }

        }

        return false

    }

    private fun addName() {

        val fullName = name.text.toString()
        val email = eMail.text.toString()
//        val number = countryCode.fullNumberWithPlus.toString() + phoneNumber.text.toString()
        val number = phoneNumber.text.toString()
        val pwd = password.text.toString()
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()

        val db = DBHelper(this, null)
        db.addName(fullName, email, number, pwd)

        Toast.makeText(this, "${name.text.toString()} added to Database", Toast.LENGTH_SHORT).show()

        name.text.clear()
        eMail.text.clear()
        phoneNumber.text.clear()
        password.text.clear()
        confirmPassword.text.clear()

    }

    private fun removeError(nameTextField: TextInputLayout, name: EditText) {

        name.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                nameTextField.error = null
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

    }
}