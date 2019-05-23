package br.com.jeguesbar.appjsystemapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class CancelarPedidoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cancelar_pedido)

        val actionbar2 = supportActionBar
        actionbar2!!.title = "Cancelar Pedidos"
        actionbar2.setDisplayHomeAsUpEnabled(true)
        actionbar2.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}


