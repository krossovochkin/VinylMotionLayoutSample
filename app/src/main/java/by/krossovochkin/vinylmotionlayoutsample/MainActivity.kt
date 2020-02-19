package by.krossovochkin.vinylmotionlayoutsample


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    AppCompatActivity(),
    ViewPager.OnPageChangeListener,
    MotionFragmentListener {

    private val callbacks: MutableSet<MotionScrollListener> = mutableSetOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pager.adapter = object :
            FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

            override fun getCount(): Int {
                return 100
            }

            override fun getItem(position: Int): Fragment {
                return MotionFragment.newInstance(position)
            }
        }
        pager.addOnPageChangeListener(this)
    }

    override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
    ) {
        callbacks.forEach { it.onPageScrolled(position, positionOffset) }
    }

    override fun onPageScrollStateChanged(state: Int) = Unit

    override fun onPageSelected(position: Int) = Unit

    override fun register(callback: MotionScrollListener) {
        callbacks.add(callback)
    }

    override fun unregister(callback: MotionScrollListener) {
        callbacks.remove(callback)
    }
}

interface MotionScrollListener {

    fun onPageScrolled(
        position: Int,
        positionOffset: Float
    )
}
