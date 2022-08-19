package ua.dimaevseenko.mornhousetestapp.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ua.dimaevseenko.mornhousetestapp.MainActivity
import ua.dimaevseenko.mornhousetestapp.di.module.AppModule
import ua.dimaevseenko.mornhousetestapp.fragment.MainFragment
import ua.dimaevseenko.mornhousetestapp.fragment.SecondFragment

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
    fun inject(secondFragment: SecondFragment)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun bindContext(context: Context): Builder
        fun build(): AppComponent
    }
}