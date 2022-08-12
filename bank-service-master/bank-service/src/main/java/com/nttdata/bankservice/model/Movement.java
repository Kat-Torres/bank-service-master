package com.nttdata.bankservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "movements")
public class Movement {
    @Id
    private Long id;
    private String productType;
    private Float amount;
    private Timestamp date;
}
