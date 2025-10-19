package org.example.bankingManagementApplication.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
@Getter
@Setter
@Data
@ToString
public class BalanceTransferRequest {

    @NotNull(message = "Account number can't be blank")
    private String toAccountNumber;
    @Positive(message = "Amount should be greater than 0.00")
    private BigDecimal amount;
}
