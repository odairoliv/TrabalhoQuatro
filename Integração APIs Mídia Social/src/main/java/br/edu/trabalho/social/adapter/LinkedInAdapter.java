package br.edu.trabalho.social.adapter;
import br.edu.trabalho.social.core.*; import br.edu.trabalho.social.adapter.client.LinkedInClient; import java.time.Instant;
public class LinkedInAdapter implements SocialAdapter<String> {
  private final LinkedInClient client; private final String token; private final String orgId;
  public LinkedInAdapter(LinkedInClient client, String token, String orgId){ this.client=client; this.token=token; this.orgId=orgId; }
  @Override public Publicacao publicar(Conteudo<String> conteudo){
    var share = client.shareText(conteudo.texto(), orgId, token);
    return new Publicacao(share.urn, Plataforma.LINKEDIN, share.url, Instant.now());
  }
  @Override public Estatisticas estatisticas(String urn){ return new Estatisticas(client.reactions(urn), client.shares(urn), client.comments(urn), 0); }
  @Override public boolean deletar(String urn){ return client.deleteShare(urn); }
}
