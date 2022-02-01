package com.pradesh.bhaktapursales.ui.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.pradesh.bhaktapursales.ui.R
import com.pradesh.bhaktapursales.firestore.FireStoreClass
import com.pradesh.bhaktapursales.models.User
import com.pradesh.bhaktapursales.utlis.Constants
import com.pradesh.bhaktapursales.utlis.GlideLoader
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_user_profile.*
import kotlinx.android.synthetic.main.activity_user_profile.et_first_name
import kotlinx.android.synthetic.main.activity_user_profile.et_last_name
import kotlinx.android.synthetic.main.activity_user_profile.iv_user_photo
import java.io.IOException
import java.util.jar.Manifest

class UserProfileActivity : BaseActivity(), View.OnClickListener {

    private lateinit var mUserDetails: User

    private var mSelectedImageFileUri: Uri? = null

    private var mUserProfileImageURL: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)


        if (intent.hasExtra(Constants.EXTRA_USER_DETAILS)){
            //get the user details from the intent as a parcelableExtra
            mUserDetails = intent.getParcelableExtra(Constants.EXTRA_USER_DETAILS)!!
        }

        et_first_name.setText(mUserDetails.firstName)
        et_last_name.setText(mUserDetails.lastName)
        et_email_profile.isEnabled = false
        et_email_profile.setText(mUserDetails.email)

        if (mUserDetails.profileCompleted == 0){
            tv_title_up.text= resources.getString(R.string.title_complete_profile)

            et_first_name.isEnabled = false
            //et_first_name.setText(mUserDetails.firstName)

            et_last_name.isEnabled = false
            //et_last_name.setText(mUserDetails.lastName)

        }
        else{
            setupActionBar()
//            et_first_name.isEnabled = true
//            et_last_name.isEnabled = true
            tv_title_up.text=resources.getString(R.string.title_edit_profile)
            GlideLoader(this@UserProfileActivity).loadUserPicture(mUserDetails.image,iv_user_photo)
            if (mUserDetails.mobile != 0L){
                et_mobile_number.setText(mUserDetails.mobile.toString())
            }
            if (mUserDetails.gender == Constants.MALE){
                rb_male.isChecked = true
            } else{
                rb_female.isChecked = false
            }
        }

//        et_first_name.isEnabled = false
//        et_first_name.setText(mUserDetails.firstName)
//
//        et_last_name.isEnabled = false
//        et_last_name.setText(mUserDetails.lastName)
//
//        et_email_profile.isEnabled = false
//        et_email_profile.setText(mUserDetails.email)

        iv_user_photo.setOnClickListener(this@UserProfileActivity)
        btn_save.setOnClickListener(this@UserProfileActivity)
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar_user_profile_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        toolbar_user_profile_activity.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onClick(v: View?) {
        if (v != null){
            when(v.id) {
                R.id.iv_user_photo -> {
                    //here we will check if the permission is already allowed or we need to request for it.
                    //first of all we will check the READ_EXTERNAL_STORAGE permission and if it is allowed we
                    if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED)
                    {
                        //showErrorSnackBar("You already have the storage permission", false)
                        Constants.showImageChooser(this)
                    }
                    else{
                        /* Request permissions to be granted to this application. These permissions
                        * must be requested in your manifest, they should not be granted to your app,
                        * and they should have protection level */

                        ActivityCompat.requestPermissions(this,
                            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                            Constants.READ_STORAGE_PERMISSION_CODE)
                    }
                }
                R.id.btn_save -> {

                    if (validateUserProfileDetails()){
                        showProgressDialog(resources.getString(R.string.please_wait))

                        if (mSelectedImageFileUri != null) {
                            FireStoreClass().uploadImageToCloudStorage(this, mSelectedImageFileUri)
                        }
                        else{
                            updateUserProfileDetails()
                        }
                    }
                }
            }
        }
    }

    private fun updateUserProfileDetails(){
        val userHashMap = HashMap<String, Any>()

        val firstName= et_first_name.text.toString().trim{it <= ' '}
        if (firstName != mUserDetails.firstName){
            userHashMap[Constants.FIRST_NAME] = firstName
        }

        val lastName= et_first_name.text.toString().trim{it <= ' '}
        if (lastName != mUserDetails.lastName){
            userHashMap[Constants.LAST_NAME] = lastName
        }


        val mobileNumber= et_mobile_number.text.toString().trim{it <= ' '}
        val gender = if (rb_male.isChecked){
            Constants.MALE
        }else{
            Constants.FEMALE
        }

        if (mUserProfileImageURL.isNotEmpty()){
            userHashMap[Constants.IMAGE] = mUserProfileImageURL
        }

        if (mobileNumber.isNotEmpty() && mobileNumber != mUserDetails.mobile.toString()){
            userHashMap[Constants.MOBILE] = mobileNumber.toLong()
        }

        if (gender.isNotEmpty() && gender!= mUserDetails.gender){
            userHashMap[Constants.GENDER] = gender
        }

        userHashMap[Constants.GENDER]=gender

        userHashMap[Constants.COMPLETE_PROFILE] = 1

        //showProgressDialog(resources.getString(R.string.please_wait))

        FireStoreClass().updateUserProfileData(this,userHashMap)
        //showErrorSnackBar("Your details are validated. You can update them",false)
    }

    fun userProfileUpdateSuccess(){

        hideProgressDialog()

        Toast.makeText(this@UserProfileActivity,
        resources.getString(R.string.msg_profile_update_success),Toast.LENGTH_LONG).show()

        startActivity(Intent(this@UserProfileActivity, DashboardActivity::class.java))
        finish()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.READ_STORAGE_PERMISSION_CODE){
            //if permission is granted
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //showErrorSnackBar("The storage permission is granted",false)
                Constants.showImageChooser(this)
            }else{
                //display another toast if permission is not granted
                Toast.makeText(this,resources.getString(R.string.read_storage_permission_denied),
                Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.PICK_IMAGE_REQUEST_CODE) {
                if (data != null) {
                    try {
                        //the uri of the selected image from the phone storage.
                        mSelectedImageFileUri = data.data!!

                        //iv_user_photo.setImageURI(selectedImageFileUri)
                        GlideLoader(this).loadUserPicture(mSelectedImageFileUri!!,iv_user_photo)
                    } catch (e: IOException) {
                        e.printStackTrace()
                        Toast.makeText(
                            this@UserProfileActivity,
                            resources.getString(R.string.image_selection_failed),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
            else if (resultCode == Activity.RESULT_CANCELED){
                //a log is printed when user close or cancel the image selection
                Log.e("Request Cancelled","Image selection cancelled")
            }
    }

    private fun validateUserProfileDetails():Boolean{
        return when {
            TextUtils.isEmpty(et_mobile_number.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_enter_mobile_number),
                    true)
                false
            }
            else -> {
                true
            }
        }
    }

    fun imageUploadSuccess(imageURL:String){
       // hideProgressDialog()
//        Toast.makeText(
//            this@UserProfileActivity,
//            "Your image is uploaded successfully. Image URL is $imageURL",
//            Toast.LENGTH_LONG
//        ).show()
        mUserProfileImageURL = imageURL
        updateUserProfileDetails()
    }
}