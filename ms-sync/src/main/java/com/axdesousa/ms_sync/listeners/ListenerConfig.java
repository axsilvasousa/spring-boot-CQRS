package com.axdesousa.ms_sync.listeners;

public interface ListenerConfig {
    void listenToCustomerQueue(String message);
    void listenToBeautyProcedureQueue(String message);
    void listenToAppointmentQueue(String message);

}
