package com.example.web_services.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.web_services.R
import com.example.web_services.api.User


class UserAdapter(val users: List<User>): RecyclerView.Adapter<UsersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerline,
            parent, false)
        return UsersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        return holder.bind(users[position])
    }
}

class UsersViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    private val name: TextView = itemView.findViewById(R.id.name)
    private val email:TextView = itemView.findViewById(R.id.email)
    private val city:TextView = itemView.findViewById(R.id.city)

    fun bind(user: User) {
        name.text = user.address.city + "-" + user.address.zipcode
        city.text = user.address.city
        email.text = user.address.zipcode
    }

}
