package com.rinho.payment.ui.pager

import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import com.rinho.payment.R
import com.rinho.payment.databinding.FragmentSearchPagerBinding
import com.rinho.payment.ui.Accounts.Account_List.AccountListFragment
import com.rinho.payment.ui.base.BaseFragment
import com.rinho.payment.ui.transportation.Budgets.BudgetsFragment
import com.rinho.payment.util.extensions.getDrawableCompat
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

/**
 * ViewPager with tabs to navigate between [AccountListFragment] and [BudgetsFragment].
 */
@AndroidEntryPoint
class PagerFragment :
    BaseFragment<PagerViewModel, FragmentSearchPagerBinding>(PagerViewModel::class.java) {

    /**
     * The arguments of the fragment.
     */
//    private val args by navArgs<PagerFragmentArgs>()

    override fun getLayoutRes() = R.layout.fragment_search_pager

    override fun init() {

        // set adapter of the viewPager
        binding.pager.adapter = PagerAdapter(this)

        // make tabs work with viewPager and init text and icon for every tab.
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when (position) {
                0 -> tab.run {

                    setText("acounts")


                }

                1 -> tab.run {
                    setText("my budgets")

                }
            }
        }.attach()

        // add divider between tabs.
        (binding.tabLayout.getChildAt(0) as LinearLayout).run {
            showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            dividerPadding = 10
            dividerDrawable = context?.getDrawableCompat(R.drawable.divider_tabs)

        }


        binding.pager.post {
            // restore page position state or get from args if no state saved.
            //TODO un comment this after finishing dashboard
//            binding.pager.currentItem =
//                viewModel.currentSelectedPage.takeUnless { it == -1 } ?: args.selectedPage.ordinal

            binding.pager.currentItem =
                viewModel.currentSelectedPage.takeUnless { it == -1 } ?: 0

            binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    // store page position state.
                    viewModel.currentSelectedPage = position
                }
            })
        }
    }

}