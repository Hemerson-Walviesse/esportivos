package com.palpites.esportivos.DTO;

import java.util.List;

public class DadosJogos {
    String get;
    Parameters parameters;

    public List<Response> response;

    class Parameters{
        String league;
        String season;
    }

    public class Response {
        public Fixture fixture;
        League league;
        public Teams teams;
        Goals goals;
        public Score score;

        public class Fixture {
            public int id;
            public String referee;
            String timezone;
            public String date;
            public Venue venue;
            public Status status;

            public class Venue {
                int id;
                public String name;
                public String city;
            }

            public class Status {
                public String longStatus;
                String shortStatus;
                int elapsed;
            }
        }

        class League {
            int id;
            String name;
            String country;
            String logo;
            String flag;
            int season;
            String round;
        }

        public class Teams {
            public Team home;
            public Team away;

            public class Team {
                int id;
                public String name;
                String logo;
                boolean winner;
            }
        }

        class Goals {
            Integer home;
            Integer away;
        }

        public class Score {
            Halftime halftime;
            public Fulltime fulltime;
            Extratime extratime;
            Penalty penalty;

            class Halftime {
                Integer home;
                Integer away;
            }

            public class Fulltime {
                public Integer home;
                public Integer away;
            }

            class Extratime {
                Integer home;
                Integer away;
            }

            class Penalty {
                Integer home;
                Integer away;
            }
        }
    }

}
