package com.fiap.techChallenge.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("category")
    private String category;
    @JsonProperty("price")
    private double price;
    @JsonProperty("description")
    private String description;
    @JsonProperty("images")
    private List<String> images;
}

