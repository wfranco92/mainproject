package co.com.companywf.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Location {
    TOSHIBA_INTERNAL_2TB("0d348c86-a6f3-4488-aeb8-6bb17547a073"),
    SAMSUNG_1TB("11c84757-6e28-47f4-88cf-ea130828da87"),
    WESTERNDIGITAL("41f725df-d465-4c92-b2a4-862541b62cd9"),
    STEAM("1f379700-f218-408f-9f11-c1ed9be34bb0"),
    OTRO("56bb9937-e810-4ac8-b625-fe508ef4ecdf"),
    TOSHIBA_EXTERNAL_4TB("731f10a9-d370-4706-9212-dc1d457d2329"),
    TOSHIBA_EXTERNAL_2TB("816f63f3-43ef-4985-ac3d-e94e889cd35b"),
    HGST_750GB("9c97583d-2757-4510-b3c4-300a70ade172"),
    HGST_500GB("cf95b633-944d-48c6-ab7c-59551a705938"),
    SANDISK_EXTERNAL_1TB("bf4f58e2-29e4-4c20-b08f-6c289398efe7");

    private final String id;

    public static String idFromName(String name){
        return Arrays.stream(values())
                .filter(status -> status.name().contentEquals(name))
                .map(Location::getId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(name + " - LOCATION_NOT_FOUND_OR_INEXISTENT"));
    }
}
