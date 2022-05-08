package br.com.uware.githubrleutzsearch.framework.di

import android.content.Context
import androidx.room.Room
import br.com.uware.database.dao.GitHubRepositoryDatabase
import br.com.uware.database.dao.GitHubRepositoryDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideGitHubRepositoryDao(gitHubRepositoryDatabase: GitHubRepositoryDatabase):
            GitHubRepositoryDatabaseDao = gitHubRepositoryDatabase.gitHubRepositoryDatabaseDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): GitHubRepositoryDatabase =
        Room.databaseBuilder(context, GitHubRepositoryDatabase::class.java, "github_leutz.db")
            .fallbackToDestructiveMigration().build()
}