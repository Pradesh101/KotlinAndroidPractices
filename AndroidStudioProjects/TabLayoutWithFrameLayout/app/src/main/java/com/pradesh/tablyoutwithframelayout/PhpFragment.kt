package com.pradesh.tablyoutwithframelayout

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_php.view.*

class PhpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_java, container, false)

        view.phpButton!!.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,"This is Php Fragment", Toast.LENGTH_SHORT).show()
        })
        return view
    }
}