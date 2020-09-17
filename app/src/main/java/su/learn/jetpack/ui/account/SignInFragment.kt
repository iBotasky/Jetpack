package su.learn.jetpack.ui.account

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_signin.*
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
class SignInFragment : BaseFragment() {

    val mAuth = FirebaseAuth.getInstance()


    override fun layoutRes() = R.layout.fragment_signin


    override fun initView() {
        btnSignIn.setOnClickListener {
            val userName = etUserName.text.toString().trim()
            val password = etPassword.text.toString().trim()
            if (userName.isNullOrEmpty().not() && password.isNullOrEmpty().not()) {
                mAuth.signInWithEmailAndPassword(
                    etUserName.text.toString().trim(),
                    etPassword.text.toString().trim()
                )
                    .addOnSuccessListener {
                        Toast.makeText(activity, "Sign in Success", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(
                            activity,
                            "Sign in error cause${it.localizedMessage}",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
            }
        }

        btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.signInToSignUp)
        }


        btnFireBaseUI.setOnClickListener {
            val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.PhoneBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build()
            )

            // Create and launch sign-in intent
            startActivity(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build()
            )
        }
    }
}