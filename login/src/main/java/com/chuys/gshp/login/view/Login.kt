package com.chuys.gshp.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.chuys.gshp.login.R
import com.chuys.gshp.login.presenter.LoginContract
import com.chuys.gshp.navigation.Activities
import com.chuys.gshp.navigation.ActivityManager
import com.chuys.gshp.shared.util.commons.ToolbarHelper

class Login : AppCompatActivity(), LoginContract.ViewContract,View.OnClickListener {

    private lateinit var syncButton: Button
    private lateinit var termsbutton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        syncButton=findViewById(R.id.login_button)
        termsbutton=findViewById(R.id.terms_button)
        syncButton.setOnClickListener(this)
        termsbutton.setOnClickListener(this)
        ToolbarHelper(this).configToolbarHelpGeneric(R.string.login_name,true,0)
    }

    override fun loginSuccess() {

    }

    override fun showError(message: String) {

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.login_button->{
                ActivityManager.changeToActivity(Activities.HOME, this)
                finish()
            }
            R.id.terms_button->{
                ActivityManager.changeToActivity(Activities.TERMS, this)
                finish()
            }
        }
    }
}
