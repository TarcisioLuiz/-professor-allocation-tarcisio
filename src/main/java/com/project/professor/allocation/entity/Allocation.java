package com.project.professor.allocation.entity;

import java.time.DayOfWeek;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Allocation {

	@Id
	@JsonProperty(access=JsonProperty.Access.READ_ONLY)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name = "day", nullable = false)
	private DayOfWeek day;
	
	@Temporal(value = TemporalType.TIME)
	@Column(name = "start", nullable = false)
	@JsonFormat(pattern="HH:mmZ")
	@JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
	@ApiModelProperty(example="19:00-0300")
	private Date start;
	
	@Temporal(value = TemporalType.TIME)
	@Column(name = "end", nullable = false)
	@JsonFormat(pattern="HH:mmZ")
	@JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
	@ApiModelProperty(example="22:30-0300")
	private Date end;
	
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	@Column(name = "course_id", nullable = false)
	private Long courseId;
	
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	@Column(name = "professor_id", nullable = false)
	private Long professorId;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "course_id", insertable = false, updatable = false, nullable = false)
	@JsonProperty(access=JsonProperty.Access.READ_ONLY)
	private Course course;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "professor_id", insertable = false, updatable = false, nullable = false)
	@JsonProperty(access=JsonProperty.Access.READ_ONLY)
	private Professor professor;
	
	public Allocation(Long id, DayOfWeek day, Date start, Date end) {
		super();
		this.id = id;
		this.day = day;
		this.start = start;
		this.end = end;
	}
	
	public Allocation() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}
	
	

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@Override
	public String toString() {
		return "Allocation [id=" + id + ", day=" + day + ", start=" + start + ", end=" + end + "]";
	}
	
	
}
