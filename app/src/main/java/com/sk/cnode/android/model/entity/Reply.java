package com.sk.cnode.android.model.entity;

import com.google.gson.annotations.SerializedName;
import com.sk.cnode.android.utils.MarkdownUtils;

import org.joda.time.DateTime;

import java.util.List;

public class Reply {

    private String id;

    private Author author;

    private String content;

    private List<String> ups;

    @SerializedName("create_at")
    private DateTime createAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getUps() {
        return ups;
    }

    public void setUps(List<String> ups) {
        this.ups = ups;
    }

    public DateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(DateTime createAt) {
        this.createAt = createAt;
    }

    // TODO 对markdown做参数过滤
    private String filterContent = null;

    public String makeSureAndGetFilterContent() {
        if (filterContent == null) { // 需要渲染
            filterContent = MarkdownUtils.cnodeFilter(getContent());
        }
        return filterContent;
    }

}
