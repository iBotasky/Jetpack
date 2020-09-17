package su.learn.jetpack.ui.account

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_signin.*
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.fragment_signup.btnSignUp
import kotlinx.android.synthetic.main.fragment_signup.etPassword
import kotlinx.android.synthetic.main.fragment_signup.etUserName
import su.learn.jetpack.BaseFragment
import su.learn.jetpack.R

/**
 * Desc:
 *
 * Date: 2020/9/17
 * Copyright: Copyright (c) 2018-2019
 * Company: @微微科技有限公司
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: suliangbo
 */
class SignUpFragment : BaseFragment() {
    val mAuth = FirebaseAuth.getInstance()

    override fun initView() {
        btnSignUp.setOnClickListener {
            val userName = etUserName.text.toString().trim()
            val password = etPassword.text.toString().trim()
            if (userName.isNullOrEmpty().not() && password.isNullOrEmpty().not()) {
                mAuth.createUserWithEmailAndPassword(
                    etUserName.text.toString().trim(),
                    etPassword.text.toString().trim()
                )
                    .addOnSuccessListener {
                        Toast.makeText(activity, "Register Success", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.signUpToSignIn)
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            activity,
                            "Register error cause ${it.localizedMessage}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }
    }

    override fun layoutRes() = R.layout.fragment_signup
}