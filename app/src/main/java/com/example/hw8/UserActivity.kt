package com.example.hw8

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserActivity : AppCompatActivity() {

    private lateinit var userProfileImageView: ImageView
    private lateinit var userNameTextView: TextView
    private lateinit var userEmailTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        userProfileImageView = findViewById(R.id.userProfileImageView)
        userNameTextView = findViewById(R.id.userNameTextView)
        userEmailTextView = findViewById(R.id.userEmailTextView)
        getUser(intent.extras?.getInt(UsersRecyclerAdapter.USER_ID)!!)
    }

    private fun getUser(userId: Int) {
        ApiClient.getService().getUser(userId).enqueue(object : Callback<UserDTO> {
            override fun onResponse(call: Call<UserDTO>, response: Response<UserDTO>) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.data?.let {
                        userNameTextView.text = "${it.firstName} ${it.lastName}"
                        userEmailTextView.text = it.email
                        Picasso.get().load(it.avatar).into(userProfileImageView)
                    }
                }
            }

            override fun onFailure(call: Call<UserDTO>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}
