package com.tal.model;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
public class User {
    private String id;
    private String name;
    private String email;
}
