package com.example.lab6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()
        super.onStart()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater = menuInflater
        inflater.inflate(R.menu.custom_menu, menu)
        return true
    }

    fun calculator(view: View) {
        val price = findViewById<EditText>(R.id.et1)
        val quantity = findViewById<EditText>(R.id.et2)
        val iva = findViewById<CheckBox>(R.id.iva)
        val textview = findViewById<TextView>(R.id.tv1)

        val price1 : Int = price.text.toString().toInt()
        val quantity1 : Int = quantity.toString().toInt()
        val total : Int

        if (price.text.toString() == "")
        {Toast.makeText(this, "coloque um preço", Toast.LENGTH_SHORT).show()}
        else if(quantity.text.toString() == "")
        {Toast.makeText(this, "coloque uma quantidade", Toast.LENGTH_SHORT).show()}
        else{
            if( iva.isSelected){
                total = (price1 * quantity1 * 1.32).toInt()
            } else{
                total = (price1 * quantity1).toInt()
            }
            textview.text = total.toString()
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.opClean -> {
                val price = findViewById<EditText>(R.id.et1)
                val quantity = findViewById<EditText>(R.id.et2)
                val iva = findViewById<CheckBox>(R.id.iva)

                price.text = null
                quantity.text = null
                iva.isChecked = false

                Toast.makeText(this,R.string.clean, Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


    }

}