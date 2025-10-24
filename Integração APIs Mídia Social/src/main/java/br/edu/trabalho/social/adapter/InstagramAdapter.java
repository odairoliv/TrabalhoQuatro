package br.edu.trabalho.social.adapter;
import br.edu.trabalho.social.core.*; import br.edu.trabalho.social.adapter.client.InstagramClient; import java.time.Instant;
public class InstagramAdapter implements SocialAdapter<byte[]> {
  private final InstagramClient client; private final String token;
  public InstagramAdapter(InstagramClient client, String token){ this.client=client; this.token=token; }
  @Override public Publicacao publicar(Conteudo<byte[]> conteudo){
    var post = client.create(conteudo.texto(), conteudo.payloadEspecifico(), token);
    return new Publicacao(post.code, Plataforma.INSTAGRAM, post.permalink, Instant.now());
  }
  @Override public Estatisticas estatisticas(String code){ return new Estatisticas(client.hearts(code), 0, client.comments(code), 0); }
  @Override public boolean deletar(String code){ return client.remove(code); }
}
