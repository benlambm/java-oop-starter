package edu.vwcc.itp220mod2;

/**
 * A section with no scheduled meetings at all -- work anytime, online.
 * ("Async" is short for asynchronous.)
 *
 * Note the empty parentheses: a record with zero components is legal.
 * There is no data to carry; this type exists purely so a Section can hold
 * it as its Modality and get the right describe()/isOnline() behavior.
 *
 * Instructions:
 * Implement both methods.
 *   describe()  must return exactly:  "Online, no scheduled meetings"
 *   isOnline()  yes.
 */
public record OnlineAsync() implements Modality {

    @Override
    public String describe() {
        // TODO: your code goes here
        return "";
    }

    @Override
    public boolean isOnline() {
        // TODO: your code goes here
        return false;
    }
}
