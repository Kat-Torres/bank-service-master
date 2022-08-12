package com.nttdata.bankservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "identifications")
public class Identification {
    private Long id;
    private String name;
    private Boolean flgEmpresarial;

}
