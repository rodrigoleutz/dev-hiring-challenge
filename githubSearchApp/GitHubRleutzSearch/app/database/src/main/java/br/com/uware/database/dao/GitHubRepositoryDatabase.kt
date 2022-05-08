package br.com.uware.database.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.uware.database.entity.GitHubRepositoryEntity


/**
 * GitHubRepositoryDatabase
 *
 * @author Rodrigo Leutz
 * @version 1.0.0 - 08/05/2022 - Initial release.
 */
@Database(entities = [GitHubRepositoryEntity::class], version = 1, exportSchema = false)
abstract class GitHubRepositoryDatabase: RoomDatabase() {
    abstract fun gitHubRepositoryDatabaseDao(): GitHubRepositoryDatabaseDao
}