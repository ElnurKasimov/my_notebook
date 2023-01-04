package group_project.MyNotebook.note;

import lombok.Getter;

@Getter
public enum Access {
    PRIVATE("Приватна"),
    PUBLIC("Публічна");
    public final String label;

    Access(String label) {
        this.label = label;
    }
}
