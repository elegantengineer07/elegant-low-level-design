package systemsv2.PubSubSystem;

import java.util.HashSet;
import java.util.Set;

// Message Class
class Message {
    private String content;

    public Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

class Publisher {
    private Set<Topic> allowedTopics;

    public Publisher() {
        this.allowedTopics = new HashSet<>();
    }

    public void addTopic(Topic topic) {
        allowedTopics.add(topic);
    }

    public void publish(Topic topic, Message message) {
        if(!allowedTopics.contains(topic)) throw new IllegalArgumentException("Not allowed to publish to this topic");

        topic.publish(message);

    }
}

class Topic {
    private String name;
    private Set<Subscriber> subscribers;

    public Topic(String name) {
        this.name = name;
        this.subscribers = new HashSet<>();
    }

    // add/remove sub

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void publish(Message message) {
        for(Subscriber subcriber: subscribers) {
            subcriber.onMessage(message);
        }
    }
}

class Subscriber {
    private String name;
    public Subscriber(String name) {
        this.name = name;
    }

    public void onMessage(Message message) {
        System.out.println(name+" " +"got a message" +" "+ message.getContent());
    }
}

public class PubSubSystem {
    public static void main(String[] args) {
        Topic sportsTopic = new Topic("Sports");
        Topic techTopic = new Topic("Tech");

        Subscriber alice = new Subscriber("Messi");
        Subscriber bob = new Subscriber("Technical Guruji");

        sportsTopic.addSubscriber(alice);
        techTopic.addSubscriber(bob);

        Publisher publisher = new Publisher();
        publisher.addTopic(sportsTopic);
        publisher.addTopic(techTopic);

        publisher.publish(sportsTopic, new Message("Football match tonight!"));
        publisher.publish(techTopic, new Message("New smartphone released."));
    }
}
