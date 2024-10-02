package com.example.book.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@NotEmpty
    @Size(min = 1)
    private String code;

    @NotEmpty
    @Size(min = 1)
    private String title;
    
    @NotEmpty
    @Size(min = 1)
    private String author;
    
	@NotEmpty
    @Size(min = 1)
    private String type;

    @NotEmpty
    @Size(min = 1)
    private String publisher;

	@NotEmpty
    @Size(min = 1)
    private String edition;

	@NotEmpty
    @Size(min = 1)
    private String year;
   
	@NotEmpty
    @Size(min = 1)
    private String circulation;

	@NotEmpty
    @Size(min = 1)
    private String school;

	@NotEmpty
    @Size(min = 1)
    private String isb;

	@NotEmpty
    @Size(min = 1)
    private String signOffStatus;

	@NotEmpty
    @Size(min = 1)
    private String topic;

	@NotEmpty
    @Size(min = 1)
    private String keywords;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    public Book() {
    }
    
    public Book(String title, String thoughts, String auth , String circulation , String code, String type, String publisher, String edition, String year, String topic, String keywords) {
        this.title = title;
        this.circulation = circulation;
        this.author = auth;
		this.code = code;
		this.type = type;
		this.publisher = publisher;
		this.edition = edition;
		this.year = year;
		this.topic = topic;
		this.keywords = keywords;
		
    }
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getAuthor() {
		return author;
	}
	
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCirculation() {
		return circulation;
	}

	public void setCirculation(String circulation) {
		this.circulation = circulation;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getIsb() {
        return isb;
    }

    public void setIsb(String isb) {
        this.isb = isb;
    }

    public String getSignOffStatus() {
        return signOffStatus;
    }

    public void setSignOffStatus(String signOffStatus) {
        this.signOffStatus = signOffStatus;
    }

	
}
