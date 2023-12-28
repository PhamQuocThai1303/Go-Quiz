package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FieldData {
    String name;
    String detail;
    int fieldID;
    int image;

    FieldData(int fieldID, String name, String detail, int image) {
        this.fieldID = fieldID;
        this.name = name;
        this.detail = detail;
        this.image = image;
    }
}