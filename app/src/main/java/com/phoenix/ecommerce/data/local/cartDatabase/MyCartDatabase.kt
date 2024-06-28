package com.phoenix.ecommerce.data.local.cartDatabase

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CartProduct::class], version = 2)
abstract class MyCartDatabase : RoomDatabase() {
    abstract fun CartDao() : CartDao

    companion object{
        @Volatile
        private var INSTANCE: MyCartDatabase? = null

        fun getDatabase(context: android.content.Context) : MyCartDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyCartDatabase::class.java,
                    "cart_database"
                ).build()

                INSTANCE = instance
                return  instance
            }
        }

    }

}
