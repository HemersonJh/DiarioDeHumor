package com.example.diariodehumor;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "humor_entries")
public class HumorEntry {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String humor;         // Ex: "feliz", "triste", "ansioso"
    public String anotacao;      // Texto escrito pelo usuário
    public String data;          // Ex: "08/06/2025"

    // Construtor
    public HumorEntry(String humor, String anotacao, String data) {
        this.humor = humor;
        this.anotacao = anotacao;
        this.data = data;
    }
}
