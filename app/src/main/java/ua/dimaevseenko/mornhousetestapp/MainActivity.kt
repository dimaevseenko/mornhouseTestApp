package ua.dimaevseenko.mornhousetestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ua.dimaevseenko.mornhousetestapp.databinding.ActivityMainBinding
import ua.dimaevseenko.mornhousetestapp.fragment.MainFragment
import ua.dimaevseenko.mornhousetestapp.fragment.SecondFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.bind(layoutInflater.inflate(R.layout.activity_main, null, false))
    }

    @Inject
    lateinit var mainFragment: MainFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setContentView(binding.root)

        if(savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .add(binding.mainContainer.id, mainFragment, MainFragment.TAG).commit()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.findFragmentByTag(SecondFragment.TAG) != null)
            supportFragmentManager.beginTransaction()
                .replace(binding.mainContainer.id, mainFragment, MainFragment.TAG).commit()
        else
            super.onBackPressed()
    }
}