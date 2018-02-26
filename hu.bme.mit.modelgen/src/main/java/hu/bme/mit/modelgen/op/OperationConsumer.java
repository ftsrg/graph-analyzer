package hu.bme.mit.modelgen.op;

public interface OperationConsumer<N, T> {

    public void consume(Operation<N, T> operation);

}
