package com.codecool.trivia.dto.payload;

import java.util.List;

public record JwtResponse(String jwt, String userName, List<String> roles) {
}
