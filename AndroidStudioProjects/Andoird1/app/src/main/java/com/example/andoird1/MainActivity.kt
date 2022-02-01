package com.example.andoird1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener(){
            val inputValue: String = editText.text.toString()
            if (inputValue == null || inputValue.trim()==""){
                Toast.makeText(this,"please input data, edit text cannot be blank",Toast.LENGTH_LONG).show()
            }else{
                textView4.setText(inputValue).toString()
            }
        }

        textView5.setOnClickListener() {
            if (textView4.text.toString()==null || textView4.text.toString().trim()==""){
                Toast.makeText(this,"clicked on reset textView,\n output textView already reset",Toast.LENGTH_LONG).show()
            }
            else{
                textView4.setText("").toString()
            }
        }

        editText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Toast.makeText(applicationContext,"executed before making any change over EditText",Toast.LENGTH_SHORT).show()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Toast.makeText(applicationContext,"executed while making any change over EditText",Toast.LENGTH_SHORT).show()
            }
            override fun afterTextChanged(p0: Editable?) {
                //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Toast.makeText(applicationContext,"executed after change made over EditText",Toast.LENGTH_SHORT).show()
            }
        })


    }
}