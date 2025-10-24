package br.edu.trabalho.social.strategy;
import br.edu.trabalho.social.infra.SchedulerSeguro; import java.time.Duration; import java.time.Instant;
public class AgendamentoAtrasoFixo implements AgendamentoStrategy {
  private final SchedulerSeguro scheduler; private final Duration atrasoMinimo;
  public AgendamentoAtrasoFixo(SchedulerSeguro scheduler, Duration atrasoMinimo){ this.scheduler=scheduler; this.atrasoMinimo=atrasoMinimo; }
  @Override public void agendar(Runnable tarefa, Instant quando){
    var delay = Math.max(0, quando.toEpochMilli() - Instant.now().toEpochMilli());
    scheduler.agendar(tarefa, Duration.ofMillis(delay).plus(atrasoMinimo));
  }
}
