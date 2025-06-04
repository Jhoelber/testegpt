package petShop.api.domain.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> listarServicos() {
        return servicoRepository.findAll();
    }

    public Servico criarServico(Servico servico) {
        return servicoRepository.save(servico);
    }

    public void deletarServico(Long id) {
        servicoRepository.deleteById(id);
    }


}
