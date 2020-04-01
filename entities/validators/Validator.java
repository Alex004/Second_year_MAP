package entities.validators;

public interface Validator<E> {
    void validate(E entity, String s) throws ValidationException;
}
