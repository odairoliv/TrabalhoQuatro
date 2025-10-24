package br.edu.trabalho.social.core;
import java.time.Instant;
public record Publicacao(String id, Plataforma plataforma, String url, Instant publicadoEm) { }
