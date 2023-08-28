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

package fns.patchwork.command.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation holds the permission information for each command. This annotation defines the command's permission,
 * whether it is only for players, and the message to send if the sender does not have permission to use the command.
 * <p>
 * Classes <u><b>MUST</b></u> have this annotation present to be registered with the handler.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Permissive
{
    /**
     * @return The command's permission.
     */
    String perm();

    /**
     * By default, this is set to <u>false</u>.
     *
     * @return True if the command is only for players, false otherwise.
     */
    boolean onlyPlayers() default false;

    /**
     * By default, this is set to <u>"You do not have permission to use this command."</u>
     *
     * @return The message to send if the sender does not have permission to use the command.
     */
    String noPerms() default "You do not have permission to use this command.";
}
