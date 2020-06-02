package adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.creativeleague.drillable.*
import kotlinx.android.synthetic.main.team_item.view.*

class TeamRecyclerAdapter(private val context: Context, private val teams: List<Team>) : RecyclerView.Adapter<TeamRecyclerAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamRecyclerAdapter.ViewHolder {
        val teamView = layoutInflater.inflate(R.layout.team_item, parent, false)
        return ViewHolder(teamView)
    }

    override fun getItemCount() = userTeams.size

    override fun onBindViewHolder(holder: TeamRecyclerAdapter.ViewHolder, position: Int) {
        val team = userTeams[position]
        holder.teamName.text = team.name
    }

    inner class ViewHolder(teamView : View) : RecyclerView.ViewHolder(teamView) {
        val teamName = teamView.findViewById<TextView>(R.id.teamNameText)
    }
}