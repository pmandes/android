package pl.madsoft.myweather.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.madsoft.myweather.data.local.dao.CityDAO
import pl.madsoft.myweather.data.local.database.MyWeatherAppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): MyWeatherAppDatabase {
        return Room.databaseBuilder(
            context,
            MyWeatherAppDatabase::class.java,
            "my_weather_app_db"
        ).build()
    }

    @Provides
    fun provideCityDao(appDatabase: MyWeatherAppDatabase): CityDAO {
        return appDatabase.cityDao()
    }
}