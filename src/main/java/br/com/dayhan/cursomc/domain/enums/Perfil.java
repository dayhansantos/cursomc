package br.com.dayhan.cursomc.domain.enums;

/**
 * Perfil de usuario
 */
public enum Perfil {

    ADMIN(1, "ROLE_ADMIN"), 
    CLIENTE(2, "ROLE_CLIENTE");

    private int cod;
    private String descricao;

    Perfil(final int cod, final String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCod() {
        return cod;
    }

    public static Perfil toEnum(final Integer cod) {
        if (cod == null) {
            return null;
        }

        for (final Perfil e : Perfil.values()) {
            if (cod.equals(e.getCod())) {
                return e;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }

}