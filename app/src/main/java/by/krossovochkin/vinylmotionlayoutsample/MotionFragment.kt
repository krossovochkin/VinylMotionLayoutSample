package by.krossovochkin.vinylmotionlayoutsample

import android.content.Context
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_motion.*
import java.util.*

private const val KEY_EXTRA_POSITION = "KEY_EXTRA_POSITION"

class MotionFragment :
    Fragment(R.layout.fragment_motion),
    MotionScrollListener {

    private var position: Int = -1
    private var listener: MotionFragmentListener? = null

    private val random = Random()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? MotionFragmentListener
        listener?.register(this)
    }

    override fun onDetach() {
        super.onDetach()
        listener?.unregister(this)
        listener = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        position = requireArguments().getInt(KEY_EXTRA_POSITION)

        cover.setColor(
            ContextCompat.getColor(
                requireContext(),
                COVER_COLORS[random.nextInt(COVER_COLORS.size)]
            )
        )
    }

    override fun onPageScrolled(
        position: Int,
        positionOffset: Float
    ) {
        val progress = when (position) {
            this.position -> positionOffset
            this.position + 1 -> 1 - positionOffset
            this.position - 1 -> 1 - positionOffset
            else -> 0f
        }
        motionLayout?.progress = progress
    }

    companion object {

        private val COVER_COLORS = listOf(
            R.color.coverColor1,
            R.color.coverColor2,
            R.color.coverColor3,
            R.color.coverColor4,
            R.color.coverColor5,
            R.color.coverColor6,
            R.color.coverColor7,
            R.color.coverColor8,
            R.color.coverColor9,
            R.color.coverColor10
        )

        fun newInstance(position: Int): Fragment {
            return MotionFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_EXTRA_POSITION, position)
                }
            }
        }
    }
}

interface MotionFragmentListener {

    fun register(callback: MotionScrollListener)

    fun unregister(callback: MotionScrollListener)
}

