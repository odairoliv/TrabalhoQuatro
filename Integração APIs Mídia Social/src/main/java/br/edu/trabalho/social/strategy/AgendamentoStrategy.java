package br.edu.trabalho.social.strategy;
import java.time.Instant;
public interface AgendamentoStrategy { void agendar(Runnable tarefa, Instant quando); }
