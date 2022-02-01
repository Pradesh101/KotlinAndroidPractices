package com.pradesh.bhaktapursales.utlis

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap

object Constants {
    const val USERS: String = "users"
    const val BS_PREFERENCES: String = "BSPrefs"
    const val LOGGED_IN_USERNAME: String = "logged_in_username"
    const val EXTRA_USER_DETAILS: String = "extra_user_details"
    const val READ_STORAGE_PERMISSION_CODE = 2
    const val PICK_IMAGE_REQUEST_CODE = 1

    const val MALE: String = "Male"
    const val FEMALE: String = "Female"
    const val FIRST_NAME : String = "firstname"
    const val LAST_NAME: String = "lastname"
    const val MOBILE: String = "mobile"
    const val GENDER: String = "gender"
    const val IMAGE: String = "image"
    const val COMPLETE_PROFILE: String = "profileCompleted"

    const val USER_PROFILE_IMAGE: String = "User_Profile_Image"

    fun showImageChooser(activity: Activity){
        //an intent for the launching the image selection of the phone storage
        val galleryIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        //launches the image selection of the phone storage using the constant code.
        activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }

    fun getFileExtension(activity: Activity,uri: Uri?) :String?{
        /*
        * MimeTypeMap: Two way map that maps MIME-types to the file extensions and vice versa
        *
        * getString(): get the singleton instance of the MimeTypeMap
        *
        * getExtensionFromMimeType: return the registered extension for the given MIME type
        *
        * contentResolver.getType: Return the MIME type of the given content URL.
        * */

        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(activity.contentResolver.getType(uri!!))
    }

}