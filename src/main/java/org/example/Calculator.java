package org.example;

public interface Calculator<T extends Number> {

    double calculateFees(T clubID);

}
