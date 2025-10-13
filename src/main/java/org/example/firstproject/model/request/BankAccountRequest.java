package org.example.firstproject.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Data
public class BankAccountRequest {
    @NotNull(message = "Must provide Account number")
    private String accountNumber;
    @Positive(message = "To deposit amount should be greater than 0.00")
    private BigDecimal amount;
}
