package br.com.jeguesbar.appjsystemapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.*
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import br.com.jeguesbar.appjsystemapp.R
import kotlinx.android.synthetic.main.activity_tela_inicial.*

class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this

    private var produtos = listOf<Produtos>()
    var recyclerProd: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        // acessar parametros da intnet
        // intent é um atributo herdado de Activity
        val args = intent.extras
        // recuperar o parâmetro do tipo String
        val nomeUser = args.getString("nome")

        Toast.makeText(context, "Parâmetro: $nomeUser", Toast.LENGTH_LONG).show()

        val mensagem = findViewById<TextView>(R.id.mensagemInicial)
        mensagem.text = "Bem vindo $nomeUser"
//
//        val botaoSair = findViewById<Button>(R.id.botaoSair)
//        botaoSair.setOnClickListener { cliqueSair() }

        // colocar toolbar
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        // alterar título da ActionBar
        supportActionBar?.title = "Faça seu pedido"

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // configuração do menu lateral
        configuraMenuLateral()

        recyclerProd = recyclerProdutos
        recyclerProd?.layoutManager = LinearLayoutManager(context)
        recyclerProd?.itemAnimator = DefaultItemAnimator()
        recyclerProd?.setHasFixedSize(true)

    }

    fun taskProdutos() {

        produtos = ProdutoService.getProdutos(context)
        recyclerProd?.adapter = ProdutoAdapter(produtos) {
            onClickProduto(it)
        }

    }

    fun onClickProduto(produto: Produtos){
        Toast.makeText(context, "Clicou ${produto.nome}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, ProdutoActivity::class.java)
        intent.putExtra("produto", produto)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        taskProdutos()
    }

    // configuração do navigation Drawer com a toolbar
    private fun configuraMenuLateral() {
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        var menuLateral = findViewById<DrawerLayout>(R.id.layoutMenuLateral)

        // ícone de menu (hamburger) para mostrar o menu
        var toogle = ActionBarDrawerToggle(
            this,
            menuLateral,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        menuLateral.addDrawerListener(toogle)
        toogle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.menu_lateral)
        navigationView.setNavigationItemSelectedListener(this)
    }

    // método que deve ser implementado quando a activity implementa a interface NavigationView.OnNavigationItemSelectedListener
    // para tratar os eventos de clique no menu lateral
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_pedidos -> {
                Toast.makeText(this, "Pedidos", Toast.LENGTH_SHORT).show()
                val intentPedido = Intent(context, PedidosActivity::class.java)
                startActivityForResult(intentPedido, 1)
            }

            R.id.nav_cancelarPedido -> {
                Toast.makeText(this, "Cancelar pedido", Toast.LENGTH_SHORT).show()
                val intentCancelarPedido = Intent(context, CancelarPedidoActivity::class.java)
                startActivityForResult(intentCancelarPedido, 1)
            }

            R.id.nav_solicitacoes -> {
                Toast.makeText(this, "Solicitação", Toast.LENGTH_SHORT).show()
                val intentSolicitacao = Intent(context, SolicitacaoActivity::class.java)
                startActivityForResult(intentSolicitacao, 1)
            }

            R.id.nav_sair -> {
                Toast.makeText(this, "Sair", Toast.LENGTH_SHORT).show()
                cliqueSair()
            }

        }

        // fecha menu depois de tratar o evento
        val drawer = findViewById<DrawerLayout>(R.id.layoutMenuLateral)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    fun cliqueSair() {
        val returnIntent = Intent();
        returnIntent.putExtra("result", "Saída da tela de pedido");
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main, menu)
        // vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                // ação enquanto está digitando
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // ação  quando terminou de buscar e enviou
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // id do item clicado
        val id = item?.itemId
        // verificar qual item foi clicado e mostrar a mensagem Toast na tela
        // a comparação é feita com o recurso de id definido no xml
        if (id == R.id.action_buscar) {
            Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(context, "Botão de atualizar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_config) {
            Toast.makeText(context, "Botão de configuracoes", Toast.LENGTH_LONG).show()
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


}
