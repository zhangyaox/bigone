package com.zhangyaoxing.cms.entity;

import java.io.Serializable;

public class ArticleWithBLOBs extends Article implements Serializable {
    /**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 3498479290700142284L;

	private String content;

    private String summary;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

	@Override
	public String toString() {
		return "ArticleWithBLOBs [content=" + content + ", summary=" + summary + "]";
	}
    
    
}