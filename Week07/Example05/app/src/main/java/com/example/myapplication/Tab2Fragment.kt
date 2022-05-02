package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.Tab2FragmentBinding

class Tab2Fragment : Fragment() {
    private lateinit var binding: Tab2FragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Tab2FragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}