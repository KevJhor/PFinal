package com.example.pfinal

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user: EditText = findViewById(R.id.txtUser)
        val pass: EditText = findViewById(R.id.txtPass)
        val btnLogin: Button = findViewById(R.id.btnLog)

        //Firebase instancia
        val auth = FirebaseAuth.getInstance()

        //Login
        btnLogin.setOnClickListener {
            val correo = user.text.toString()
            val clave = pass.text.toString()

            auth.signInWithEmailAndPassword(correo,clave)
                .addOnCompleteListener(this){task->
                    if(task.isSuccessful){
                        Snackbar
                            .make(
                                findViewById(android.R.id.content)
                                ,"Inicio de sesión exitoso"
                                , Snackbar.LENGTH_LONG
                            ).show()
                        startActivity(Intent(this, EmpleadoActivity::class.java))
                    }else {
                        Snackbar
                            .make(
                                findViewById(android.R.id.content)
                                ,"Credenciales inválidas"
                                , Snackbar.LENGTH_LONG
                            ).show()
                    }
                }
        }

        //Register

        val btnRegister:Button = findViewById(R.id.btnRegister)
        btnRegister.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}