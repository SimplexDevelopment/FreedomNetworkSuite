/*
 * This file is part of FreedomNetworkSuite - https://github.com/SimplexDevelopment/FreedomNetworkSuite
 * Copyright (C) 2023 Simplex Development and contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package fns.tyr.oauth;

import com.j256.twofactorauth.TimeBasedOneTimePasswordUtil;
import fns.patchwork.utils.logging.FNS4J;
import java.security.GeneralSecurityException;

/**
 * User-friendly version of TimeBasedOneTimePasswordUtil.
 */
public final class TOTP
{
    private TOTP()
    {
        throw new AssertionError("This class cannot be instantiated.");
    }

    public static String createSecretKey()
    {
        return TimeBasedOneTimePasswordUtil.generateBase32Secret(32);
    }

    public static String createQRCode(final String username, final String secretKey)
    {
        return TimeBasedOneTimePasswordUtil.qrImageUrl(username, secretKey);
    }

    public static boolean verify(final String secretKey, final int userCode)
    {
        try
        {
            int vCode = TimeBasedOneTimePasswordUtil.generateCurrentNumber(secretKey);
            return vCode == userCode;
        }
        catch (GeneralSecurityException ex)
        {
            FNS4J.getLogger("Tyr").error("Failed to verify TOTP code: " + ex.getMessage());
            return false;
        }
    }
}
