package com.pradesh.bhaktapursales.firestore

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.pradesh.bhaktapursales.models.User
import com.pradesh.bhaktapursales.ui.activities.LoginActivity
import com.pradesh.bhaktapursales.ui.activities.RegisterActivity
import com.pradesh.bhaktapursales.ui.activities.SettingsActivity
import com.pradesh.bhaktapursales.ui.activities.UserProfileActivity
import com.pradesh.bhaktapursales.utlis.Constants

class FireStoreClass {

    private val mFirestore = FirebaseFirestore.getInstance()

    fun registerUser(activity: RegisterActivity, userInfo: User){

        //the "user" is collection name. If the collection is already created then it will not create the same one again
        mFirestore.collection(Constants.USERS)
        //document id for the users fields. here the document is the user ID.
            .document(userInfo.id)
            //here the userInfo are field and the setOption is the set to merge. It is for if we wants to merge later
                //on instead of the replacing files
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                //here call a function of the base activity for the transferring the result to it
                activity.userRegistrationSuccess()
            }
            .addOnFailureListener{ e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName, "Error while registering the user.",e)
            }
    }

    fun getCurrentUserID() :String {
        //an instance of current user using firebase

        val currentUser = FirebaseAuth.getInstance().currentUser

        //A variable to assign the currentUserId if it is not null ore else it will be blank
        var currentUserID= ""
        if (currentUser!=null){
            currentUserID=currentUser.uid
        }

        return  currentUserID
    }

    fun getUserDetails(activity: Activity){

        //here we pass the collection name from which we wants the data
        mFirestore.collection(Constants.USERS)
        //the document id to get the fields of users
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->
                Log.i(activity.javaClass.simpleName,document.toString())

                //here we have received the document snapshot which converted into the User Data model object
                val user = document.toObject(User::class.java)!!

                val sharedPreferences = activity.getSharedPreferences(Constants.BS_PREFERENCES,Context.MODE_PRIVATE)

                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                //key: value  logged_in_username:pop lol
                editor.putString(Constants.LOGGED_IN_USERNAME,"${user.firstName} ${user.lastName}")
                editor.apply()


                //start
                when(activity){
                    is LoginActivity -> {
                        //call the function from the base activity for transferring the result to it
                        activity.userLoggedInSuccess(user)
                    }

                    is SettingsActivity -> {
                        activity.userDetailsSuccess(user)
                    }
                }
                //end
            }
            .addOnFailureListener{ e->
                //hide thr progress dialog if there is any error. and print the error in log
                when(activity){
                    is LoginActivity -> {
                        activity.hideProgressDialog()
                    }

                    is SettingsActivity -> {
                        activity.hideProgressDialog()
                    }
                }
                Log.e(activity.javaClass.simpleName, "Error while logging the user.",e)
            }
    }

    fun updateUserProfileData(activity: Activity, userHashMap: HashMap<String,Any>){

        mFirestore.collection(Constants.USERS)
            .document(getCurrentUserID())
            .update(userHashMap)
            .addOnSuccessListener {

                when (activity) {
                    is UserProfileActivity -> {
                        //hide the progress dialog if there is any error. and print the error log.
                        activity.userProfileUpdateSuccess()
                    }
                }
            }

            .addOnFailureListener{ e->
                when (activity){
                    is UserProfileActivity ->{
                        //hide the progress dialog if there is any error. and print the error log.
                        activity.hideProgressDialog()
                    }
                }
                Log.e(activity.javaClass.simpleName, "Error while updating details.",e)
            }
    }

    fun uploadImageToCloudStorage(activity: Activity,imageFileUri: Uri?){
        val sRef: StorageReference = FirebaseStorage.getInstance().reference.child(
            Constants.USER_PROFILE_IMAGE + System.currentTimeMillis() + "." +
                    Constants.getFileExtension(activity,imageFileUri)
        )

        sRef.putFile(imageFileUri!!).addOnSuccessListener { taskSnapshot ->
            //the image upload is success
            Log.e("Firebase Image URL",taskSnapshot.metadata!!.reference!!.downloadUrl.toString())

            //get the download url from the task snapshot
            taskSnapshot.metadata!!.reference!!.downloadUrl
                .addOnSuccessListener { uri ->
                Log.e("Download Image URl",uri.toString())
                    when(activity){
                        is UserProfileActivity -> {
                            activity.imageUploadSuccess(uri.toString())
                        }
                    }
            }
        }

            .addOnFailureListener { exception ->

                //hide the progress dialog if there is any error. ANd print the error in log.
                when(activity){
                    is UserProfileActivity -> {
                        activity.hideProgressDialog()
                    }
                }

                Log.e(
                    activity.javaClass.simpleName,
                    exception.message,
                    exception
                )
            }
    }
}