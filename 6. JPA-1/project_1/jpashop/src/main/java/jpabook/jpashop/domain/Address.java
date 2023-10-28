package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Embeddable // 내장될 수 있다.
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
