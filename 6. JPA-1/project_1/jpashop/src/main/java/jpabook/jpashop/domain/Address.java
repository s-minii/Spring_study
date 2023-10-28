package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;


// 값 타입은 기본적으로 변경 불가능하게 설계해야 한다. (Setter 설정X)
@Embeddable // 내장될 수 있다.
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
