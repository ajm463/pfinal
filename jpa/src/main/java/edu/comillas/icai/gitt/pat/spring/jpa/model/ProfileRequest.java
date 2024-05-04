package edu.comillas.icai.gitt.pat.spring.jpa.model;

public record ProfileRequest() {

}


/* public record ProfileRequest(String name, Role role, @Pattern(
    regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).{8,}$"
) String password) {
    public ProfileRequest(String name, Role role, @Pattern(
    regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).{8,}$"
) String password) {
        this.name = name;
        this.role = role;
        this.password = password;
    }

    public String name() {
        return this.name;
    }

    public Role role() {
        return this.role;
    }

    public @Pattern(
    regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).{8,}$"
) String password() {
        return this.password;
    }
}*/