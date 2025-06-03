package petShop.api.domain.agenda;

public record AgendaAnimalDTO(
        Long id,
        String data,
        String hora,
        String servico,
        String status
) {
    public AgendaAnimalDTO(Agenda agenda) {
        this(
                agenda.getId(),
                agenda.getData().toLocalDate().toString(),
                agenda.getData().toLocalTime().toString(),
                agenda.getServico().getNome(),
                agenda.getStatus()
        );
    }
}


