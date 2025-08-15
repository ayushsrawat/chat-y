package com.ayushrawat.chat.payload.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisteredEvent {

  private Integer id;
  private String username;
  private String email;
  private Integer userRole;

}

