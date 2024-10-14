package com.talentica.services;

import java.time.Duration;
import org.springframework.modulith.events.IncompleteEventPublications;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ApplicationEventsManager {

  private final IncompleteEventPublications incompleteEventPublications;

  public ApplicationEventsManager(IncompleteEventPublications incompleteEventPublications) {
    this.incompleteEventPublications = incompleteEventPublications;
  }

  @Scheduled(fixedDelay = 60_000)
  public void retryFailedEvents() {
    incompleteEventPublications.resubmitIncompletePublicationsOlderThan(Duration.ofSeconds(30));
  }
}
