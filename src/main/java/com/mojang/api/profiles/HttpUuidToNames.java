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

import com.google.gson.Gson;
import com.mojang.api.http.BasicHttpClient;
import com.mojang.api.http.HttpClient;
import com.mojang.api.http.HttpHeader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class HttpUuidToNames implements PlayerNameRepository
{
    private static Gson gson = new Gson();
    private HttpClient client;
    private final String agent;

    public HttpUuidToNames(String agent)
    {
        this(agent, BasicHttpClient.getInstance());
    }

    public HttpUuidToNames(String agent, HttpClient client)
    {
        this.agent = agent;
        this.client = client;
    }

    public String getCurrentPlayerNameByUUID (UUID playerUUID)
    {
        PlayerName[] names = this.getNamesByUUID(playerUUID);

        if (names.length == 0) return null;

        PlayerName name = names[names.length -1];
        return name.getName();
    }

    public PlayerName[] getNamesByUUID (UUID playerUUID)
    {
        List<PlayerName> names = new ArrayList<>();
        try
        {
            List<HttpHeader> headers = new ArrayList<>();
            headers.add(new HttpHeader("User-Agent", this.agent));
            PlayerName[] result = get(getUuidUrl(playerUUID), headers);
            names.addAll(Arrays.asList(result));
        }
        catch (Exception e)
        {
            // TODO: logging and allowing consumer to react?
        }
        return names.toArray(new PlayerName[names.size()]);
    }

    private URL getUuidUrl(UUID uuid) throws MalformedURLException
    {
        // To lookup Minecraft profiles, agent should be "minecraft"
        return new URL("https://api.mojang.com/user/profiles/" + uuid.toString().replace("-","") + "/names");
    }

    private PlayerName[] get(URL url, List<HttpHeader> headers) throws IOException
    {
        String response = client.get(url, headers);
        return gson.fromJson(response, PlayerName[].class);
    }

}
