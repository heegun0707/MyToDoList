package hee.study.todo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import hee.study.todo.databinding.ActivityMainBinding
import hee.study.todo.ui.AddBottomSheetFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var bottomSheetFragment: AddBottomSheetFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)
        binding.navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_dashboard -> {
//                    bottomSheetFragment?.let {
//                        bottomSheetFragment = AddBottomSheetFragment()
//                        if (it.isAdded) {
//                            return@let
//                        }
//                        it.show(supportFragmentManager, it.tag)
//                    }
                    bottomSheetFragment = AddBottomSheetFragment()
                    bottomSheetFragment!!.show(supportFragmentManager, bottomSheetFragment!!.tag)
                    false
                }
                else -> NavigationUI.onNavDestinationSelected(item, navController)
            }
        }
    }

    companion object {
        private const val BOTTOM_TAG = "bottomSheetFragment"
    }
}