package hee.study.todo.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hee.study.data.source.AppDatabase
import javax.inject.Singleton

// Abstract classes cannot be used immediately
// Because Hilt don't know where the abstract class is implemented
// must create a module and implement it so that Hilt can go to the implementation
@Module
// ApplicationComponent is a singleton range
// It continues to return the same hilt instance until the application is created and died.
// It fits the ROOM rules to make the DB into a singleton.
@InstallIn(SingletonComponent::class)
object DBModule {

    // create DB in singleton
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }
}