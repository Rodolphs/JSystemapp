package br.com.jeguesbar.appjsystemapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class SolicitacaoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solicitacao)

        val actionbar3 = supportActionBar
        actionbar3!!.title = "Solicitações"
        actionbar3.setDisplayHomeAsUpEnabled(true)
        actionbar3.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
