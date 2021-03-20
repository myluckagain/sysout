package ru.sysout.projections.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(generator = "sequence")
    private Long id;

    private String title;

    private String text;

    @CreationTimestamp
    private LocalDateTime createdDateTime;

    @ManyToOne
    private User user;
}
