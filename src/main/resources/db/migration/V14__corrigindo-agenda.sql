ALTER TABLE agendas ADD COLUMN veterinario_id BIGINT;
ALTER TABLE agendas ADD CONSTRAINT fk_agenda_veterinario FOREIGN KEY (veterinario_id) REFERENCES veterinarios(id);
