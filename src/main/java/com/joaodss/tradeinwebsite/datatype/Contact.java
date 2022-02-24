package com.joaodss.tradeinwebsite.datatype;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Contact {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
}
