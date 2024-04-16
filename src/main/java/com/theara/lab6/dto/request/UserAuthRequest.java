package com.theara.lab6.dto.request;

public record UserAuthRequest(
        String username,
        String password
) {
}
