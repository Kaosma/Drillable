package fragments

import adapters.DrillRecyclerAdapter
import adapters.TeamRecyclerAdapter
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.*
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.creativeleague.drillable.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import java.lang.Double.parseDouble
import java.lang.Integer.parseInt

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TeamsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TeamsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_teams, container, false)
        val teamRecyclerView = view.findViewById<RecyclerView>(R.id.teamRecyclerView)
        val adapter = TeamRecyclerAdapter(requireContext(), DataManager.userTeams)
        val gridLayoutManager = GridLayoutManager(view.context, 2, GridLayoutManager.VERTICAL, false)
        val addTeamButton = view.findViewById<FloatingActionButton>(R.id.addTeamFab)
        var clubNameInput : EditText? = null
        var teamNameInput : EditText? = null
        var playersInput : EditText? = null

        teamRecyclerView.adapter = adapter
        teamRecyclerView.layoutManager = gridLayoutManager
        addTeamButton.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(requireContext())
            dialogBuilder.setView(R.layout.dialog_new_team)
                .setTitle("Add New Team")
                .setPositiveButton("DONE", DialogInterface.OnClickListener { dialog, id ->
                    var numeric = true
                    try {
                        val num = parseInt(playersInput?.text.toString())
                    } catch (e: NumberFormatException) {
                        numeric = false
                    }
                    if(clubNameInput?.text.toString() != "" && teamNameInput?.text.toString() != "" && playersInput?.text.toString() != "" && numeric) {
                        val newTeam = Team("${clubNameInput?.text} ${teamNameInput?.text}", playersInput?.text.toString().toInt())
                        val snackbar = Snackbar.make(view, "New team ${newTeam.name} created", Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(Color.parseColor("#FC5C14"))
                            .setTextColor(Color.parseColor("#F4F4F4")).show()
                        DataManager.userTeams.add(newTeam)
                    } else {
                        val snackbar = Snackbar.make(view, "Invalid input", Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(Color.parseColor("#F4F4F4"))
                            .setTextColor(Color.RED).show()
                    }

                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                })

            val alert = dialogBuilder.create()
            alert.show()
            clubNameInput = alert.findViewById(R.id.writeClubName)
            teamNameInput = alert.findViewById(R.id.writeTeamName)
            playersInput = alert.findViewById(R.id.writeNumberOfPlayers)
            alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
            alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TeamsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TeamsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
