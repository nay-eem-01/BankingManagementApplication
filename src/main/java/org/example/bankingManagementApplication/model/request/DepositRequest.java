package org.example.bankingManagementApplication.model.request;

import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class DepositRequest {
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0.00")
    private BigDecimal amount;
}
