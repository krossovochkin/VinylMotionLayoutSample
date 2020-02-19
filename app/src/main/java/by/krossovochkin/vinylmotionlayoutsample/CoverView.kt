package by.krossovochkin.vinylmotionlayoutsample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils

class CoverView
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {

    private val offset = 20f
    private val frontPaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.TRANSPARENT
        isAntiAlias = true
    }
    private val backPaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.TRANSPARENT
        isAntiAlias = true
    }
    private val path = Path()

    fun setColor(@ColorInt color: Int) {
        frontPaint.color = color
        backPaint.color = ColorUtils.blendARGB(color, Color.BLACK, 0.2f)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        path.reset()

        canvas.drawRect(0f, offset, measuredWidth.toFloat(), measuredHeight.toFloat(), frontPaint)
        canvas.drawPath(
            path.apply {
                fillType = Path.FillType.EVEN_ODD
                moveTo(0f, offset)
                lineTo(measuredWidth.toFloat(), offset)
                lineTo(measuredWidth.toFloat(), 0f)
                close()
            },
            backPaint
        )
    }

}
