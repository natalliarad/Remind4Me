package com.natallia.radaman.remind4me.onboarding

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import com.google.android.material.button.MaterialButton

import com.natallia.radaman.remind4me.R

/**
 * A simple [Fragment] subclass.
 * Use the [OnboardingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OnboardingFragment : Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var onBoardingGoButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupScreen()
        setupViewPager(view)
        setupWelcomePagerControlButton(view)
    }

    private fun setupScreen() {
        activity?.let {
            it.window.statusBarColor = resources.getColor(R.color.neutral_white, null)
            it.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    @SuppressLint("InflateParams")
    private fun setupViewPager(view: View) {
        viewPager = view.findViewById(R.id.view_pager_onboarding)
        val onboardingList = arrayListOf<View>()
        val layoutInflater = LayoutInflater.from(activity)
        val onboardingWelcomeViewStart =
            layoutInflater.inflate(R.layout.view_onboarding_welcome_screen, null)
        val onboardingWecomeViewFirst =
            layoutInflater.inflate(R.layout.view_onboarding_welcome_first_screen, null)

        setupWelcomeIconAnimation(onboardingWelcomeViewStart)

        onboardingList.add(onboardingWelcomeViewStart)
        onboardingList.add(onboardingWecomeViewFirst)

        val onboardingViewPagerAdapter = OnboardingViewPagerAdapter(onboardingList)
        viewPager.adapter = onboardingViewPagerAdapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position == onboardingViewPagerAdapter.count - 1) {
                    onBoardingGoButton.visibility = View.VISIBLE
                }
            }

            override fun onPageSelected(position: Int) {

            }
        })
    }

    private fun setupWelcomeIconAnimation(view: View) {
        val iconWelcome = view.findViewById<ImageView>(R.id.welcome_icon_app)
        val animation = AnimationUtils.loadAnimation(context, R.anim.welcome_icon_animation)
        iconWelcome.startAnimation(animation)
    }

    private fun setupWelcomePagerControlButton(view: View) {
        onBoardingGoButton = view.findViewById(R.id.onboarding_go_button)
        onBoardingGoButton.setOnClickListener {
            activity?.let {
                Navigation.findNavController(it, R.id.navigation_host_fragment).navigateUp()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment Onboarding.
         */
        @JvmStatic
        fun newInstance() = OnboardingFragment()
    }
}
