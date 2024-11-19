package org.kr.co.sports_result.soccer.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class PremierResult {
    @JsonProperty("scores")
    private List<Score> scores;

    @Data
    @NoArgsConstructor
    public static class Score {
        List<League> leagues;
        List<Event> events;
    }

    @Data
    @NoArgsConstructor
    public static class League {
        private String id;
        private String uid;
        private String name;
        private String abbreviation;
        private String midsizeName;
        private String slug;
        private Season season;
        private String calendarType;
        private List<String> calendar;
    }

    @Data
    @NoArgsConstructor
    public static class Season {
        private String year;
        private String slug;
    }

    @Data
    @NoArgsConstructor
    public static class Event {
        private String id;
        private String uid;
        private String date;
        private String name;
        private String shortName;
        private Season season;
        @JsonProperty("competitions")
        private List<Competition> competitions;
        private Status status;
        private Venue venue;

        @Data
        @NoArgsConstructor
        public static class Competition {
            //private String id;
            //private String uid;
            private String date;
            private String startDate;
            //private int attendance;
            private boolean timeValid;
            private boolean recent;
            private Status status;
            private Venue venue;
            private List<Competitor> competitors;
        }

        @Data
        @NoArgsConstructor
        public static class Status {
            private int clock;
            private String displayClock;
            private int period;
            //private StatusType type;

            @Data
            @NoArgsConstructor
            public static class StatusType {
                //private int id;
                //private String name;
                //private String state;
                //private boolean completed;
                //private String description;
                //private String shortDetail;
            }
        }

        @Data
        @NoArgsConstructor
        public static class Venue {
            private String id;
            private String fullName;
            private Address address;

            @Data
            @NoArgsConstructor
            public static class Address {
                private String city;
                private String country;
            }
        }

        @Data
        @NoArgsConstructor
        public static class Competitor {
            private String id;
            //private String uid;
            private String type;
            private int order;
            private String homeAway;
            private boolean winner;
            //private String form;
            private String score;
            //private List<Record> records;
            private Team team;

            @Data
            @NoArgsConstructor
            public static class Record {
                //private String name;
                //private String type;
                //private String summary;
                //private String abbreviation;
            }

            @Data
            @NoArgsConstructor
            public static class Team {
                private String id;
                //private String uid;
                //private String abbreviation;
                private String displayName;
                //private String shortDisplayName;
                //private String name;
                //private String location;
                //private String color;
                //private String alternateColor;
                //private boolean isActive;
                //private String logo;
            }
        }
    }
}
