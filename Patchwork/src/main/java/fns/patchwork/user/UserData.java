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

package fns.patchwork.user;

import fns.patchwork.display.adminchat.AdminChatFormat;
import fns.patchwork.economy.EconomicEntityData;
import fns.patchwork.security.Group;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface UserData extends EconomicEntityData
{
    @NotNull UUID getUniqueId();

    String getUsername();

    User getUser();

    @Nullable Group getGroup();

    void setGroup(@Nullable Group group);

    long getPlaytime();

    void setPlaytime(long playtime);

    void addPlaytime(long playtime);

    void resetPlaytime();

    boolean canInteract();

    void setInteractionState(boolean canInteract);

    boolean hasCustomACFormat();

    void setCustomACFormat(final String customACFormat);

    AdminChatFormat getCustomACFormat();
}
