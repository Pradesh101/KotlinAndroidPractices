package com.pradesh.bhaktapursales.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.pradesh.bhaktapursales.ui.R

class OrdersFragment : Fragment() {

    //private lateinit var notificationsViewModel: NotificationsViewModel
    //private var _binding: FragmentOrdersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    //private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)

        //_binding = FragmentOrdersBinding.inflate(inflater, container, false)
        val root =inflater.inflate(R.layout.fragment_orders,container,false)

        val textView: TextView =  root.findViewById(R.id.text_notifications)
       // notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
        textView.text = "This is a Orders Fragment"

        return root
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}