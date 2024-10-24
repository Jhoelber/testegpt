package petShop.api.domain.animal;

import petShop.api.domain.cliente.Cliente;

public record DadosListagemAnimal(Long id,
                                  String nome,
                                  String especie,
                                  String vacina,
                                  String sexo,
                                  String dataNascimento,
                                  String cor,
                                  String descricao,
                                  String raca,
                                  String peso,
                                  Cliente cliente) {

    public DadosListagemAnimal(Animal animal) {
        this(  animal.getId(), animal.getNome(),
                animal.getEspecie(), animal.getVacina(),
                animal.getSexo(), animal.getDataNascimento(),
                animal.getCor(), animal.getDescricao(),
                animal.getRaca(), animal.getPeso(),
                animal.getCliente());
    }

}
