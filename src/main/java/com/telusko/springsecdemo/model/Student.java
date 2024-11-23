package com.telusko.springsecdemo.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor @NoArgsConstructor @ToString @Getter @Setter
public class Student {
    private int id ;
    private String name ;
    private String tech;
}
