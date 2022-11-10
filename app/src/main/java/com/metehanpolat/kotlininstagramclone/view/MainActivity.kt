package com.metehanpolat.kotlininstagramclone.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.metehanpolat.kotlininstagramclone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth : FirebaseAuth
    private var email : String? = null
    private var password : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        val currentUser = auth.currentUser

        if(currentUser != null){
            val intent = Intent(this, FeedActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun signInClicked (view: View){

        email = binding.eMailText.text.toString()
        password = binding.passwordText.text.toString()

        if(email.equals("") || password.equals("")){
            Toast.makeText(this,"Enter email and password!",Toast.LENGTH_LONG).show()
        }else{
            auth.signInWithEmailAndPassword(email!!,password!!).addOnSuccessListener {
                val intent = Intent(this@MainActivity, FeedActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this@MainActivity,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }

    }

    fun signUpClicked (view: View){

        email = binding.eMailText.text.toString()
        password = binding.passwordText.text.toString()

        if(email!!.isNotEmpty() && password!!.isNotEmpty()){
            auth.createUserWithEmailAndPassword(email!!,password!!).addOnSuccessListener {
                //succes
                val intent = Intent(this@MainActivity, FeedActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
            }

        }else{
            Toast.makeText(this,"Enter email and password!",Toast.LENGTH_LONG).show()
        }
    }
}