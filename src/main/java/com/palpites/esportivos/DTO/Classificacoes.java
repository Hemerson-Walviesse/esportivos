package com.palpites.esportivos.DTO;

import java.util.List;

public class Classificacoes {
    public String get;
    public Parameters parameters;
    public List<Response> response;

    public static class Parameters {
        public String league;

    }

    public static class Response {
        public League league;
    }

    public static class League {
        public int id;
        public String name;
        public String country;
        public String logo;
        public String flag;
        public int season;
        public List<List<Standing>> standings; // Lista de listas para corresponder à estrutura do JSON
    }

    public static class Standing {
        public int rank;
        public Team team;
        public int points;
        public int goalsDiff;
        public String group;
        public String form;
        public String status;
        public String description;
        public Stats all;
        public Stats home;
        public Stats away;
        public String update;
    }

    public static class Team {
        public int id;
        public String name;
        public String logo;
    }

    public static class Stats {
        public int played;
        public int win;
        public int draw;
        public int lose;
        public Goals goals;
    }

    public static class Goals {
        public int goalsFor;
        public int goalsAgainst;
    }
}
