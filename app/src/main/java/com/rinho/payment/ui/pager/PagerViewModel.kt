package com.rinho.payment.ui.pager

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel


/**
 * perform business logic and store ui states for [PagerFragment].
 */
class PagerViewModel @ViewModelInject constructor() : ViewModel() {

    /**
     * the current selected page position, -1 means no selected page.
     */
    var currentSelectedPage = -1
}