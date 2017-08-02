package stud.mi.dachatserver.dto;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Message
{
    // public Message(final String user, final String message)
    // {
    // this.user = user;
    // this.content = message;
    // final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    // final Date now = Calendar.getInstance().getTime();
    // this.datePosted = df.format(now);
    // this._id = Integer.toString(message.hashCode());
    // }

    private static final JsonParser JSON_PARSER = new JsonParser();
    private static final String REV = "_rev";
    private static final String ID = "_id";

    private JsonObject jsonData;

    public Message(final String json)
    {
        this.jsonData = JSON_PARSER.parse(json).getAsJsonObject();
        this.addMissingFields();
    }

    public Message(final JsonObject json)
    {
        this.jsonData = json;
        this.addMissingFields();
    }

    private void addMissingFields()
    {
        if (this.jsonData.get(REV) == null)
        {
            this.jsonData.addProperty(REV, "");
        }
        if (this.jsonData.get(ID) == null)
        {
            this.jsonData.addProperty(ID, "0");
        }
    }

    public void setID(final String newId)
    {
        this.jsonData.addProperty(ID, newId);
    }

    public int getVersion()
    {
        return this.jsonData.get("version").getAsInt();
    }

    public String getType()
    {
        return this.jsonData.get("type").getAsString();
    }

    public Long getUserID()
    {
        return this.getContent().get("userID").getAsLong();
    }

    public String getUserName()
    {
        return this.getContent().get("userName").getAsString();
    }

    public String getMessage()
    {
        return this.getContent().get("message").getAsString();
    }

    public String getChannelName()
    {
        return this.getContent().get("channelName").getAsString();
    }

    public JsonObject getContent()
    {
        return this.jsonData.get("content").getAsJsonObject();
    }

    @Override
    public String toString()
    {
        return "";
    }

    public String toJson()
    {
        return this.jsonData.toString();
    }
}
