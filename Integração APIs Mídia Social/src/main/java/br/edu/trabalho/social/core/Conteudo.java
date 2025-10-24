package br.edu.trabalho.social.core;
import java.util.Map;
public record Conteudo<T>(String texto, T payloadEspecifico, Map<String, Object> metadados) { }
