package com.pradesh.bhaktapursales.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.pradesh.bhaktapursales.ui.R

class ProductsFragment : Fragment() {

    //private lateinit var homeViewModel: HomeViewModel
   // private var _binding: FragmentProductsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
   // private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

       // _binding = FragmentProductsBinding.inflate(inflater, container, false)
        val root = inflater.inflate(R.layout.fragment_products,container,false)

        val textView: TextView = root.findViewById(R.id.text_home)
        //homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = "This is a Product Fragment"

        return root
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}