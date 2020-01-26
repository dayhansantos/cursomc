package br.com.dayhan.cursomc.domain.enums;

/**
 * EstadoPagamento
 */
public enum EstadoPagamento {

    PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

    private int cod;
    private String descricao;

    private EstadoPagamento(final int cod, final String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCod() {
        return cod;
    }

    public static EstadoPagamento toEnum(final Integer cod) {
        if (cod == null) {
            return null;
        }

        for (final EstadoPagamento e : EstadoPagamento.values()) {
            if (cod.equals(e.getCod())) {
                return e;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }

}