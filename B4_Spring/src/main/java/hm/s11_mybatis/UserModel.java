package hm.s11_mybatis;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserModel {
    private Long id;
    private String name;
}