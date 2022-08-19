package ua.dimaevseenko.mornhousetestapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import ua.dimaevseenko.mornhousetestapp.di.component.AppComponent
import ua.dimaevseenko.mornhousetestapp.di.component.DaggerAppComponent

class App: Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().bindContext(this).build()
    }
}

val Context.appComponent: AppComponent
    get() = if(this is App) appComponent else applicationContext.appComponent

val Fragment.appComponent: AppComponent
    get() = requireContext().appComponent