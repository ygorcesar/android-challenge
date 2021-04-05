package com.android.challenge.base.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.android.challenge.base.R
import com.android.challenge.base.extensions.getColorRes
import com.android.challenge.base.extensions.view.getDimen
import kotlinx.android.synthetic.main.skeleton_view.view.*

class SkeletonView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        val set = intArrayOf(android.R.attr.background)
        val typedArray = context.obtainStyledAttributes(attrs, set)
        val backgroundDrawable = typedArray.getDrawable(0)

        val appearance = context.obtainStyledAttributes(attrs, R.styleable.SkeletonView)
        val skeletonLayoutId = appearance.getResourceId(
            R.styleable.SkeletonView_skeleton_layout,
            R.layout.skeleton_progress_bar_view
        )

        val skeletonElevation = appearance.getDimension(
            R.styleable.SkeletonView_skeleton_elevation,
            getDimen(R.dimen.default_elevation_custom_view).toFloat()
        )
        if (backgroundDrawable != null) background = backgroundDrawable
        else setBackgroundColor(context.getColorRes(R.color.colorSurface))

        View.inflate(context, R.layout.skeleton_view, this)

        elevation = skeletonElevation

        if (skeletonLayoutId != -1) {
            val skeletonLayout = View.inflate(context, skeletonLayoutId, null)
            shimmer.addView(skeletonLayout)
        }

        appearance.recycle()
        typedArray.recycle()
    }
}
