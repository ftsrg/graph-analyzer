package hu.bme.mit.modelgen.op;

public interface OperationProvider<N, T> {

    public void register(OperationConsumer<N, T> consumer);

    public void provide();

}
