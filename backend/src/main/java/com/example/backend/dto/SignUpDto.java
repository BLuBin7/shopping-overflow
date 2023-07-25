package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Binh
 * Date : 7/27/2023 - 11:12 PM
 * Description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {

    private String firstName;

    private String lastName;

    private String login;

    private char[] password;

}
