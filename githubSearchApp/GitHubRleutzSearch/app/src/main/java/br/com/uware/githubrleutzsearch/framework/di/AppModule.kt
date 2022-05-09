package br.com.uware.githubrleutzsearch.framework.di


//@InstallIn(SingletonComponent::class)
//@Module
//object AppModule {
//
////    @Singleton
////    @Provides
////    fun provideGitHubRepositoryDao(gitHubRepositoryDatabase: GitHubRepositoryDatabase):
////            GitHubRepositoryDatabaseDao = gitHubRepositoryDatabase.gitHubRepositoryDatabaseDao()
////
////    @Singleton
////    @Provides
////    fun provideAppDatabase(@ApplicationContext context: Context): GitHubRepositoryDatabase =
////        Room.databaseBuilder(context, GitHubRepositoryDatabase::class.java, "github_leutz.db")
////            .fallbackToDestructiveMigration().build()
//}