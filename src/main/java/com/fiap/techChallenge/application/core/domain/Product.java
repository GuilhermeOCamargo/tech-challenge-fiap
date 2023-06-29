package com.fiap.techChallenge.application.core.domain;

import com.fiap.techChallenge.application.core.exceptions.InvalidDataException;
import com.google.common.base.Strings;
import lombok.Builder;

import java.util.List;

@Builder
public record Product(Long id, String category, Double price, String description, List<String> images) {

    public Product {
        if (Strings.isNullOrEmpty(description))
            throw new InvalidDataException("Invalid description");

        if (Strings.isNullOrEmpty(category))
            throw new InvalidDataException("Invalid category");

        if (images.isEmpty())
            throw new InvalidDataException("Invalid images");
    }
}