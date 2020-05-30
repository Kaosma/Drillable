package fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.creativeleague.drillable.R
import com.creativeleague.drillable.Team
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

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
        val addTeamButton = view.findViewById<FloatingActionButton>(R.id.addTeamFab)
        val clubNameInput = view.findViewById<EditText>(R.id.writeClubName)
        val teamNameInput = view.findViewById<EditText>(R.id.writeTeamName)
        val playersInput = view.findViewById<EditText>(R.id.writeNumberOfPlayers)
        var userTeams = mutableListOf<Team>()
        addTeamButton.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(requireContext())
            dialogBuilder.setView(R.layout.dialog_new_team)
                .setTitle("Add New Team")
                .setPositiveButton("DONE", DialogInterface.OnClickListener { dialog, id ->
                    //val newTeam = Team("$clubNameInput $teamNameInput", playersInput.toString().toInt())
                    //userTeams.add(newTeam)
                    //Log.i("HEJSAN TEAM", userTeams[0].name)
                    //Log.i("HEJSAAAAN", clubNameInput.text.toString())
                    val snackbar = Snackbar.make(view, "New team created", Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(Color.parseColor("#FC5C14"))
                        .setTextColor(Color.parseColor("#F4F4F4")).show()
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                })

            val alert = dialogBuilder.create()
            alert.show()
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
