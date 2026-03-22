package com.app.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDTO {

    private int isbn;
    private String name;
    private boolean available;
    private float price;
    private String author;

}
