package cl.desafiolatam

import ListaAdapter
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.SpinnerAdapter
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lista: MutableList<Ciclovia>? = null //se pone afuera para poder usarla en el metodo de abajo y dentro del oncreate
    var current:MutableList<Ciclovia>?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lista=setupCiclovias()//se setean los datos de comunas y ciclovias a una lista llamada "lista"
        recycler_ciclovias.layoutManager = LinearLayoutManager(this)
        val listaAdapter = ListaAdapter(lista,this)
        recycler_ciclovias.adapter = ListaAdapter(lista,this)
        listaAdapter.setupData(lista!!)
        Log.d("spinner",spinner_bike.selectedItem.toString())

        spinner_bike.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //recycler_ciclovias.adapter = ListaAdapter(lista,baseContext)
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                /*if(spinner_bike.selectedItem.equals("Mostrar Todo")){
                    recycler_ciclovias.adapter = ListaAdapter(lista,baseContext)
                }*/
                recycler_ciclovias.adapter = ListaAdapter(lista!!.filter { n->n.comuna.equals(spinner_bike.selectedItem) } as MutableList<Ciclovia>,baseContext)
                when(spinner_bike.selectedItem){"Mostrar Todo"->recycler_ciclovias.adapter = ListaAdapter(lista!!,baseContext)}
            }

        }


        /*button_filtrar.setOnClickListener {
            when(spinner_bike.selectedItem){
                "Mostrar Todo" -> recycler_ciclovias.adapter = ListaAdapter(lista,this)
                else ->recycler_ciclovias.adapter = ListaAdapter(lista!!.filter { n->n.comuna.equals(spinner_bike.selectedItem) } as MutableList<Ciclovia>,this)

            }

            //recycler_ciclovias.adapter = ListaAdapter(lista!!.filter { n->n.comuna.equals("Las Condes") } as MutableList<Ciclovia>,this)
        }*/
        button_invertir.setOnClickListener {
            when(spinner_bike.selectedItem){
                "Mostrar Todo" -> recycler_ciclovias.adapter = ListaAdapter(lista!!.reversed() as MutableList<Ciclovia>,this)
                else -> when(button_invertir.text){
                    resources.getString(R.string.invertir)->
                        recycler_ciclovias.adapter = ListaAdapter(lista!!.filter { n->n.comuna.equals(spinner_bike.selectedItem) }.reversed() as MutableList<Ciclovia>,this)
                            .also { button_invertir.text=resources.getString(R.string.volver) }
                    resources.getString(R.string.volver)->
                        recycler_ciclovias.adapter = ListaAdapter(lista!!.filter { n->n.comuna.equals(spinner_bike.selectedItem) } as MutableList<Ciclovia>,this)
                            .also { button_invertir.text=resources.getString(R.string.invertir) }
                }
            }

             }
    }

    private fun setupCiclovias():MutableList<Ciclovia> {
        return SetupCiclovias().init()
    }
}
