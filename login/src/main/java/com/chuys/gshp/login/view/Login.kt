package com.chuys.gshp.login.view

//import com.okta.appauth.android.OktaAppAuth
 import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.chuys.gshp.login.R
import com.chuys.gshp.login.presenter.LoginContract
import com.chuys.gshp.navigation.Activities
import com.chuys.gshp.navigation.ActivityManager
import com.chuys.gshp.shared.util.commons.ToolbarHelper
import com.okta.appauth.android.OktaAppAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_terms.*
import net.openid.appauth.AuthorizationException
import java.util.concurrent.Executors


class Login : AppCompatActivity(), LoginContract.LoginViewContract, View.OnClickListener {

    private val TAG="Login"
    private lateinit var syncButton: Button
    private lateinit var termsbutton: Button
    private lateinit var mOktaAuth: OktaAppAuth
    private var executor = Executors.newSingleThreadExecutor()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mOktaAuth = OktaAppAuth.getInstance(this)
        if(isUserLoggedIn()){
            Log.i(TAG, "User is already authenticated, proceeding to token activity");
            startActivity(Intent(this, LoginInputUser::class.java))
        }
        setContentView(R.layout.activity_login)
        syncButton = findViewById(R.id.login_button)
        termsbutton = findViewById(R.id.terms_button)
        syncButton.setOnClickListener(this)
        termsbutton.setOnClickListener(this)
        ToolbarHelper(this).configToolbarHelpGeneric(R.string.login_name, false, 0)
        toolbar.setOnClickListener(View.OnClickListener { })

        user_edittext.addTextChangedListener(
            OktaAppAuth.LoginHintChangeHandler(mOktaAuth))

        if(intent.getBooleanExtra("failed",false)){
            Toast.makeText(this,"Authorization canceled",Toast.LENGTH_SHORT).show()
        }
        initializeOktaAuth()
    }

    private fun  initializeOktaAuth(){
        Log.i(TAG, "Initializing OktaAppAuth")
        mOktaAuth.init(
            this,
            object : OktaAppAuth.OktaAuthListener {
                override fun onSuccess() {
                    runOnUiThread { Log.e(TAG,"success")  }
                }

                override fun onTokenFailure(@NonNull ex: AuthorizationException) {
                    runOnUiThread {
                        Log.e(TAG,"Fallo")
                    }
                }
            }
        )
    }

    override fun loginSuccess() {

    }

    override fun showError(message: String) {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.login_button -> {
                startAuth()
                //ActivityManager.changeToActivity(Activities.HOME, this)

            }
            R.id.terms_button -> {
                ActivityManager.changeToActivity(Activities.TERMS, this)
            }
        }
    }

    private fun isUserLoggedIn():Boolean {
        return mOktaAuth.isUserLoggedIn
    }

    @MainThread
    private fun startAuth() {
        var completionIntent = Intent(this, LoginInputUser::class.java)
        var cancelIntent = Intent(this,Login::class.java)

        cancelIntent.putExtra("failed",true)
        cancelIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        mOktaAuth.login(this, PendingIntent.getActivity(this, 0, completionIntent, 0),
            PendingIntent.getActivity(this, 0, cancelIntent, 0))

    }

    override fun onDestroy() {
        super.onDestroy()
        mOktaAuth.dispose()
    }
}
