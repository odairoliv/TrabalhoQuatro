package br.edu.trabalho.social.core;
import br.edu.trabalho.social.adapter.SocialAdapter;
import br.edu.trabalho.social.factory.SocialAdapterFactory;
import br.edu.trabalho.social.infra.Logger;
import br.edu.trabalho.social.strategy.AgendamentoStrategy;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import static br.edu.trabalho.social.core.Erros.*;
public class GerenciadorMidiaSocial {
  private final SocialAdapterFactory factory;
  private final Map<String, Publicacao> publicacoes = new ConcurrentHashMap<>();
  public GerenciadorMidiaSocial(SocialAdapterFactory factory){ this.factory = factory; }
  public <T> Resposta<Publicacao> publicarAgora(Plataforma p, Conteudo<T> conteudo){
    try{
      Objects.requireNonNull(conteudo);
      @SuppressWarnings("unchecked") SocialAdapter<T> adapter = (SocialAdapter<T>) factory.criar(p);
      var pub = adapter.publicar(conteudo);
      publicacoes.put(pub.id(), pub);
      return Resposta.sucesso(pub);
    } catch (SocialException se){ Logger.error("Erro social", se); return Resposta.erro(se.codigo, se.getMessage()); }
      catch (Exception e){ Logger.error("Falha inesperada", e); return Resposta.erro("INTERNAL", e.getMessage()); }
  }
  public <T> Resposta<Publicacao> agendar(Plataforma p, Conteudo<T> conteudo, Instant quando, AgendamentoStrategy strategy){
    try{
      strategy.agendar(() -> publicarAgora(p, conteudo), quando);
      return Resposta.sucesso(null);
    } catch (SocialException se){ Logger.error("Erro no agendamento", se); return Resposta.erro(se.codigo, se.getMessage()); }
      catch (Exception e){ Logger.error("Falha inesperada no agendamento", e); return Resposta.erro("INTERNAL", e.getMessage()); }
  }
  public Resposta<Estatisticas> obterEstatisticas(Plataforma p, String id){
    try{
      @SuppressWarnings("unchecked") SocialAdapter<Object> adapter = (SocialAdapter<Object>) factory.criar(p);
      return Resposta.sucesso(adapter.estatisticas(id));
    } catch (SocialException se){ return Resposta.erro(se.codigo, se.getMessage()); }
      catch (Exception e){ return Resposta.erro("INTERNAL", e.getMessage()); }
  }
  public Resposta<Boolean> deletar(Plataforma p, String id){
    try{
      @SuppressWarnings("unchecked") SocialAdapter<Object> adapter = (SocialAdapter<Object>) factory.criar(p);
      boolean ok = adapter.deletar(id);
      if (ok) publicacoes.remove(id);
      return Resposta.sucesso(ok);
    } catch (SocialException se){ return Resposta.erro(se.codigo, se.getMessage()); }
      catch (Exception e){ return Resposta.erro("INTERNAL", e.getMessage()); }
  }
}
