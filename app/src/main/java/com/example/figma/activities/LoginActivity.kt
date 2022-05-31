package com.example.figma.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.figma.DBHelper
import com.example.figma.R
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private lateinit var phoneNumber: EditText
    private lateinit var password: EditText
    private lateinit var textView: TextView
    private lateinit var login: Button
    private lateinit var numberTextField: TextInputLayout
    private lateinit var passwordTextField: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        phoneNumber = findViewById(R.id.phoneNumber)
        password = findViewById(R.id.password)
        textView = findViewById(R.id.textView)
        login = findViewById(R.id.Login)
        numberTextField = findViewById(R.id.numberTextField)
        passwordTextField = findViewById(R.id.passwordTextField)


        login.setOnClickListener {

            var login = true

            if (phoneNumber.text.isNotEmpty() &&
                password.text.isNotEmpty()
            ) {


                if ((phoneNumber.text.toString().length != 10)) {
                    login = false
                    numberTextField.error = "Invalid PhoneNumber!"
                    removeError(numberTextField, phoneNumber)

                }

                if (password.text.toString().length < 4) {

                    login = false
                    passwordTextField.error = "Password should contain atleast 4 characters!"
                    removeError(passwordTextField, password)


                }


                if (login && userExist(phoneNumber.text.toString(), password.text.toString())) {

                    Intent(this, MainActivity::class.java).also {
                        startActivity(it)
                    }


                }


            } else {

                if(phoneNumber.text.isEmpty()) {
                    numberTextField.error = "Cannot be empty!"
                    removeError(numberTextField, phoneNumber)
                }

                if(password.text.isEmpty()) {
                    passwordTextField.error = "Cannot be empty!"
                    removeError(passwordTextField, password)
                }
            }
        }

        textView.setOnClickListener {

            Intent(this, SignUpActivity::class.java).also {
                startActivity(it)
            }
        }


    }

    private fun userExist(number: String, password: String): Boolean {

        val db = DBHelper(this, null)

        val cursor = db.getName()

        while (cursor.moveToNext()) {

            if(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NUMBER_COL)) == number) {

                if(password == cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.PASSWORD_COL))) {
                    Toast.makeText(this, "User Found", Toast.LENGTH_SHORT).show()
                    return true
                }

                else {
                    passwordTextField.error = "Incorrect Password!"
                    return false
                }


            }

        }

        Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
        return false

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