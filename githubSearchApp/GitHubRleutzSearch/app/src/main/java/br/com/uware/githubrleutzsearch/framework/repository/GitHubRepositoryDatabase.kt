package br.com.uware.githubrleutzsearch.framework.repository

import br.com.uware.database.dao.GitHubRepositoryDatabaseDao
import br.com.uware.database.entity.GitHubRepositoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


/**
 * GitHubRepository
 *
 * @author Rodrigo Leutz
 * @version 1.0.0 - 08/05/2022 - Initial release.
 */
class GitHubRepositoryDatabase @Inject constructor(
    private val gitHubRepositoryDatabaseDao: GitHubRepositoryDatabaseDao
) {

    fun getAll(): Flow<List<GitHubRepositoryEntity>> =
        gitHubRepositoryDatabaseDao.getAllGitHubRepositories().flowOn(Dispatchers.IO).conflate()

    suspend fun get(id: String): br.com.uware.core.model.GitHubRepository =
        gitHubRepositoryDatabaseDao.getGitHubRepository(id).toGitHubRepository()

    suspend fun add(gitHubRepository: GitHubRepositoryDatabase) =
        gitHubRepositoryDatabaseDao.insert(
            GitHubRepositoryEntity.fromGitHubRepository(gitHubRepository)
        )

    suspend fun update(gitHubRepository: GitHubRepositoryDatabase) =
        gitHubRepositoryDatabaseDao.update(
            GitHubRepositoryEntity.fromGitHubRepository(
                gitHubRepository
            )
        )

    suspend fun delete(gitHubRepository: GitHubRepositoryDatabase) =
        gitHubRepositoryDatabaseDao.delete(
            GitHubRepositoryEntity.fromGitHubRepository(
                gitHubRepository
            )
        )


}