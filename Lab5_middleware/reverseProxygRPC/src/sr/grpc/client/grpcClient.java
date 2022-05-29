/*
 * Copyright 2015, Google Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *    * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following disclaimer
 * in the documentation and/or other materials provided with the
 * distribution.
 *
 *    * Neither the name of Google Inc. nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package sr.grpc.client;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import sr.grpc.gen.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


public class grpcClient {

    private final ManagedChannel channel;

    private final CalculatorGrpc.CalculatorBlockingStub calcBlockingStub;
    private final CalculatorGrpc.CalculatorStub calcNonBlockingStub;
    private final CalculatorGrpc.CalculatorFutureStub calcFutureStub;

    private final AdvancedCalculatorGrpc.AdvancedCalculatorBlockingStub advCalcBlockingStub;


    /**
     * Construct client connecting to HelloWorld server at {@code host:port}.
     */
    public grpcClient(String remoteHost, int remotePort) {
        channel = ManagedChannelBuilder.forAddress(remoteHost, remotePort)
                .usePlaintext() // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid needing certificates.
                .build();


        calcBlockingStub = CalculatorGrpc.newBlockingStub(channel);
        calcNonBlockingStub = CalculatorGrpc.newStub(channel);
        calcFutureStub = CalculatorGrpc.newFutureStub(channel);

        advCalcBlockingStub = AdvancedCalculatorGrpc.newBlockingStub(channel);

    }

    public static void main(String[] args) throws Exception {
        grpcClient client = new grpcClient("192.168.8.100", 80);
        client.test();
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }


    public void test() throws InterruptedException {
        String line = null;
        java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        ListenableFuture<ArithmeticOpResult> future = null;

        do {
            try {
                System.out.print("==> ");
                System.out.flush();
                line = in.readLine();
                if (line == null) {
                    break;
                }
                if (line.equals("add1")) {
                    ArithmeticOpArguments request = ArithmeticOpArguments.newBuilder().setArg1(44).setArg2(55).build();
                    ArithmeticOpResult result = calcBlockingStub.add(request);
                    System.out.println(result.getRes());
                }
                if (line.equals("add2")) {
                    ArithmeticOpArguments request = ArithmeticOpArguments.newBuilder().setArg1(4444).setArg2(5555).build();
                    ArithmeticOpResult result = calcBlockingStub.add(request);
                    System.out.println(result.getRes());
                }
                if (line.equals("subtract")) {
                    try {
                        ArithmeticOpArguments request = ArithmeticOpArguments.newBuilder().setArg1(4444).setArg2(5555).build();
                        ArithmeticOpResult result = calcBlockingStub.subtract(request);
                        System.out.println(result.getRes());
                    } catch (StatusRuntimeException e) {
                        System.out.println(e.getStatus());
                    }
                }
                if (line.equals("add-deadline1")) {
                    try {
                        ArithmeticOpArguments request = ArithmeticOpArguments.newBuilder().setArg1(44).setArg2(55).build();
                        ArithmeticOpResult result = calcBlockingStub.withDeadlineAfter(100, TimeUnit.MILLISECONDS).add(request);
                        System.out.println(result.getRes());
                    } catch (io.grpc.StatusRuntimeException e) {
                        System.out.println("DEADLINE EXCEEDED");
                    }
                }
                if (line.equals("add-deadline2")) {
                    try {
                        ArithmeticOpArguments request = ArithmeticOpArguments.newBuilder().setArg1(4444).setArg2(5555).build();
                        ArithmeticOpResult result = calcBlockingStub.withDeadlineAfter(100, TimeUnit.MILLISECONDS).add(request);
                        System.out.println(result.getRes());
                    } catch (io.grpc.StatusRuntimeException e) {
                        System.out.println("DEADLINE EXCEEDED");
                    }
                } else if (line.equals("complex-sum")) {
                    ComplexArithmeticOpArguments request = ComplexArithmeticOpArguments.newBuilder()
                            .setOptype(OperationType.SUM).addAllArgs(Arrays.asList(4.0, 5.0, 3.1415926))
                            .build();
                    ComplexArithmeticOpResult result = advCalcBlockingStub.complexOperation(request);
                    System.out.println(result.getRes());
                } else if (line.equals("complex-avg")) {
                    ComplexArithmeticOpArguments request = ComplexArithmeticOpArguments.newBuilder()
                            .setOptype(OperationType.AVG).addAllArgs(Arrays.asList(4.0, 5.0, 8.5))
                            .build();
                    ComplexArithmeticOpResult result = advCalcBlockingStub.complexOperation(request);
                    System.out.println(result.getRes());
                } else if (line.equals("callback-add")) {
                    ArithmeticOpArguments request = ArithmeticOpArguments.newBuilder().setArg1(4444).setArg2(5555).build();
                    StreamObserver<ArithmeticOpResult> responseObserver = new StreamObserver<>() {
                        @Override
                        public void onError(Throwable t) {
                            System.out.println("gRPC ERROR");
                        }

                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onNext(ArithmeticOpResult res) {
                            System.out.println(res.getRes() + " (async)");
                        }
                    };
                    calcNonBlockingStub.add(request, responseObserver);
                } else if (line.equals("future-add-1")) {
                    ArithmeticOpArguments request = ArithmeticOpArguments.newBuilder().setArg1(4444).setArg2(5555).build();
                    ListenableFuture<ArithmeticOpResult> future2 = calcFutureStub.add(request);
                    Futures.addCallback(future2, new FutureCallback<>() {
                        @Override
                        public void onSuccess(ArithmeticOpResult result) {
                            System.out.println(result.getRes() + " (future)");
                        }

                        @Override
                        public void onFailure(Throwable throwable) {
                            System.out.println("gRPC ERROR");
                        }
                    }, MoreExecutors.directExecutor());
                } else if (line.equals("future-add-2a")) {
                    ArithmeticOpArguments request = ArithmeticOpArguments.newBuilder().setArg1(4444).setArg2(5555).build();
                    future = calcFutureStub.add(request);
                } else if (line.equals("future-add-2b")) {
                    try {
                        assert future != null;
                        ArithmeticOpResult result = future.get();
                        System.out.println(result.getRes() + " (future)");
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                } else if (!line.equals("")) {
                    System.out.println("???");
                }
            } catch (java.io.IOException ex) {
                ex.printStackTrace();
            }
        }
        while (!Objects.equals(line, "x"));

        shutdown();
    }
}
