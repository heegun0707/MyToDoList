package hee.study.todo.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hee.study.data.repositoryimpl.TodoRepositoryImpl
import hee.study.data.repositoryimpl.WeatherRepositoryImpl
import hee.study.domain.repository.TodoRepository
import hee.study.domain.repository.WeatherRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTodoRepository(todoRepositoryImpl: TodoRepositoryImpl): TodoRepository

    @Binds
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}