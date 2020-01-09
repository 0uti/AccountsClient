/*
 * Copyright (C) 2020 MineStar.de
 *
 * This file is part of accounts-client.
 *
 * accounts-client is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * accounts-client is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with accounts-client.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.mojang.api.profiles;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(JUnit4.class)
public class HttpUuidToNameTests
{
    @Test
    public void findPlayerNamesByUUID_existingUuidProvided_returnsPlayerName() throws Exception{
        PlayerNameRepository repository = new HttpUuidToNames("minecraft");

        String playername = repository.getCurrentPlayerNameByUUID(UUID.fromString("f8cdb683-9e90-43ee-a819-39f85d9c5d69"));
        assertThat(playername, is(equalTo("mollstam")));
    }
}
