package at.nullpointer;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

public class Main {
    public static void main(String[] args) {
        TwitchClient twitchClient = TwitchClientBuilder.builder()
                .withEnableChat(true)
                .withEnableHelix(true)
                .build();
        twitchClient.getChat().joinChannel("edopeh");

        twitchClient.getEventManager().onEvent(ChannelMessageEvent.class, event -> {
            System.out.println("[" + event.getChannel().getName() + "] " + event.getUser().getName() + ": " + event.getMessage());
            int subscriptionTier = event.getSubscriptionTier();
            if (subscriptionTier > 0) System.out.println("Darf: " + subscriptionTier);
        });
    }
}