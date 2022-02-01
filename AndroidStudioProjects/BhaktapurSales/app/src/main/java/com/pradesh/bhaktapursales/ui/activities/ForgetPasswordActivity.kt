package com.pradesh.bhaktapursales.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.pradesh.bhaktapursales.ui.R
import kotlinx.android.synthetic.main.activity_forget_password.*
import kotlinx.android.synthetic.main.activity_register.*

class ForgetPasswordActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        setupActionBar()
    }

    private fun setupActionBar(){
        setSupportActionBar(toolbar_forgot_password_activity)

        val actionBar= supportActionBar
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolbar_forgot_password_activity.setNavigationOnClickListener{ onBackPressed()}

        btn_submit.setOnClickListener{
            val email: String = et_email_forgot.text.toString().trim{ it <= ' '}
            if (email.isEmpty()){
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
            }
            else{
                showProgressDialog(resources.getString(R.string.please_wait))

                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener{task ->

                        hideProgressDialog()

                        if (task.isSuccessful){
                            //show toast message and finish forgot password activity to go back to the user
                            Toast.makeText(this@ForgetPasswordActivity,
                                resources.getString(R.string.email_sent_success)
                            ,Toast.LENGTH_LONG).show()
                            finish()
                        }
                        else{
                            //if the registering is not successful then show error message.
                            showErrorSnackBar(task.exception!!.message.toString(),true)
                        }
                    }
            }
        }
    }

}