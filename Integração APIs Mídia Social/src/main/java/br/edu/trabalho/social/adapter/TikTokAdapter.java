package br.edu.trabalho.social.adapter;
import br.edu.trabalho.social.core.*; import br.edu.trabalho.social.adapter.client.TikTokClient; import java.time.Instant;
public class TikTokAdapter implements SocialAdapter<byte[]> {
  private final TikTokClient client; private final String token;
  public TikTokAdapter(TikTokClient client, String token){ this.client=client; this.token=token; }
  @Override public Publicacao publicar(Conteudo<byte[]> conteudo){
    var video = client.upload(conteudo.texto(), conteudo.payloadEspecifico(), token);
    return new Publicacao(video.vid, Plataforma.TIKTOK, video.url, Instant.now());
  }
  @Override public Estatisticas estatisticas(String vid){ return new Estatisticas(client.likes(vid), 0, client.comments(vid), client.views(vid)); }
  @Override public boolean deletar(String vid){ return client.removeVideo(vid); }
}
