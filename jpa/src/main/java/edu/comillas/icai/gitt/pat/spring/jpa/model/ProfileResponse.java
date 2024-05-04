package edu.comillas.icai.gitt.pat.spring.jpa.model;

import javax.management.relation.Role;

public record ProfileResponse(String name, String email, Role role) {
        public ProfileResponse(String name, String email, Role role) {
            this.name = name;
            this.email = email;
            this.role = role;
        }

        public String name() {
            return this.name;
        }

        public String email() {
            return this.email;
        }

        public Role role() {
            return this.role;
        }
    }


