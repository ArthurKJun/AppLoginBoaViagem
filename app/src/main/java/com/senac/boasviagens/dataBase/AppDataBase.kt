package com.senac.boasviagens.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.senac.boasviagens.dao.DadosDao
import com.senac.boasviagens.dao.DestinoDao
import com.senac.boasviagens.models.Dados
import com.senac.boasviagens.models.Destino

@Database(entities = [Dados::class, Destino::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract val dadosDao: DadosDao
    abstract  val destinoDao : DestinoDao

    //companion é para instanciar apenas 1 vez o banco nao fazer varios acessos
    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null
        fun getDatabase(context : Context): AppDataBase = INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                "appdata-db"
            ).build()
            INSTANCE = instance
            instance
        }
    }

}