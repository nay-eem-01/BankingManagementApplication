package org.example.firstproject.model.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountResponse {
    private String accountNumber;
    private BigDecimal balance;
}
