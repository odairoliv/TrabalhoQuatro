package br.edu.trabalho.social.core;
import java.util.Optional;
public final class Resposta<T> {
  private final boolean ok; private final T dado; private final String erroCodigo; private final String erroMensagem;
  private Resposta(boolean ok, T dado, String erroCodigo, String erroMensagem){ this.ok=ok; this.dado=dado; this.erroCodigo=erroCodigo; this.erroMensagem=erroMensagem; }
  public static <T> Resposta<T> sucesso(T dado){ return new Resposta<>(true, dado, null, null); }
  public static <T> Resposta<T> erro(String codigo, String mensagem){ return new Resposta<>(false, null, codigo, mensagem); }
  public boolean ok(){ return ok; }
  public java.util.Optional<T> dado(){ return java.util.Optional.ofNullable(dado); }
  public java.util.Optional<String> erroCodigo(){ return java.util.Optional.ofNullable(erroCodigo); }
  public java.util.Optional<String> erroMensagem(){ return java.util.Optional.ofNullable(erroMensagem); }
}
