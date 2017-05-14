package org.xmlking.mapr.util

import java.io.IOException

import org.springframework.stereotype.Component

import com.mapr.db.MapRDB
import com.mapr.db.Table

@Component
class MapRJsonDBHelper {

    private val table: Table? = null

    /**
     * Get the table, create it if not present

     * @throws IOException
     */
    @Throws(IOException::class)
    fun getTable(tableName: String): Table {
        val table: Table

        if (!MapRDB.tableExists(tableName)) {
            table = MapRDB.createTable(tableName) // Create the table if not already present
        } else {
            table = MapRDB.getTable(tableName) // get the table
        }
        return table
    }

    @Throws(IOException::class)
    fun deleteTable(tableName: String) {
        if (MapRDB.tableExists(tableName)) {
            MapRDB.deleteTable(tableName)
        }

    }


}