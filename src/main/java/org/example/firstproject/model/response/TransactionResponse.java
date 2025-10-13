package org.example.firstproject.model.response;

import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionResponse {
    private String transactionId;
    private String fromAccountNumber;
    private String toAccountNumber;
    private BigDecimal amount;
}
