package com.app.mytablayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class HomeFragment : Fragment() {

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_home, container, false)
	}

	companion object {

		private const val ARG_SECTION_NUMBER = "section_number"

		@JvmStatic
		fun newInstance(index: Int) =
			HomeFragment().apply {
				arguments = Bundle().apply {
					putInt(ARG_SECTION_NUMBER, index)
				}
			}
	}
}