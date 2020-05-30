package adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import fragments.ClipBoardFragment
import fragments.DrillBankFragment
import fragments.TeamsFragment

class FragmentAdapter (context: Context, fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> DrillBankFragment()
            1 -> TeamsFragment()
            else -> ClipBoardFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Drills"
            1 -> "Teams"
            else -> "ClipBoard"
        }
    }
}