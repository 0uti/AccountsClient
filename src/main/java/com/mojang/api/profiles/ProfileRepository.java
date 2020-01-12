package com.mojang.api.profiles;

public interface ProfileRepository
{
    Profile findProfileByName(String name);
    Profile[] findProfilesByNames(String... names);
}
