package br.edu.trabalho.social.adapter;
import br.edu.trabalho.social.core.*;
public interface SocialAdapter<TPayload> { Publicacao publicar(Conteudo<TPayload> c); Estatisticas estatisticas(String id); boolean deletar(String id); }
