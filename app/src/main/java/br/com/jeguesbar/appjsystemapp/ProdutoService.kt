package br.com.jeguesbar.appjsystemapp

import android.content.Context
import android.util.Log
import br.com.jeguesbar.appjsystemapp.AndroidUtils
import br.com.jeguesbar.appjsystemapp.HttpHelper
import br.com.jeguesbar.appjsystemapp.Response
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URL

object ProdutoService {

    val host = "http://siouxsie.pythonanywhere.com/"
    val TAG = "WS_OPE"

    fun getProdutos(context: Context): List<Produtos> {

        if (AndroidUtils.isInternetDisponivel(context)) {

            val url = "$host/produtos"
            val json = HttpHelper.get(url)
            Log.d(TAG, json)
            return parserJson<List<Produtos>>(json)
        } else {
            return ArrayList()
        }

    }

    fun postProduto(produto: Produtos) : Response {

        val json = HttpHelper.post("$host/produtos", produto.toJson())

        return parserJson(json)

    }

    inline fun <reified T> parserJson(json: String): T {

        val type = object : TypeToken<T>() {}.type

        return Gson().fromJson<T>(json, type)

    }

}