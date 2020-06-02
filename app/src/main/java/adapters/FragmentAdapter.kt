package adapters

import android.content.Context
import androidx.fragment.app.*
import fragments.*

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