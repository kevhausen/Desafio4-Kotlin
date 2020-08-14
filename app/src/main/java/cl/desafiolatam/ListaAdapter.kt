import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.Ciclovia
import cl.desafiolatam.R

class ListaAdapter(var items: MutableList<Ciclovia>?,private val context: Context) :
    RecyclerView.Adapter<ViewHolder>() {


    // Gets the number of animals in the list


    // Infla la vista del ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_view, parent,
                false))
    }

    //  Enlaza cada comuna en el arreglo a una vista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.nombre?.text = items!![position].nombre
        holder?.comuna?.text = items!![position].comuna
    }

    fun setupData(lista: MutableList<Ciclovia>) {
        items = lista
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return items!!.size
    }
}
class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val nombre = view.findViewById<TextView>(R.id.nombre) as TextView
    val comuna = view.findViewById<TextView>(R.id.comuna) as TextView
}