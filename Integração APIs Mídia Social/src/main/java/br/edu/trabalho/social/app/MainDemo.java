package br.edu.trabalho.social.app;
import br.edu.trabalho.social.core.*; import br.edu.trabalho.social.factory.*; import br.edu.trabalho.social.infra.SchedulerSeguro; import br.edu.trabalho.social.strategy.*;
import java.time.*; import java.util.Map;
public class MainDemo {
  public static void main(String[] args) {
    var factory = new SocialAdapterFactory(new SocialConfig());
    var ger = new GerenciadorMidiaSocial(factory);
    var conteudoTw = new Conteudo<>("Olá, mundo do Adapter!", Map.of("mediaId","img-123"), Map.of("campanha","BlackFriday"));
    var r1 = ger.publicarAgora(Plataforma.TWITTER, conteudoTw);
    System.out.println("Twitter -> ok="+r1.ok()+" dado="+r1.dado());
    try (var scheduler = new SchedulerSeguro(2)){
      var strategy = new AgendamentoAtrasoFixo(scheduler, Duration.ofMillis(0));
      var conteudoIg = new Conteudo<>("Legenda do post no IG", new byte[]{1,2,3}, Map.of());
      var r2 = ger.agendar(Plataforma.INSTAGRAM, conteudoIg, Instant.now().plusSeconds(2), strategy);
      System.out.println("Agendamento IG aceito -> ok="+r2.ok());
      Thread.sleep(2500);
    } catch (InterruptedException e){ Thread.currentThread().interrupt(); }
    r1.dado().ifPresent(pub -> {
      var est = ger.obterEstatisticas(Plataforma.TWITTER, pub.id());
      System.out.println("Stats TW -> "+est.dado());
    });
  }
}
