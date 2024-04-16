package jpa.crudpractice.domain.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PostDeleteRequest implements Serializable {
    private Long postId;
}
