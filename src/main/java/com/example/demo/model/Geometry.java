package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Geometry {
    private String type; // "Point", "LineString", "Polygon"
    private Object coordinates; // Change to Object to accept different structures
}
