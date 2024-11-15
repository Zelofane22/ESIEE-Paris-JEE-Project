package techsupport.final_project.beans;

public enum StatutRequete {
    NOUVELLE,
    EN_COURS,
    TERMINEE;

    @Override
    public String toString() {
        switch (this) {
            case NOUVELLE:
                return "Nouvelle";
            case EN_COURS:
                return "En cours";
            case TERMINEE:
                return "Terminée";
            default:
                throw new IllegalArgumentException();
        }
    }
}
