package br.edu.trabalho.social.adapter;
import br.edu.trabalho.social.core.*; import br.edu.trabalho.social.adapter.client.TwitterClient; import java.time.Instant; import java.util.Map;
public class TwitterAdapter implements SocialAdapter<Map<String,Object>> {
  private final TwitterClient client; private final String token;
  public TwitterAdapter(TwitterClient client, String token){ this.client=client; this.token=token; }
  @Override public Publicacao publicar(Conteudo<Map<String,Object>> conteudo){
    var mediaId = (String) conteudo.payloadEspecifico().getOrDefault("mediaId", null);
    var tweet = client.post(conteudo.texto(), mediaId, token);
    return new Publicacao(tweet.id(), Plataforma.TWITTER, tweet.link(), Instant.now());
  }
  @Override public Estatisticas estatisticas(String id){ return new Estatisticas(client.likes(id), client.retweets(id), 0, 0); }
  @Override public boolean deletar(String id){ return client.delete(id, token); }
}
