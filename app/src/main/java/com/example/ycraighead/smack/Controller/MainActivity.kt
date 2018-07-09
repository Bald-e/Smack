package com.example.ycraighead.smack.Controller

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.ycraighead.smack.R
import com.example.ycraighead.smack.Services.AuthService
import com.example.ycraighead.smack.Services.UserDataService
import com.example.ycraighead.smack.Utils.BROADCAST_USER_DATA_CHANGE
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity : AppCompatActivity() {

    private val userDataChangeReceiver = object: BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            if (AuthService.isLoggedIn){
                userNameNavHeader.text = UserDataService.name
                userEmailNavHeader.text = UserDataService.email
                val resourceId = resources.getIdentifier(UserDataService.avatarName, "drawable", packageName)
                userIconNavHeader.setImageResource(resourceId)
                loginBtnNavHeader.text = "Logout"

                recreate()

                Log.d("JENIA", "Broadcast recived!")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        LocalBroadcastManager.getInstance(this).registerReceiver(userDataChangeReceiver, IntentFilter(BROADCAST_USER_DATA_CHANGE))
    }



    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun loginBtnNavClicked (view: View) {
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)

    }

    fun addChannelClicked (view: View){

    }

    fun sendMessageBtnClicked (view: View){

    }
}
