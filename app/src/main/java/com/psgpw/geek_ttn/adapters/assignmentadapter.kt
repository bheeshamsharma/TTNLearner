import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.data.dummymodel.Assignment
import com.psgpw.geek_ttn.data.dummymodel.Course

class assignmentadapter(
    val context: Context,
    var listener: ClickListener,
    var list: List<Assignment>
) :
    RecyclerView.Adapter<assignmentadapter.ViewHolder>() {
    interface ClickListener {
        fun onItemClick(data: Assignment?)
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var assignmentName: TextView = view.findViewById(R.id.tv_name)
        var assignmentDesc: TextView = view.findViewById(R.id.tv_desc)
        var assignmentLink: TextView = view.findViewById(R.id.tv_link)
        var submit: Button = view.findViewById(R.id.enroll_now)

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
        holder.assignmentName.text = item.assignment_name
        holder.assignmentDesc.text = item.description
        holder.assignmentLink.text = item.assign_link

        if(item.assignment_state.equals("submitted")){
            holder.submit.isEnabled = false
            holder.submit.text = "Already Submitted"
        }

        if(item.assignment_state.equals("done")){
            holder.submit.isEnabled = false
            holder.submit.text = "DONE"
        }


        holder.submit.setOnClickListener {
            listener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
