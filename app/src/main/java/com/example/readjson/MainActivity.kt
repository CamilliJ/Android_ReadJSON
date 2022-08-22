package com.example.readjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var texto: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        texto = findViewById(R.id.textview)
        jsonLoad()
    }

    fun jsonLoad(){

        val jsonData = applicationContext.resources.openRawResource(
            applicationContext.resources.getIdentifier(
                "json",
                "raw",
                applicationContext.packageName
            )
        ).bufferedReader().use {it.readText()}

        val outputString = JSONObject(jsonData)
        Log.d("Tag_Error", "Houve um Erro $outputString")

        val users = outputString.getJSONArray("users") as JSONArray

        for ( i in 0 until users.length()){
            val id = users.getJSONObject(i).get("id")
            val name = users.getJSONObject(i).get("name")
            val cpf = users.getJSONObject(i).get("CPF")
            val senha = users.getJSONObject(i).get("senha")

            val data = "\n\nID: $id \nLogin: $name \nCPF: $cpf \nSenha: $senha"
            val previus = texto.text
            texto.text = previus.toString() + data
        }
    }
}