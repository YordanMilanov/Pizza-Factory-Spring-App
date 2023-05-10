package bg.softuni.pizzashop.model.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@Getter
@Setter
public class Comment extends BaseEntity {

    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(name = "created")
    private LocalDateTime createTime;

    @ManyToOne
    private User author;
}
