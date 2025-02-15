package co.com.companywf.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Status {
    PLAYED("6d0204ce-97d2-4083-8ff8-391dbab0bd4b"),
    DOWNLOADED("bfa2f301-4e8e-4ba9-9768-23f94df92292"),
    INPROGRESS("ee0e8447-9210-453d-8a04-18ee75846b61");

    private final String id;

    public static String idFromName(String name){
        return Arrays.stream(values())
                .filter(status -> status.name().contentEquals(name))
                .map(Status::getId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("STATUS_NOT_FOUND_OR_INEXISTENT"));
    }
}
