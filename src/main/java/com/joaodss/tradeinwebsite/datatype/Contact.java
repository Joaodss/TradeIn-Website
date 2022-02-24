package com.joaodss.tradeinwebsite.datatype;

import javax.persistence.Embeddable;

@Embeddable
public class Contact {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
}
