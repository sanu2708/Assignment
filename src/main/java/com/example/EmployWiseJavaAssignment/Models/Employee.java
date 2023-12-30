package com.example.EmployWiseJavaAssignment.Models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    String id;

   private String name;
    private String phoneNumber;
    private String emailId;
    private String reportsTo;
    private String profileImage;

}
