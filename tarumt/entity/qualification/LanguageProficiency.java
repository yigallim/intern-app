package com.tarumt.entity.qualification;

public class LanguageProficiency extends Qualification {

    private Language language;
    private Proficiency proficiency; // Elementary, Intermediate, Fluent, Native

    public enum Language {
        EN("English"),
        CH("Chinese"),
        MY("Malay"),
        TM("Tamil"),
        JP("Japanese"),
        KR("Korean"),
        FR("French"),
        DE("German"),
        ES("Spanish"),
        IT("Italian"),
        RU("Russian"),
        PT("Portuguese"),
        AR("Arabic"),
        HI("Hindi"),
        TH("Thai"),
        VN("Vietnamese"),
        ID("Indonesian"),
        NL("Dutch"),
        SW("Swahili"),
        TR("Turkish"),
        HE("Hebrew"),
        FA("Persian"),
        UR("Urdu"),
        BN("Bengali"),
        PL("Polish"),
        GR("Greek");

        private final String displayName;

        Language(String displayName
        ) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    public enum Proficiency {
        ELEMENTARY,
        INTERMEDIATE,
        FLUENT,
        NATIVE;

    }

    public LanguageProficiency(Language language, Proficiency proficiency,boolean optional, Importance importance) {
        this.language = language;
        this.proficiency = proficiency;
        setOptional(optional);
        setImportance(importance);
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Proficiency getProficiency() {
        return proficiency;
    }

    public void setProficiency(Proficiency proficiency) {
        this.proficiency = proficiency;
    }

    @Override
    public double score() {
        switch (proficiency.toString().toLowerCase()) {
            case "native":
                return 1.0;
            case "fluent":
                return 0.8;
            case "intermediate":
                return 0.5;
            case "elementary":
                return 0.2;
            default:
                return 0.0;
        }
    }

    @Override
    public String toString() {
        return "LanguageProficiency{"
                + "language='" + language + '\''
                + ", proficiency='" + proficiency + '\''
                + '}';
    }
}
