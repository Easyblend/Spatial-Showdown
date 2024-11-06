package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CountriesConquered {

    private String countryName;
    private String username;
    private String flag;
    private double area;
    private String action;
}
