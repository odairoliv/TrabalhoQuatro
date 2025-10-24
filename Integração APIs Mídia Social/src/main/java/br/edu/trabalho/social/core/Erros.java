package br.edu.trabalho.social.core;
public class Erros {
  public static abstract class SocialException extends RuntimeException {
    public final String codigo;
    public SocialException(String codigo, String msg, Throwable c){ super(msg, c); this.codigo=codigo; }
    public SocialException(String codigo, String msg){ this(codigo, msg, null); }
  }
  public static class AuthException extends SocialException { public AuthException(String m){ super("AUTH", m);} }
  public static class RateLimitException extends SocialException { public RateLimitException(String m){ super("RATE_LIMIT", m);} }
  public static class RedeIndisponivelException extends SocialException { public RedeIndisponivelException(String m){ super("NETWORK", m);} }
  public static class RequisicaoInvalidaException extends SocialException { public RequisicaoInvalidaException(String m){ super("BAD_REQUEST", m);} }
}
