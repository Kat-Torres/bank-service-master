package com.nttdata.bankservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "persons")
public class Person {
    @Id
    private Long id;
    private String documentId;
    private String documentNumber;
    private String name;
    private String address;
    private String phone;
    private String email;
}
