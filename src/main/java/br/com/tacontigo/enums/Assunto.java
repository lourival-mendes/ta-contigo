package br.com.tacontigo.enums;

public enum Assunto {
    PSM_I, PSM_II, PSM_III, PSPO_I, PSPO_II, PSPO_III, PSK_I, PSK_II, PSK_III, PSMF_I, PSMF_II, PSMF_III, PSMT_I,
    PSMT_II, PSMT_III, PSPOC_I, PSPOC_II, PSPOC_III, PSKOC_I, PSKOC_II, PSKOC_III, PSMFOC_I, PSMFOC_II, PSMFOC_III,
    PSMTOC_I, PSMTOC_II, PSMTOC_III;

    public static Assunto fromString(String assunto) {
        return Assunto.valueOf(assunto.toUpperCase());
    }

    public static String toString(Assunto assunto) {
        return assunto.name();
    }
}
