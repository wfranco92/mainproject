package co.com.companywf.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageUtilsEnum {

    EMPTY(""),
    NOT_VALIDATE_BODY_DATA("los campos del body request no cumplen con la validacion minima.");

    private final String message;
}
