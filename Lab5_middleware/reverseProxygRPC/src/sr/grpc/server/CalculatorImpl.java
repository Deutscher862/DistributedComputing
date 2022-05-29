package sr.grpc.server;

import sr.grpc.gen.ArithmeticOpResult;
import sr.grpc.gen.CalculatorGrpc;

public class CalculatorImpl extends CalculatorGrpc.CalculatorImplBase {
    @Override
    public void add(sr.grpc.gen.ArithmeticOpArguments request,
                    io.grpc.stub.StreamObserver<sr.grpc.gen.ArithmeticOpResult> responseObserver) {
        System.out.println("addRequest (" + request.getArg1() + ", " + request.getArg2() + ")");
        int val = request.getArg1() + request.getArg2();
        ArithmeticOpResult result = ArithmeticOpResult.newBuilder().setRes(val).build();
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    @Override
    public void subtract(sr.grpc.gen.ArithmeticOpArguments request,
                         io.grpc.stub.StreamObserver<sr.grpc.gen.ArithmeticOpResult> responseObserver) {
        System.out.println("subtractRequest (" + request.getArg1() + ", " + request.getArg2() + ")");

        int val = request.getArg1() - request.getArg2();
        ArithmeticOpResult result = ArithmeticOpResult.newBuilder().setRes(val).build();
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }


}
