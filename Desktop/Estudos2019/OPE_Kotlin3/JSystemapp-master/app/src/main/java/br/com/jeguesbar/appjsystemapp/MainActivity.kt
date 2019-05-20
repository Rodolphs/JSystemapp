package br.com.jeguesbar.appjsystemapp

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import br.com.jeguesbar.appjsystemapp.R
import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // encontra objeto pelo id
        val imagem = findViewById<ImageView>(R.id.campo_imagem)
        imagem.setImageResource(R.drawable.apresentacao_jegues_bar_system)

        val texto = findViewById<TextView>(R.id.texto_login)
        texto.text = getString(R.string.mensagem_login)

        val botaoLogin = findViewById<Button>(R.id.botao_login)

        botaoLogin.setOnClickListener { onClickLogin() }

    }

    override fun onResume() {

        super.onResume()

        campo_usuario.setText(Prefs.getString("lembrarUser"))
        campo_senha.setText(Prefs.getString("lembrarSenha"))
        checkBox.isChecked = Prefs.getBoolean("lembrar")

    }

    fun onClickLogin() {
        val campoUsuario = findViewById<EditText>(R.id.campo_usuario)
        val campoSenha = findViewById<EditText>(R.id.campo_senha)
        val valorUsuario = campoUsuario.text.toString()
        val valorSenha = campoSenha.text.toString()
        //Toast.makeText(context, "$valorUsuario : $valorSenha", Toast.LENGTH_LONG).show()

        if (campoUsuario.text.toString() == "aluno" && campoSenha.text.toString() == "impacta") {

            // criar intent
            val intent = Intent(context, TelaInicialActivity::class.java)
            // colocar parâmetros (opcional)
            val params = Bundle()
            params.putString("nome", "Jegues Bar")
            intent.putExtras(params)

            // enviar parâmetros simplificado
            intent.putExtra("numero", 10)

            // fazer a chamada
            //startActivity(intent)

            // fazer a chamada esperando resultado
            startActivityForResult(intent, 1)
        } else {
            Toast.makeText(this, "Usuário ou senha incorreto", Toast.LENGTH_SHORT).show()
        }

        Prefs.setBoolean("lembrar", checkBox.isChecked)
        if (checkBox.isChecked) {

            Prefs.setString("lembrarUser", valorUsuario)
            Prefs.setString("lembrarSenha", valorSenha)

        } else {

            Prefs.setString("lembrarUser", "")
            Prefs.setString("lembrarSenha", "")


        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            val result = data?.getStringExtra("result")
            Toast.makeText(context, "$result", Toast.LENGTH_LONG).show()
        }
    }
}
