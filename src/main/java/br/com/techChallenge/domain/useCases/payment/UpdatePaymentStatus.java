package br.com.techChallenge.domain.useCases.payment;

public interface UpdatePaymentStatus {

    void execute(Long merchantOrderId);
}
