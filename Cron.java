import java.time.Duration;
import io.timeandspace.cronscheduler.CronScheduler;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.event.domain.Event;
import java.util.concurrent.TimeUnit;

public class Cron {
  public Cron(GatewayDiscordClient bot) {
    Duration syncPeriod = Duration.ofMinutes(5);
    CronScheduler cron = CronScheduler.create(syncPeriod);
    cron.scheduleAtFixedRateSkippingToLatest(0, 1, TimeUnit.MINUTES, runTimeMillis -> {
        System.out.println("ASD");
    }); 
  }
}