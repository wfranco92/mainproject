package co.com.companywf.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Gender {
    AVENTURA("0a38ce6c-bf94-4d4c-b1b0-362b38862adf"),
    ESTRATEGIA("79b70ec5-d2d5-4244-aa55-9ec8e51247e0"),
    SHOTTER("8eaa8809-98be-4e62-a88c-a5470831f269"),
    SIMULACION("95d3574f-dd8f-498f-9d2b-a73655e4bf45"),
    CARRERAS("b2bf58bf-2d5c-4014-bf2a-bfa75a46a797"),
    ROL("c132db6c-447f-4c5f-b098-97efc5f95303"),
    SURVIVOR("f35ab215-0ee3-430f-8306-66ff573b6e31"),
    OTRO("6f57dd56-10de-4c01-be95-19261876e125"),
    PUZZLES("83ee0179-1cd1-4c5e-8cef-d60e6921d5d3"),
    SOULSLIKE("a4ad9389-b78c-4cf9-a196-32d9a474a900"),
    ACCION("8286bd45-8ebe-4fb0-b737-dd45e61d63b3");

    private final String id;

    public static String idFromName(String name){
        return Arrays.stream(values())
                .filter(status -> status.name().contentEquals(name))
                .map(Gender::getId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(name + " - GENDER_NOT_FOUND_OR_INEXISTENT"));
    }
}
