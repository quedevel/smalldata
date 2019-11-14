package org.jarvis.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RepVO {

   private Integer rno,no;
   
   @NotNull
   private Integer score;
   
   @Size(min=1, max=10)
   private String reply;
   
   private Date regDate;
}
