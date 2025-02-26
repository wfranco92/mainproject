package co.com.companywf.usecase;

public abstract class AbstractUseCase<E> {
    public abstract boolean validateBody(E e);
}
