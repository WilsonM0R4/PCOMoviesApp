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
import com.example.pcomoviesapp.databinding.FragmentLoginBinding
import com.example.pcomoviesapp.viewModel.UserViewModel


class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    private val viewModel by viewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeObserver()
        binding.btnGoToRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.btnLogin.setOnClickListener {
            viewModel.getUser(requireContext(), binding.etLoginSessionName.text.toString())
        }
    }

    private fun initializeObserver() {
        viewModel.user.observe(requireActivity()) {
            val alert = AlertDialog.Builder(requireContext())
            alert.setTitle(R.string.login_alert_failed_title)
            if (it.name.isNotEmpty() && it.sessionName == binding.etLoginSessionName.text.toString()){
                if (it.password == binding.etLoginPassword.text.toString()) {
                    //Go to home page
                } else {
                    alert.apply {
                        setMessage(R.string.login_alert_failed_password)
                        setPositiveButton(R.string.alert_fail_button){_,_ ->}
                    }
                    alert.create()
                    alert.show()
                }
            } else {
                alert.apply {
                    setMessage(R.string.login_alert_failed_user)
                    setPositiveButton(R.string.alert_fail_button){_,_ ->}
                }
                alert.create()
                alert.show()
            }
        }
    }
}