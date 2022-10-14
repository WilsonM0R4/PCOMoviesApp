package com.example.pcomoviesapp.view.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.pcomoviesapp.R
import com.example.pcomoviesapp.databinding.FragmentRegisterBinding
import com.example.pcomoviesapp.viewModel.UserViewModel

class RegisterFragment : Fragment() {

    private lateinit var binding : FragmentRegisterBinding
    private val viewModel by viewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeObserver()
        binding.btnCancel.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnRegister.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.registerUser(
                requireContext(),
                binding.etRegisterSessionName.text.toString(),
                binding.etRegisterName.text.toString(),
                binding.etRegisterPassword.text.toString()
            )
        }
    }

    private fun initializeObserver() {
        viewModel.register.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            val dialog = AlertDialog.Builder(requireContext())
            if (it) {
                dialog.apply {
                    setTitle(R.string.register_alert_success_title)
                    setMessage(R.string.register_alert_success_message)
                    setPositiveButton(R.string.alert_success_button) { _, _ ->
                        findNavController().popBackStack()
                    }
                }
            } else {
                dialog.apply {
                    setTitle(R.string.register_alert_fail_title)
                    setMessage(R.string.register_alert_fail_message)
                    setPositiveButton(R.string.alert_fail_button) { _, _ ->
                    }
                }
            }
            dialog.create()
            dialog.show()
        }
    }
}