package su.learn.jetpack.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import su.learn.jetpack.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    companion object {
        const val REQUEST_AUTH = 101
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseAuth.getInstance().createUserWithEmailAndPassword("291770865@qq.com","123456")
            .addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(activity, "Register Success", Toast.LENGTH_SHORT).show()
                }else if (it.isComplete){
                    Toast.makeText(activity, "Register Complete", Toast.LENGTH_SHORT).show()
                }
            }

//        startActivityForResult(
//            AuthUI.getInstance()
//                .createSignInIntentBuilder()
//                .setAvailableProviders(
//                    arrayListOf(
//                        AuthUI.IdpConfig.EmailBuilder().build(),
//                        AuthUI.IdpConfig.PhoneBuilder().build()
//                    )
//                )
//                .build(),
//            REQUEST_AUTH
//        )

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_AUTH) {
            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                user?.apply {
                    Log.e("UserAuth", "onActivityResult: " +this.uid + this.displayName + this.photoUrl)
                }
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...

            }
        }
    }
}