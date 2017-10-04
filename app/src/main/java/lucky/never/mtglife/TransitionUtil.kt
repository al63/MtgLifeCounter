package lucky.never.mtglife

import android.support.transition.ChangeBounds;
import android.support.transition.Fade
import android.support.transition.TransitionManager
import android.support.transition.TransitionSet
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.view.ViewGroup

/**
 * Created by aleclee on 10/3/17.
 */

object TransitionUtil {

    private val DURATION_MS = 800L
    private val INTERPOLATOR = FastOutSlowInInterpolator()

    fun beginTransition(container: ViewGroup) {
        val changeBounds = ChangeBounds()
        changeBounds.duration = DURATION_MS
        changeBounds.interpolator = INTERPOLATOR
        val fadeOut = Fade(Fade.OUT)
        fadeOut.duration = DURATION_MS
        fadeOut.interpolator = INTERPOLATOR
        val fadeIn = Fade(Fade.IN)
        fadeIn.duration = DURATION_MS
        fadeIn.interpolator = INTERPOLATOR

        val transitionSet = TransitionSet()
        transitionSet.ordering = TransitionSet.ORDERING_TOGETHER
        TransitionManager.beginDelayedTransition(container, transitionSet.addTransition(fadeOut)
                .addTransition(changeBounds)
                .addTransition(fadeIn))
    }
}
