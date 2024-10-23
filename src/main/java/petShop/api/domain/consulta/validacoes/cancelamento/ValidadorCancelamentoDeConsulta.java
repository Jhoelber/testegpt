package petShop.api.domain.consulta.validacoes.cancelamento;

import petShop.api.domain.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {

    void validar(DadosCancelamentoConsulta dados);

}
