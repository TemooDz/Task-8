package com.example.hw8

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class UsersRecyclerAdapter(private val items: List<User>) :
    RecyclerView.Adapter<UsersRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val userNameTextView: TextView = itemView.findViewById(R.id.userNameTextView)
        private val userProfileImageView: ImageView =
            itemView.findViewById(R.id.userProfileImageView)
        private val userEmailTextView: TextView = itemView.findViewById(R.id.userEmailTextView)

        private lateinit var user: User

        fun onBind(item: User) {
            user = item
            userNameTextView.text = "${item.firstName} ${item.lastName}"
            userEmailTextView.text = item.email
            Picasso.get().load(item.avatar).into(userProfileImageView)
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val intent = Intent(itemView.context, UserActivity::class.java)
            intent.putExtra(USER_ID, user.id)
            itemView.context.startActivity(intent)
        }
    }

    companion object {
        const val USER_ID = "user_id"
    }
}
