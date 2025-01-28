package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;

public record ProfileDto(@NotBlank String name) {
}