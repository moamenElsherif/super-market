package com.app.supermarket.presentation.main.cart

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import com.app.supermarket.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CartBottomSheet: BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.cart_bottom_sheet, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : BottomSheetDialog(requireContext(), theme) {
        }.apply {
            setCancelable(false)
            setCanceledOnTouchOutside(false)

            setOnShowListener {
                val bottomSheetDialog = it as BottomSheetDialog
                val parentLayout =
                    bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
                parentLayout?.let { it ->
                    val behaviour = BottomSheetBehavior.from(it)
//                    val peekHeight = resources.getDimensionPixelSize(R.dimen.maxheight)
//                    behaviour.peekHeight = peekHeight
                    behaviour.state = BottomSheetBehavior.STATE_EXPANDED
                    behaviour.isFitToContents = true
                    behaviour.expandedOffset = 200
                    setupFullHeight(it)
                }
                val myFragment = CartFragment()
                childFragmentManager.beginTransaction()
                    .add(R.id.container, myFragment)
                    .commit()
            }
        }
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }
}