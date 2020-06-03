package fragments

import adapters.DrillRecyclerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.creativeleague.drillable.DataManager
import com.creativeleague.drillable.Drill
import com.creativeleague.drillable.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DrillBankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class DrillBankFragment : Fragment() {
    val db = FirebaseFirestore.getInstance()
    lateinit var recyclerView : RecyclerView

    val drills = mutableListOf<Drill>(
        //Drill( name = "Shooting", length = 20, content = "Shoot the ball hard", rating = mutableMapOf("Hej" to 3)),
        //Drill(name = "Defense", length = 15, content = "Play tough defense with a lot of grit like Kobe Bryant", rating = mutableMapOf("Hej" to 3))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*val newDrill =  Drill("Name", 15, "Content.", mutableMapOf("UserID" to 1))
        db.collection("drills")
            .add(newDrill)
            .addOnSuccessListener { documentReference ->
                Log.d("DRILL ADDED TO DATABASE", "DocumentSnapshot added with ID: " + documentReference.id)
            }
            .addOnFailureListener { e ->
                Log.w("FAILED TO ADD DRILL", "Error adding document", e)
            }*/
        drillsFromDatabase()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_drill_bank, container, false)
        val adapter = DrillRecyclerAdapter(requireContext(), drills)
        recyclerView = view?.findViewById(R.id.drillRecyclerView)!!
        val searchFab = view.findViewById<FloatingActionButton>(R.id.searchFab)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        searchFab.setOnClickListener {

        }
        return view
    }

    private fun drillsFromDatabase() {
        val drillsRef = db.collection("drills")
        drillsRef.addSnapshotListener { snapshot, e ->
            if(snapshot != null) {
                drills.clear()
                for(document in snapshot.documents) {
                    val newDrill = document.toObject(Drill::class.java)
                    val message = newDrill!!.name
                    if (newDrill != null)
                        drills.add(newDrill!!)
                }
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DrillBankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DrillBankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
