import java.time.Duration;

import discord4j.common.util.Snowflake;
import io.timeandspace.cronscheduler.CronScheduler;
import discord4j.core.GatewayDiscordClient;
import java.util.concurrent.TimeUnit;

public class Cron {
  public Cron(GatewayDiscordClient bot, CanvasAPI api) {
    Snowflake id = Snowflake.of("820142600899133510"); // The channel's ID
    Duration syncPeriod = Duration.ofMinutes(5);
    CronScheduler cron = CronScheduler.create(syncPeriod);
    cron.scheduleAtFixedRateSkippingToLatest(0, 1, TimeUnit.MINUTES, runTimeMillis -> {
      // will call the API here
      api.getAssigments();
      //bot.getChannelById(id).block().getRestChannel().createMessage("tester").block();
    }); 
  }
}
