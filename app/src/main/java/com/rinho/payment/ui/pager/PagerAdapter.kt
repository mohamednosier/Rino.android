package com.rinho.payment.ui.pager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rinho.payment.ui.Accounts.Account_List.AccountListFragment
import com.rinho.payment.ui.transportation.Budgets.BudgetsFragment

/**
 * Pager adapter to navigate between fragments.
 */
class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * The count of the fragments.
     */
    override fun getItemCount() = 2

    /**
     * Get the fragment of every position.
     */
    override fun createFragment(position: Int) = when (position) {
        0 -> AccountListFragment()
        else -> BudgetsFragment()
    }
}