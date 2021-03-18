package ru.sysout.modelmapper.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
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
    @ManyToOne(fetch = FetchType.LAZY)

    private User user;
}
