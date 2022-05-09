package br.com.uware.githubrleutzsearch.framework.repository


/**
 * GitHubRepository
 *
 * @author Rodrigo Leutz
 * @version 1.0.0 - 08/05/2022 - Initial release.
 */
//class GitHubRepositoryDatabase @Inject constructor(
//    private val gitHubRepositoryDatabaseDao: GitHubRepositoryDatabaseDao
//) {
//
//    fun getAll(): Flow<List<GitHubRepositoryEntity>> =
//        gitHubRepositoryDatabaseDao.getAllGitHubRepositories().flowOn(Dispatchers.IO).conflate()
//
//    suspend fun get(id: String): br.com.uware.core.model.GitHubRepository =
//        gitHubRepositoryDatabaseDao.getGitHubRepository(id).toGitHubRepository()
//
//    suspend fun add(gitHubRepository: GitHubRepository) =
//        gitHubRepositoryDatabaseDao.insert(
//            GitHubRepositoryEntity.fromGitHubRepository(gitHubRepository)
//        )
//
//    suspend fun update(gitHubRepository: GitHubRepository) =
//        gitHubRepositoryDatabaseDao.update(
//            GitHubRepositoryEntity.fromGitHubRepository(
//                gitHubRepository
//            )
//        )
//
//    suspend fun delete(gitHubRepository: GitHubRepository) =
//        gitHubRepositoryDatabaseDao.delete(
//            GitHubRepositoryEntity.fromGitHubRepository(
//                gitHubRepository
//            )
//        )
//
//
//}