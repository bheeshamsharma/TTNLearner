import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.data.dummymodel.coursescreen
import com.psgpw.pickapp.data.models.ChatUser

class assignmentadapter(
    val context: Context,
    var listener: ClickListener,
    var list: List<coursescreen>
) :
    RecyclerView.Adapter<assignmentadapter.ViewHolder>() {
    interface ClickListener {
        fun onItemClick(data: coursescreen?)
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var cousrename: TextView = view.findViewById(R.id.cousrename)

        init {

            // Define click listener for the ViewHolder's View
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): assignmentadapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_assignment, parent, false)
        return assignmentadapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: assignmentadapter.ViewHolder, position: Int) {
        val item = list[position]
        holder.cousrename.text = item.coursename

        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
        }    }

    override fun getItemCount(): Int {
        return list.size
    }
}
