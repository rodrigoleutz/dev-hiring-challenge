package br.com.uware.database.dao


/**
 * GitHubDatabaseDao
 *
 * @author Rodrigo Leutz
 * @version 1.0.0 - 08/05/2022 - Initial release.
 */
//@Dao
//interface GitHubRepositoryDatabaseDao {
//
//    @Query("SELECT * FROM github_repositories")
//    fun getAllGitHubRepositories(): Flow<List<GitHubRepositoryEntity>>
//
//    @Query("SELECT * from github_repositories WHERE id = :id")
//    suspend fun getGitHubRepository(id: String): GitHubRepositoryEntity
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(gitHubRepositoryEntity: GitHubRepositoryEntity)
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun update(gitHubRepositoryEntity: GitHubRepositoryEntity)
//
//    @Delete
//    suspend fun delete(gitHubRepositoryEntity: GitHubRepositoryEntity)
//
//    @Query("DELETE from github_repositories")
//    suspend fun deleteAll()
//}