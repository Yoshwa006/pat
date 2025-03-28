package com.example.billing_service.grpc;

import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);
    List<String> names = new ArrayList<>();
    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest, StreamObserver<billing.BillingResponse> streamObserver){
        names.add((billingRequest.getName()));
        log.info("createBillingAccount request received {}",names);
        // buisness logic - save in db, calculate etc...

        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("123")
                .setStatus("ACTIVE")
                .build();

        streamObserver.onNext(response);
        streamObserver.onCompleted();
    }






}
