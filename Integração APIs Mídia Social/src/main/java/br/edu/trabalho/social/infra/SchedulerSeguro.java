package br.edu.trabalho.social.infra;
import java.time.Duration; import java.util.Objects; import java.util.concurrent.ScheduledThreadPoolExecutor; import java.util.concurrent.TimeUnit;
public class SchedulerSeguro implements AutoCloseable {
  private final ScheduledThreadPoolExecutor exec;
  public SchedulerSeguro(int pool){ this.exec = new ScheduledThreadPoolExecutor(pool); this.exec.setRemoveOnCancelPolicy(true); }
  public void agendar(Runnable r, Duration d){ Objects.requireNonNull(r); Objects.requireNonNull(d); exec.schedule(r, d.toMillis(), TimeUnit.MILLISECONDS); }
  @Override public void close(){ exec.shutdownNow(); }
}
