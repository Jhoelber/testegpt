package petShop.api.domain.veterinario;

import petShop.api.domain.cliente.Cliente;
import petShop.api.domain.consulta.Consulta;
import petShop.api.domain.endereco.DadosEndereco;
import petShop.api.domain.animal.DadosCadastroAnimal;
import petShop.api.domain.animal.Animal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class VeterinarioRepositoryTest {

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando unico veterinario cadastrado nao esta disponivel na data")
    void escolherVeterinarioAleatorioLivreNaDataCenario1() {
        //given ou arrange
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var veterinario = cadastrarVeterinario(
                "Veterinario", "veterinario@hotmail.com",
                "123456", Especialidade.CARDIOLOGIA);
        var animal = cadastrarAnimal("Animal", "animal@email.com",
                "00000000000", "masculino",
                "2024-10-10", "marrom",
                "bravo ao lavar", "pinscher", "12kg",
                new Cliente());
        cadastrarConsulta(veterinario, animal, proximaSegundaAs10);


        var veterinarioLivre = veterinarioRepository.escolherVeterinarioAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);


        assertThat(veterinarioLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver veterinario quando ele estiver disponivel na data")
    void escolherVeterinarioAleatorioLivreNaDataCenario2() {
        //given ou arrange
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var veterinario = cadastrarVeterinario(
                "Veterinario", "veterinario@voll.med",
                "123456", Especialidade.CARDIOLOGIA);


        var veterinarioLivre = veterinarioRepository.escolherVeterinarioAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);


        assertThat(veterinarioLivre).isEqualTo(veterinario);
    }

    private void cadastrarConsulta(Veterinario veterinario, Animal animal, LocalDateTime data) {
        em.persist(new Consulta(null, veterinario, animal, data, null));
    }

    private Veterinario cadastrarVeterinario(String nome, String email, String crm, Especialidade especialidade) {
        var veterinario = new Veterinario(dadosVeterinario(nome, email, crm, especialidade));
        em.persist(veterinario);
        return veterinario;
    }

    private Animal cadastrarAnimal(
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
        var animal = new Animal(dadosAnimal(nome,
                especie,
                vacina,
                sexo,
                dataNascimento,
                cor,
                descricao,
                raca,
                peso,
                cliente));
        em.persist(animal);
        return animal;
    }

    private DadosCadastroVeterinario dadosVeterinario(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroVeterinario(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroAnimal dadosAnimal(String nome,
                                            String especie,
                                            String vacina,
                                            String sexo,
                                            String dataNascimento,
                                            String cor,
                                            String descricao,
                                            String raca,
                                            String peso,
                                            Cliente cliente) {
        return new DadosCadastroAnimal(
                nome,
                especie,
                vacina,
                sexo,
                dataNascimento,
                cor,
                descricao,
                raca,
                peso,
               cliente


        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }


}