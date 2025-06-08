package com.example.diariodehumor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HumorDao {

    @Insert
    void inserir(HumorEntry entry);

    @Delete
    void deletar(HumorEntry entry);

    @Query("SELECT * FROM humor_entries ORDER BY id DESC")
    List<HumorEntry> getTodos();
}
