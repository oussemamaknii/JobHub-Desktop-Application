/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package paypal.base.exception;

/**
 * A PayPalException is thrown to signal a problem during SDK execution.
 */
public abstract class PayPalException extends Exception
{
    /*
     * Default constructor
     */
    public PayPalException()
    {
        super();
    }

    /*
     * Constructs a new exception with the specified detail message.
     */
    public PayPalException(String message)
    {
        super(message);
    }

    /*
     * Constructs a new exception with the specified detail message and cause.
     */
    public PayPalException(String message, Throwable cause)
    {
        super(message, cause);
    }
} // PayPalException
