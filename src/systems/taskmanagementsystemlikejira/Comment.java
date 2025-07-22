package systems.taskmanagementsystemlikejira;

public class Comment {
    private String id;
    private String userId;
    private String taskId;
    private String content;
    // Things like timestamps, audit;

    public Comment(String userId, String taskId, String content) {
        this.id = "randomId";
        this.userId = userId;
        this.taskId = taskId;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getContent() {
        return content;
    }
}
