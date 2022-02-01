package com.pradesh.tablyoutwithframelayout

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_java.view.*

class JavaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_java, container, false)

        view.javaButton!!.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,"This is Java Fragment", Toast.LENGTH_SHORT).show()
        })
        return view
    }
}