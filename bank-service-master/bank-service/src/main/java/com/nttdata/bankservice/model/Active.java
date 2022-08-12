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
@Document(collection = "actives")
public class Active {

        @Id
        private Long id;
        private String name;
        private Float availableCredit;
        private String personId;
        private Float creditLimited;
    }


