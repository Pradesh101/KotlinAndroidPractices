package com.pradesh.bhaktapursales.ui.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.pradesh.bhaktapursales.ui.R
import com.pradesh.bhaktapursales.firestore.FireStoreClass
import com.pradesh.bhaktapursales.models.User
import com.pradesh.bhaktapursales.utlis.Constants
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        //click event assigned to respective attributes
        tv_register.setOnClickListener(this)
        tv_forgot_password.setOnClickListener(this)
        btn_login.setOnClickListener(this)
//        tv_register.setOnClickListener {
//            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
//            startActivity(intent)
//        }
    }

    fun userLoggedInSuccess(user: User){

        //hide the progress dialog
        hideProgressDialog()

        //print the user details in the log as of now
//        Log.i("First Name", user.firstName)
//        Log.i("Last Name", user.lastName)
//        Log.i("Email", user.email)

        if (user.profileCompleted ==0){
            //if the user profile is incomplete then launch the userProfileActivity
            val intent = (Intent(this@LoginActivity, UserProfileActivity::class.java))
            intent.putExtra(Constants.EXTRA_USER_DETAILS,user)
            startActivity(intent)
        }
        else{
            //redirect the user to the Main Screen after log in.
            startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
        }
        finish()
    }

    //in login screen the clickable components are login button, forget password text and register text
    override fun onClick(view:View?){
        if(view!=null){
            when(view.id){
                R.id.tv_forgot_password -> {
                    val intent = Intent(this@LoginActivity, ForgetPasswordActivity::class.java)
                    startActivity(intent)
                }
                R.id.btn_login -> {
                    logInRegisteredUser()
                }
                R.id.tv_register -> {
                    val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                    startActivity(intent)
                }
            }

        }
    }

    private fun validateLoginDetails(): Boolean {
        return when{
            TextUtils.isEmpty(et_email.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }

            TextUtils.isEmpty(et_password.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }
            else -> {
                //showErrorSnackBar("Your details are validated", false)
                true
            }
        }
    }

    private fun logInRegisteredUser(){

        if (validateLoginDetails()){
            //show progress dialog
            showProgressDialog(resources.getString(R.string.please_wait))

            //get the text from edit text and trim the text
            val email: String = et_email.text.toString().trim{ it <= ' '}
            val password: String = et_password.text.toString().trim{ it <= ' '}

            //log in using firebase
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener{ task ->

                        //hide progress dialog
                        //hideProgressDialog()

                        //if the registration is successfully done
                        if (task.isSuccessful){
                            //send user to main screen
//                            showErrorSnackBar("You are logged in successfully",false)
                            FireStoreClass().getUserDetails(this@LoginActivity)
                        }
                        else{
                            hideProgressDialog()
                            //if the registering is not successful then show error message.
                            showErrorSnackBar(task.exception!!.message.toString(),true)
                        }
                    }
        }

    }
}