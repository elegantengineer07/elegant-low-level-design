package systems.taskmanagementsystemlikejira;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

enum TaskStatus {
    TODO,
    IN_PROGRESS,
    DONE
}

public class Task {
    private String id;
    private String title;
    private String description;
    private TaskStatus status;
    private List<Comment> commentList;
    private String assigneeId;
    private Date createdAt;
    private Date dueAt;


    public Task(String title) {
        this.id = "random-id";
        this.title = title;
        this.status = TaskStatus.TODO;
        this.commentList = new ArrayList<>();
        this.createdAt = new Date();
    }

    public String getId() {
        return id;
    }

    public synchronized void updateStatus(TaskStatus status) {
        this.status = status;
    }

    public synchronized void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public synchronized void addComment(Comment comment) {
        this.commentList.add(comment); // can be only comment id;
    }

}
