package com.tkim949.pastagram

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.*
import com.tkim949.pastagram.fragments.ComposeFragment
import com.tkim949.pastagram.fragments.FeedFragment
import com.tkim949.pastagram.fragments.ProfileFragment
import java.io.File

/**
 * Let user create a post by taking a photo with their camera!
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. setting the description of the post
        // 2. A button to launch the camera to take a picture
        // 3. An imageView to show the picture the user has taken
        // 4. A button to save and send the post to our Parse server
        val fragmentManager: FragmentManager = supportFragmentManager

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
            item ->

            var fragmentToShow: Fragment? = null
            when (item.itemId) {

                R.id.action_home -> {
                    // TODO Navigate to the home screen / feed fragment
                    fragmentToShow = FeedFragment()
                    //Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                }
                R.id.action_compose -> {
                    // TODO Navigate to the Compose screen
                    fragmentToShow = ComposeFragment()
                    //Toast.makeText(this, "Compose", Toast.LENGTH_SHORT).show()
                }
                R.id.action_profile -> {
                    // TODO Navigate to the profile screen
                    fragmentToShow = ProfileFragment()
                    //Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                }

            }

            if(fragmentToShow != null) {
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            }
            //Return true to say that we've handled this user interaction on the item
            true
        }

        // Set default selection
        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.action_home
        /*
        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            ParseUser.logOut()
            val currentUser = ParseUser.getCurrentUser() // this will now be null
            if(currentUser == null) {
                goToLoginActivity()
            }
        }*/
       // queryPosts()
    }

    /*
    private fun goToLoginActivity() {
        val intent = Intent(this@MainActivity, loginActivity::class.java)
        startActivity(intent)
        // finish() //after login, we don't want to logout by clicking the back-button!
    }*/

    companion object {
        const val TAG = "MainActivity"
    }
}

/*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firstObject = ParseObject("FirstClass")
        firstObject.put("message","Hey ! First message from android. Parse is now connected")
        firstObject.saveInBackground {
            if (it != null){
                it.localizedMessage?.let { message -> Log.e("MainActivity", message) }
            }else{
                Log.d("MainActivity","Object saved.")
            }
        }
    }
}
*/
