package com.chuys.gshp.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import com.chuys.gshp.login.R
import com.chuys.gshp.login.data.provider.LoginDataProvider
import com.chuys.gshp.login.domain.model.TermsData
import com.chuys.gshp.login.domain.provider.LoginProvider
import com.chuys.gshp.login.presenter.LoginContract
import com.chuys.gshp.login.presenter.TermsPresenter
import com.chuys.gshp.shared.data.job.JobExecutor
import com.chuys.gshp.shared.data.job.UIThread
import com.chuys.gshp.shared.util.commons.ToolbarHelper

class Terms : AppCompatActivity(), LoginContract.TermsViewContract, View.OnClickListener {

    private lateinit var termsTextVie: WebView
    private lateinit var agreeButton: Button
    private lateinit var desagreeButton: Button
    private lateinit var loginProvider: LoginProvider
    private lateinit var presenter: LoginContract.TermsPresenterContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms)
        ToolbarHelper(this).configToolbarHelpGeneric("TERMINOS Y CONDICIONES", true, 0)
        termsTextVie = findViewById(R.id.terms_value_textview)
        agreeButton = findViewById(R.id.agree_button)
        desagreeButton = findViewById(R.id.desagree_button)
        loginProvider = LoginDataProvider(
            JobExecutor(),
            UIThread()
        )
        presenter = TermsPresenter(this, loginProvider)
    }

    override fun onResume() {
        super.onResume()
        agreeButton.setOnClickListener(this)
        desagreeButton.setOnClickListener(this)
        presenter.getTermsData()
    }

    override fun loadTerms(termsData: TermsData) {
        termsTextVie.loadDataWithBaseURL("", termsData.value, "text/html", "utf-8", "")
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.agree_button -> finish()
            R.id.desagree_button -> finish()
        }
    }

    override fun showError(message: String) {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
}
