package me.totalfreedom.obsidian.http;

public enum WebStatus
{
    OK(200),
    CREATED(201),
    NO_CONTENT(204),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    private final int code;

    WebStatus(final int code)
    {
        this.code = code;
    }

    public int getCode()
    {
        return code;
    }
}

