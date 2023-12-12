package com.example.pfinal

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pfinal.adapter.EmpleadoAdapter
import com.example.pfinal.model.Empleado
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EmpleadoActivity : AppCompatActivity() {

    private lateinit var rvEmpleado: RecyclerView
    private lateinit var empleados: List<Empleado>
    private lateinit var empleadoAdapter: EmpleadoAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empleado)
        val nombre: EditText = findViewById(R.id.txtName)
        val pais: EditText = findViewById(R.id.txtCountry)
        val fechaI: EditText = findViewById(R.id.txtFecha)
        val URL: EditText = findViewById(R.id.txtURL)
        val btnRegister: Button = findViewById(R.id.btnSaveRegister)
        val btnR: Button = findViewById(R.id.btnR)
        rvEmpleado = findViewById(R.id.rvEmpleado)

        CargarData()

        btnRegister.setOnClickListener{
            val emple = Empleado(1,nombre.text.toString(), pais.text.toString() ,fechaI.text.toString(), URL.text.toString())
            EnviarComentario(emple)
        }
    }

    val db = FirebaseFirestore.getInstance()
    val collectionRef = db.collection("empleados")

    fun EnviarComentario(emp: Empleado){

        collectionRef.add(emp)
            .addOnSuccessListener { documentReference ->

            }
            .addOnFailureListener { e ->

            }

    }

    fun CargarData(){
        db.collection("empleados")
            .addSnapshotListener { snap, e ->
                if (e != null) {
                    Log.i("ERROR", "Ocurrió un error")
                    return@addSnapshotListener
                }

                empleados = snap!!.documents.map { document ->
                    Empleado(
                        document["id"].toString().toInt(),
                        document["nombre"].toString(),
                        document["pais"].toString(),
                        document["fechaI"].toString(),
                        document["Url"].toString()
                    )
                }

                rvEmpleado.adapter = EmpleadoAdapter(empleados)
                rvEmpleado.layoutManager = LinearLayoutManager(this)
            }
    }

}