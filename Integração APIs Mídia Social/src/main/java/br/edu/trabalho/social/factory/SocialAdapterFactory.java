package br.edu.trabalho.social.factory;
import br.edu.trabalho.social.adapter.*; import br.edu.trabalho.social.adapter.client.*; import br.edu.trabalho.social.core.Plataforma; import br.edu.trabalho.social.core.Erros.AuthException;
public class SocialAdapterFactory {
  private final SocialConfig cfg;
  public SocialAdapterFactory(SocialConfig cfg){ this.cfg = cfg; }
  public SocialAdapter<?> criar(Plataforma p){
    return switch (p){
      case TWITTER -> new TwitterAdapter(new TwitterClient(), cfg.twitterToken().orElseThrow(() -> new AuthException("TWITTER_TOKEN ausente")));
      case INSTAGRAM -> new InstagramAdapter(new InstagramClient(), cfg.instagramToken().orElseThrow(() -> new AuthException("INSTAGRAM_TOKEN ausente")));
      case LINKEDIN -> new LinkedInAdapter(new LinkedInClient(), cfg.linkedinToken().orElseThrow(() -> new AuthException("LINKEDIN_TOKEN ausente")), cfg.linkedinOrgId().orElse("org-123"));
      case TIKTOK -> new TikTokAdapter(new TikTokClient(), cfg.tiktokToken().orElseThrow(() -> new AuthException("TIKTOK_TOKEN ausente")));
    };
  }
}
