package br.edu.trabalho.social.strategy;
import java.time.Instant;
public class AgendamentoImediato implements AgendamentoStrategy { @Override public void agendar(Runnable tarefa, Instant quando){ tarefa.run(); } }
