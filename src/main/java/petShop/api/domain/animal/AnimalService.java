package petShop.api.domain.animal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public void salvarAnimal(DadosCadastroAnimal dados) {
        if (existeAnimalComMesmoNomeESpecieECliente(dados.nome(), dados.especie(), dados.clientesId())) {
            throw new RuntimeException("Animal já cadastrado");
        }
        // Salvar animal
    }

    public boolean existeAnimalComMesmoNomeESpecieECliente(String nome, String especie, Long clienteId) {
        return animalRepository.findByNomeAndEspecieAndClienteId(nome, especie, clienteId).isPresent();
    }
}
