package ua.dimaevseenko.mornhousetestapp.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ua.dimaevseenko.mornhousetestapp.db.FactDB
import ua.dimaevseenko.mornhousetestapp.db.dao.FactDao
import ua.dimaevseenko.mornhousetestapp.network.request.RFact

@Module(includes = [RetrofitModule::class, NetworkModule::class, DBModule::class])
object AppModule

@Module
object DBModule{

    @Provides
    fun provideFactDB(context: Context): FactDB{
        return Room.databaseBuilder(context, FactDB::class.java, "database").build()
    }
}

@Module
object NetworkModule{

    @Provides
    fun provideRFact(retrofit: Retrofit): RFact{
        return retrofit
            .create(RFact::class.java)
    }
}

@Module
object RetrofitModule{

    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://numbersapi.com/")
            .build()
    }
}