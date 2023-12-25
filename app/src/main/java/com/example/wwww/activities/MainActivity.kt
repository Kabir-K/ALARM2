package com.example.wwww.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.wwww.R
import com.example.wwww.firebase.firestoreclass
import com.example.wwww.models.User
import com.example.wwww.util.Constant
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    var a:Toolbar?=null
    private val mfirestore= FirebaseFirestore.getInstance()
    private lateinit var muserdetails:User
    var b:DrawerLayout?=null
    var c:NavigationView?=null
    var d:TextView?=null
    var uid:EditText?=null
    var firstconnection:TextView?=null
    var secondconnection:TextView?=null
    var thirdconnection:TextView?=null
    var fourthconnection:TextView?=null
    var fifthconnection:TextView?=null
    var z:FloatingActionButton?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        a=findViewById(R.id.toolbar_main)
        b=findViewById(R.id.drawerlayout)
        secondconnection=findViewById(R.id.maincontentusertwo)
        thirdconnection=findViewById(R.id.maincontentuserthree)
        fourthconnection=findViewById(R.id.maincontentuserfour)
        fifthconnection=findViewById(R.id.maincontentuserfive)
        uid=findViewById(R.id.finaladduid)
        c=findViewById(R.id.navview)
        firstconnection=findViewById(R.id.maincontentuserone)
        d=findViewById(R.id.usernameid)
        z=findViewById(R.id.addconnect)
        setupactionbar()
        z?.setOnClickListener{
            addaconnection()
        }
        addconnectioninui()
        c?.setNavigationItemSelectedListener(this@MainActivity)
        firestoreclass().loaduserdata(this@MainActivity)
    }
    private fun setupactionbar(){
        setSupportActionBar(a)
        a?.setNavigationIcon(R.drawable.navmenuicon)
        a?.setNavigationOnClickListener{
            toggledrawer()
        }
    }
    private fun addaconnection(){
        var id:String=uid?.text.toString().trim{ it <= ' '}
        if(validateform(id)) {
            mfirestore.collection(Constant.USERS)
                .document(id)
                .get().addOnSuccessListener { document ->
                    val newconnection = document.toObject(User::class.java)!!
                    val userhashmap = HashMap<String, Any>()
                    var a=newconnection.name
                    mfirestore.collection(Constant.USERS)
                        .document(firestoreclass().getcurrentuserid())
                        .get().addOnSuccessListener { document ->
                            val loggedinuser=document.toObject(User::class.java)!!
                            if(loggedinuser.connecteduserone.length==0){
                                userhashmap[Constant.CONNECTEDUSERONE]=newconnection.name
                                firestoreclass().addanewconnection(this@MainActivity,userhashmap)
                                addconnectioninui()
                            }
                            else if(loggedinuser.connectedusertwo.length==0){
                                userhashmap[Constant.CONNECTEDUSERTWO]=newconnection.name
                                firestoreclass().addanewconnection(this@MainActivity,userhashmap)
                                addconnectioninui()
                            }
                            else if(loggedinuser.connecteduserthree.length==0){
                                userhashmap[Constant.CONNECTEDUSERTWO]=newconnection.name
                                firestoreclass().addanewconnection(this@MainActivity,userhashmap)
                                addconnectioninui()
                            }
                            else if(loggedinuser.connecteduserfour.length==0){
                                userhashmap[Constant.CONNECTEDUSERTWO]=newconnection.name
                                firestoreclass().addanewconnection(this@MainActivity,userhashmap)
                                addconnectioninui()
                            }
                            else if(loggedinuser.connecteduserfive.length==0){
                                userhashmap[Constant.CONNECTEDUSERTWO]=newconnection.name
                                firestoreclass().addanewconnection(this@MainActivity,userhashmap)
                                addconnectioninui()
                            }
                            else{
                                Toast.makeText(this@MainActivity,"MORE USER CANNOT BE ADDED",Toast.LENGTH_LONG).show()
                            }
                        }
                }
        }
    }
    private fun addconnectioninui(){
        mfirestore.collection(Constant.USERS)
            .document(firestoreclass().getcurrentuserid())
            .get().addOnSuccessListener { document ->
                val currentuser=document.toObject(User::class.java)!!
                firstconnection?.text=currentuser.connecteduserone
            }
    }



    private fun validateform(a:String):Boolean{
        return when{
            TextUtils.isEmpty(a) ->{
                false
            }else ->{
                true
            }
        }
    }
    fun updatenavigationuserdetail(user:User){
        d?.text=user.name
    }
    private fun toggledrawer(){
        if(b?.isDrawerOpen(GravityCompat.START)==true){
            b?.closeDrawer(GravityCompat.START)
        }else{
            b?.openDrawer(GravityCompat.START)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.navmyprofile ->{
                startActivity(Intent(this@MainActivity,profilescreen::class.java))
            }
            R.id.navsignout ->{
                FirebaseAuth.getInstance().signOut()
                val intent= Intent(this@MainActivity,intro::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }
        b?.closeDrawer(GravityCompat.START)
        return true

    }

}