package co.com.companywf.utils;

import co.com.companywf.model.developer.Developer;
import co.com.companywf.model.gender.Gender;
import co.com.companywf.model.location.Location;
import co.com.companywf.model.status.Status;
import co.com.companywf.model.videogame.Videogame;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static Mono<Videogame> getVideogameFormater(Videogame videogame) {
        return Mono.zip(Parser.parseGenderEntity(videogame.getGender())
                                .map(Gender::getName),
                        Parser.parseStatusEntity(videogame.getStatus())
                                .map(Status::getDescription),
                        Parser.parseDeveloperEntity(videogame.getDeveloper())
                                .map(Developer::getName),
                        Parser.parseLocationEntity(videogame.getLocation())
                                .map(Location::getName))
                .map(tuple -> videogame.toBuilder()
                        .gender(tuple.getT1())
                        .status(tuple.getT2())
                        .developer(tuple.getT3())
                        .location(tuple.getT4())
                        .build());
    }

    private static Mono<Gender> parseGenderEntity(String input) {

        Pattern pattern = Pattern.compile(
                "genderId=([^,]+),\\s*" +
                        "name=([^,]+),\\s*" +
                        "createdAt=([^,\\)]+)"
        );

        Matcher matcher = pattern.matcher(input);

        return Mono.just(matcher)
                .filter(Matcher::find)
                .map(match -> Gender.builder()
                        .genderId(match.group(1))
                        .name(match.group(2))
                        .createdAt(LocalDateTime.parse(matcher.group(3)))
                        .build());
    }

    private static Mono<Status> parseStatusEntity(String input) {

        Pattern pattern = Pattern.compile(
                "statusId=([^,]+),\\s*" +
                        "description=([^,]+),\\s*" +
                        "createdAt=([^,\\)]+)"
        );

        Matcher matcher = pattern.matcher(input);

        return Mono.just(matcher)
                .filter(Matcher::find)
                .map(match -> Status.builder()
                        .statusId(match.group(1))
                        .description(match.group(2))
                        .createdAt(LocalDateTime.parse(matcher.group(3)))
                        .build());
    }

    private static Mono<Developer> parseDeveloperEntity(String input) {

        Pattern pattern = Pattern.compile(
                "developerId=([^,]+),\\s*" +
                        "name=([^,]+),\\s*" +
                        "createdAt=([^,\\)]+)"
        );

        Matcher matcher = pattern.matcher(input);

        return Mono.just(matcher)
                .filter(Matcher::find)
                .map(match -> Developer.builder()
                        .developerId(match.group(1))
                        .name(match.group(2))
                        .createdAt(LocalDateTime.parse(matcher.group(3)))
                        .build());
    }


    private static Mono<Location> parseLocationEntity(String input) {

        Pattern pattern = Pattern.compile(
                "locationId=([^,]+),\\s*" +
                        "name=([^,]+),\\s*" +
                        "createdAt=([^,\\)]+)"
        );

        Matcher matcher = pattern.matcher(input);

        return Mono.just(matcher)
                .filter(Matcher::find)
                .map(match -> Location.builder()
                        .locationId(match.group(1))
                        .name(match.group(2))
                        .createdAt(LocalDateTime.parse(matcher.group(3)))
                        .build());
    }

}

