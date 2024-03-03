
import com.fasterxml.jackson.annotation.JsonProperty;

public class FactsCats {
    private final String id;
    private final String text;
    private final String type;
    private final String user;
    private final String upvotes;

    /*
    id - уникальный идентификатор записи
text - сообщение
type - тип животного
user - имя пользователя
upvotes - голоса
     */
    public FactsCats(
            @JsonProperty("id") String id,
            @JsonProperty("text") String text,
            @JsonProperty("type") String type,
            @JsonProperty("user") String user,
            @JsonProperty("upvotes") String upvotes
    ) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public String getUser() {
        return user;
    }

    public String getUpvotes() {
        return upvotes;
    }

    @Override
    public String toString() {
        return "FactsCats" +
                "\n id=" + id +
                "\n text=" + text +
                "\n type=" + type +
                "\n user=" + user +
                "\n upvotes=" + upvotes;
    }
}

