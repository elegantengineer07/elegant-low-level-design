package systems.solidprinciples;

/*
I - Interface Segregation Principle (ISP):
Definition: Clients should not be forced to depend on interfaces they do not use.
Explanation: Larger interfaces should be split into smaller, more specific ones so that classes implement only the methods they need.
Example: Instead of having one large Animal interface with methods like walk(), fly(), and swim(), you could have separate interfaces like Walkable, Flyable, and Swimmable, so animals only implement the methods relevant to them.
 */

// Violates ISP
interface MediaPlayer {
    void playAudio(String audiofile);
    void playVideo(String videofile);
}

// So using above, whether it is audioplayer or videoplayer, we need to implement

// Follows ISP
interface AudioPlayer {
    void playAudio(String audiofile);
}

interface VideoPlayer {
    void playVideo(String videofile);
}

class SimpleMediaPlayer implements AudioPlayer {

    @Override
    public void playAudio(String audiofile) {

    }
}
class AdvancedMediaPlayer implements AudioPlayer, VideoPlayer {

    @Override
    public void playAudio(String audiofile) {

    }

    @Override
    public void playVideo(String videofile) {

    }
}

public class ISP {
}
