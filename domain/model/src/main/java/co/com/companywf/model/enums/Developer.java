package co.com.companywf.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Developer {
    SONY("2cd18c70-681e-4382-9bf0-307f42e09f08"),
    ESQUARE_ENIX("61bfd913-f5dd-4c80-8302-c755ba55c119"),
    NINTENDO("83228eab-5bc2-41cf-bc00-17b9efd91677"),
    EPIC_GAMES("8c112ec0-f308-4b80-a5d0-c94cbac1865d"),
    SEGA("901d597b-9b70-4c5d-96e3-0a36e56a7737"),
    EA_GAMES("b3692154-57b9-4391-8edf-e1251ce1d831"),
    NAUGHTY_DOG("c51db7c1-ad17-4f70-aafe-f070acfa40a7"),
    ACTIVISION("cf6e3a75-35a8-4f80-bb14-dd27f573fc6b"),
    BANDAI_NAMCO("d339656b-cb98-4dd4-8a87-befaa11c524e"),
    FROM_SOFTWARE("d5cfa904-eb28-42cc-b5ed-22bed4565fe5"),
    RIOT_GAMES("e5d809ca-6587-4b60-9b83-ce865639881d"),
    CRYTEK("96a50918-3507-4a83-aac4-f20b475620ed"),
    OTRO("dc348198-23f9-4b92-82bc-916160510c14"),
    UBISOFT("f184b778-7f85-4dcb-8431-3d5e9b57a6aa");

    private final String id;

    public static String idFromName(String name){
        return Arrays.stream(values())
                .filter(status -> status.name().contentEquals(name))
                .map(Developer::getId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(name + " - DEVELOPER_NOT_FOUND_OR_INEXISTENT"));
    }
}
