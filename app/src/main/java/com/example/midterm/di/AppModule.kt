package com.example.midterm.di

import android.content.Context
import androidx.room.Room
import com.example.midterm.data.local.database.AppDatabase
import com.example.midterm.data.repository.UserRepositoryImpl
import com.example.midterm.domain.repository.UserRepository
import com.example.midterm.domain.usecase.GetUsersUseCase
import com.example.midterm.domain.usecase.InsertUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        )
            .addMigrations(AppDatabase.MIGRATION_1_2)
            .build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase) = database.userDao()

    @Provides
    fun provideUserRepository(userDao: com.example.midterm.data.local.dao.UserDao): UserRepository {
        return UserRepositoryImpl(userDao)
    }

    @Provides
    fun provideGetUsersUseCase(repository: UserRepository): GetUsersUseCase {
        return GetUsersUseCase(repository)
    }

    @Provides
    fun provideInsertUserUseCase(repository: UserRepository): InsertUserUseCase {
        return InsertUserUseCase(repository)
    }
}
