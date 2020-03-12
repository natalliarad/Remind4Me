package com.natallia.radaman.remind4me

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.natallia.radaman.remind4me.eventsForRemind.BackNavigationRouter
import com.natallia.radaman.remind4me.eventsForRemind.EventsForRemindFragment

class MainActivity : AppCompatActivity(), BackNavigationRouter {

    private lateinit var eventsForRemindFragment: EventsForRemindFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openOnboardingScreenIfNeed()
    }

    override fun onBackPressed() {
        if (!eventsForRemindFragment.onBackPressed() || !eventsForRemindFragment.isVisible) {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.navigation_host_fragment).navigateUp()
    }

    override fun setFragment(eventsRemindFragment: EventsForRemindFragment) {
        eventsForRemindFragment = eventsRemindFragment
    }

    private fun openOnboardingScreenIfNeed() {
        if (AppInformation.isFirstTimeLoading(this)) {
            Navigation.findNavController(this, R.id.navigation_host_fragment)
                .navigate(R.id.action_from_events_to_onboarding)
        }
    }
}
