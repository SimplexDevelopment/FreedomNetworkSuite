package me.totalfreedom.security;

public interface Verification
{
    public boolean verify(String input);

    public String getVerificationMessage();

    public String getVerificationFailedMessage();

    public String generateVerificationCode();
}
