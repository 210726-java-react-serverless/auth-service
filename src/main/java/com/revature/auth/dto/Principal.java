package com.revature.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data @NoArgsConstructor
public class Principal {

    private String id;
    private String username;

}
