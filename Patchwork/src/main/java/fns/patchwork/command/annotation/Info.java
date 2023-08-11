/*
 * This file is part of Freedom-Network-Suite - https://github.com/AtlasMediaGroup/Freedom-Network-Suite
 * Copyright (C) 2023 Total Freedom Server Network and contributors
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
 * This interface holds the information for each command. This annotation defines the command's name, description,
 * usage, and aliases. Commands <b><u>must</u></b> have this annotation present to be registered with the handler.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Info
{
    /**
     * Technically, this is the only required value you must supply yourself. However, it is HIGHLY recommended you
     * supply the other optional values as well, for better customization of your command.
     *
     * @return The command's name.
     */
    String name();

    /**
     * By default, this is set to <u>"This is the default command description."</u>
     *
     * @return The command's description.
     */
    String description() default "This is the default command description.";

    /**
     * By default, this is set to <u>"/&lt;command&gt;"</u>
     *
     * @return The command's usage.
     */
    String usage() default "/<command>";

    /**
     * By default, this returns an empty array.
     *
     * @return The command's aliases.
     */
    String[] aliases() default {};
}
