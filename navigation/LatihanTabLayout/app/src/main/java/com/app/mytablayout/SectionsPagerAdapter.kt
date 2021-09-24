package com.app.mytablayout

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
	override fun getItemCount(): Int {
		return 3
	}

	override fun createFragment(position: Int): Fragment {
//		Menampilkan tab layout menggunakan 2 fragment
//		var fragment: Fragment? = null
//		when (position) {
//			0 -> fragment = HomeFragment()
//			1 -> fragment = ProfileFragment()
//		}
//		return fragment as Fragment

//		Membuat tab layout hanya dengan home fragment
		return HomeFragment.newInstance(position + 1)

	}

}
