package com.example.web_services

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.web_services.adapter.UserAdapter
import com.example.web_services.api.EndPoints
import com.example.web_services.api.ServiceBuilder
import com.example.web_services.api.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getUsers()

        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val recView = findViewById<RecyclerView>(R.id.recyclerView)
                    recView.layoutManager = LinearLayoutManager(this@MainActivity)
                    recView.adapter = UserAdapter(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getSingle(view: View) {
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getUserById(2)
        call.enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful){
                    val c: User = response.body()!!
                    Toast.makeText(this@MainActivity, c.address.zipcode, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun post(view: View) {}
}